package com.sipios.refactoring.service;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.model.CustomerType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;

import java.time.LocalDate;


class DiscountServiceTest extends UnitTest {

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
        @CsvSource(value = {"2020/06/10 | true", "2020/01/10 | true",
            "2020/03/30 | false", "2020/10/30 | false"}, delimiter = '|')
        public void should_return_true(@ConvertWith(SlashyDateConverter.class) LocalDate localDate, boolean expectedValue) {
            Assertions.assertThat(discountService.isWinterOrSummerDiscountPeriod(localDate)).isEqualTo(expectedValue);
        }
    }
}

class SlashyDateConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context)
        throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException(
                "The argument should be a string: " + source);
        }
        try {
            String[] parts = ((String) source).split("/");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert", e);
        }
    }
}
