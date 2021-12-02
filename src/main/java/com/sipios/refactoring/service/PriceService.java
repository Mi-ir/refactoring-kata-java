package com.sipios.refactoring.service;

import com.sipios.refactoring.model.Cart;
import com.sipios.refactoring.model.Item;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;

import static com.sipios.refactoring.model.ItemType.*;

@Service
public class PriceService {

    private final DiscountService discountService;

    public PriceService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public double computePrice(final Cart requestCart,
                               final Calendar cal) {

        double discount = discountService.computeDiscount(requestCart.getCustomerType());

        double price = 0;
        final var items = requestCart.getItems();

        boolean winterOrSummerDiscountPeriod = discountService.isWinterOrSummerDiscountPeriod(cal);

        if (items != null) {
            Arrays.stream(items).forEach(item ->{

            } );
            for (final var item : items) {
                switch (item.getType()) {
                    case TSHIRT:
                        price += computeItemPrice(discount, item);
                        break;
                    case DRESS: {
                        double itemPrice = DRESS.getPrice() * item.getNb() * discount;
                        if (winterOrSummerDiscountPeriod) {
                            itemPrice = itemPrice * 0.8;
                        }
                        price += itemPrice;
                        break;
                    }
                    case JACKET: {
                        double itemPrice = JACKET.getPrice() * item.getNb() * discount;
                        if (winterOrSummerDiscountPeriod) {
                            itemPrice = itemPrice * 0.9;
                        }
                        price += itemPrice;
                        break;
                    }
                }
            }
        }
        return price;
    }

    private double computeItemPrice(double discount, Item item) {
        return TSHIRT.getPrice() * item.getNb() * discount;
    }

    public double computePrice_old(Cart requestCart,
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
