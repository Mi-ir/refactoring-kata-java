package com.sipios.refactoring.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DiscountService {

    public double computeDiscount(String type){
        switch (type) {
            case "STANDARD_CUSTOMER":
                return 1;
            case "PREMIUM_CUSTOMER":
                return 0.9;
            case "PLATINUM_CUSTOMER":
                return 0.5;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
