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

            Assertions.assertThat(actualDiscount)
                .isEqualTo(expectedDiscount);
        }
    }

    @Nested
    @DisplayName("Discount Period")
    class DiscountPeriod {

    }
}
