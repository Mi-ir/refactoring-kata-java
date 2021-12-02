package com.sipios.refactoring.service;

import com.sipios.refactoring.model.CustomerType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    @Nested
    @DisplayName("Compute Discount")
    class ComputeDiscount {

        @ParameterizedTest
        @CsvSource(value = {"STANDARD_CUSTOMER | 1", "PREMIUM_CUSTOMER | 0.9",
            "PLATINUM_CUSTOMER | 0.5"}, delimiter = '|')
        public void should_compute_discount(CustomerType type,
                                            double expectedDiscount) {
            //THEN
            final var actualDiscount = discountService.computeDiscount(type);

            Assertions.assertThat(actualDiscount).isEqualTo(expectedDiscount);
        }
    }

    @Nested
    @DisplayName("Discount Period")
    class DiscountPeriod {

        @ParameterizedTest
        @CsvSource(value = {"1591740000000 | true", "1578610800000 | true",
            "1592258400000 | false", "1590962400000 | false"}, delimiter = '|')
        public void should_return_true(long dateAsMillis, boolean expectedValue) {
            final var date = new Date(dateAsMillis);
            final var cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(date);

            Assertions.assertThat(discountService.isWinterOrSummerDiscountPeriod(cal)).isEqualTo(expectedValue);
        }
    }
}
