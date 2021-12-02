package com.sipios.refactoring.controller;

import com.sipios.refactoring.model.Cart;
import com.sipios.refactoring.model.CustomerType;
import com.sipios.refactoring.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private final PriceService priceService;

    public ShoppingController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping
    public String getPrice(@RequestBody Cart cart) {

        double price = priceService.computePrice(cart);

        validatePrice(price, cart.getCustomerType());

        return String.valueOf(price);
    }

    private void validatePrice(double price, CustomerType customerType) {
        if (price > customerType.getMaxPrice()) {
            String message = String.format("Price (%s) is too high for %s customer", price, customerType.getValue());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

}




