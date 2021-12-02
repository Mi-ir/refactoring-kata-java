package com.sipios.refactoring.service;

import com.sipios.refactoring.model.Cart;
import com.sipios.refactoring.model.Item;
import com.sipios.refactoring.model.ItemType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Service
public class PriceService {

    private final DiscountService discountService;

    public PriceService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public double computePrice(final Cart requestCart) {

        final var discount = discountService.computeDiscount(requestCart.getCustomerType());

        final var items = Objects.requireNonNullElse(requestCart.getItems(), new Item[]{});

        final var winterOrSummerDiscountPeriod = discountService.isWinterOrSummerDiscountPeriod(LocalDate.now());

        return Arrays.stream(items)
            .map(item -> computeItemPrice(discount, item, winterOrSummerDiscountPeriod))
            .reduce(0D, Double::sum);
    }

    private double computeItemPrice(double discount, Item item, boolean winterOrSummerDiscountPeriod) {
        ItemType itemType = item.getType();
        double itemPrice = itemType.getPrice() * item.getNb() * discount;
        if (winterOrSummerDiscountPeriod) {
            itemPrice = itemPrice * itemType.getAdditionnalDiscountRatio();
        }
        return itemPrice;
    }

}
