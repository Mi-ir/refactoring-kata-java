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

    public double computePrice(final Cart requestCart,
                               final double discount,
                               final Calendar cal) {
        var price = 0;
        final var items = requestCart.getItems();
        if (!discountService.isWinterOrSummerDiscountPeriod(cal)) {
            if (items != null) {
                for (final var item : items) {
                    if (item.getType().equals(TSHIRT)) {
                        price += 30 * item.getNb() * discount;
                    } else if (item.getType().equals(DRESS)) {
                        price += 50 * item.getNb() * discount;
                    } else if (item.getType().equals(JACKET)) {
                        price += 100 * item.getNb() * discount;
                    }
                }
            }
        } else {
            if (items != null) {
                for (final var item : items) {
                    if (item.getType().equals(TSHIRT)) {
                        price += 30 * item.getNb() * discount;
                    } else if (item.getType().equals(DRESS)) {
                        price += 50 * item.getNb() * 0.8 * discount;
                    } else if (item.getType().equals(JACKET)) {
                        price += 100 * item.getNb() * 0.9 * discount;
                    }
                }
            }
        }
        return price;
    }
}
