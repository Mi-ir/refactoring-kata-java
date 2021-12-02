package com.sipios.refactoring.service;

import com.sipios.refactoring.model.CustomerType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Month;

@Service
public class DiscountService {

    public double computeDiscount(CustomerType type) {
        switch (type) {
            case STANDARD_CUSTOMER:
                return 1;
            case PREMIUM_CUSTOMER:
                return 0.9;
            case PLATINUM_CUSTOMER:
                return 0.5;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean isWinterOrSummerDiscountPeriod(LocalDate now) {

        int dayOfMonth = now.getDayOfMonth();

        return (dayOfMonth < 15
            && dayOfMonth > 5
            && now.getMonth() == Month.JUNE)
            ||
            (dayOfMonth
                < 15
                && dayOfMonth > 5
                && now.getMonth() == Month.JANUARY);
    }

}
