package com.sipios.refactoring.service;

import com.sipios.refactoring.model.Cart;
import com.sipios.refactoring.model.Item;
import org.springframework.stereotype.Service;

import java.util.Calendar;

import static com.sipios.refactoring.model.ItemType.*;

@Service
public class PriceService {

    private final DiscountService discountService;

    public PriceService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public double computePrice(Cart requestCart,
                               double d,
                               Calendar cal) {
        var price = 0;
        if (!discountService.isWinterOrSummerDiscountPeriod(cal)) {
            if (requestCart.getItems() != null) {
                for (int i = 0; i < requestCart.getItems().length; i++) {
                    Item it = requestCart.getItems()[i];
                    if (it.getType().equals(TSHIRT)) {
                        price += 30 * it.getNb() * d;
                    } else if (it.getType().equals(DRESS)) {
                        price += 50 * it.getNb() * d;
                    } else if (it.getType().equals(JACKET)) {
                        price += 100 * it.getNb() * d;
                    }
                }
            }
        } else {
            if (requestCart.getItems() != null) {
                for (int i = 0; i < requestCart.getItems().length; i++) {
                    Item it = requestCart.getItems()[i];

                    if (it.getType().equals(TSHIRT)) {
                        price += 30 * it.getNb() * d;
                    } else if (it.getType().equals(DRESS)) {
                        price += 50 * it.getNb() * 0.8 * d;
                    } else if (it.getType().equals(JACKET)) {
                        price += 100 * it.getNb() * 0.9 * d;
                    }
                }
            }
        }
        return price;
    }
}
