package com.sipios.refactoring.controller;

import com.sipios.refactoring.model.Cart;
import com.sipios.refactoring.service.DiscountService;
import com.sipios.refactoring.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.sipios.refactoring.model.CustomerType.*;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private final Logger logger;
    private final DiscountService discountService;
    private final PriceService priceService;

    public ShoppingController(final DiscountService discountService, PriceService priceService) {
        this.priceService = priceService;
        this.logger = LoggerFactory.getLogger(ShoppingController.class);
        this.discountService = discountService;
    }

    @PostMapping
    public String getPrice(@RequestBody Cart requestCart) {

        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);

        // Compute discount for customer
        double d = discountService.computeDiscount(requestCart.getCustomerType());

        // Compute total amount depending on the types and quantity of product and
        // if we are in winter or summer discounts periods
        double price = priceService.computePrice(requestCart, d, cal);

        try {
            if (requestCart.getCustomerType().equals(STANDARD_CUSTOMER)) {
                if (price > 200) {
                    throw new Exception("Price (" + price + ") is too high for standard customer");
                }
            } else if (requestCart.getCustomerType().equals(PREMIUM_CUSTOMER)) {
                if (price > 800) {
                    throw new Exception("Price (" + price + ") is too high for premium customer");
                }
            } else if (requestCart.getCustomerType().equals(PLATINUM_CUSTOMER)) {
                if (price > 2000) {
                    throw new Exception("Price (" + price + ") is too high for platinum customer");
                }
            } else {
                if (price > 200) {
                    throw new Exception("Price (" + price + ") is too high for standard customer");
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return String.valueOf(price);
    }

}




