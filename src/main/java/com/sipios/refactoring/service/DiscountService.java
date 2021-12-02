package com.sipios.refactoring.service;

import com.sipios.refactoring.model.CustomerType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;

@Service
public class DiscountService {

    public double computeDiscount(CustomerType type){
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

    public boolean isWinterOrSummerDiscountPeriod(Calendar cal) {
        return (cal.get(Calendar.DAY_OF_MONTH) < 15
            && cal.get(Calendar.DAY_OF_MONTH) > 5
            && cal.get(Calendar.MONTH) == 5)
            ||
            (cal.get(Calendar.DAY_OF_MONTH)
            < 15
            && cal.get(Calendar.DAY_OF_MONTH) > 5
            && cal.get(Calendar.MONTH) == 0);
    }

}
