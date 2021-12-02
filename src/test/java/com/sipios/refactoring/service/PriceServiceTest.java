package com.sipios.refactoring.service;

import com.sipios.refactoring.model.Cart;
import com.sipios.refactoring.model.CustomerType;
import com.sipios.refactoring.model.Item;
import com.sipios.refactoring.model.ItemType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.TimeZone;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private DiscountService discountService;

    final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

    @Nested
    @DisplayName("Compute price with sale")
    class ComputePriceWithSale {

        @BeforeEach
        public void init() {
            when(discountService.isWinterOrSummerDiscountPeriod(any())).thenReturn(true);
            when(discountService.computeDiscount(any())).thenReturn(1D);
        }

        @Test
        public void should_compute_tshirt_price() {
            //GIVEN
            final var tshirt = new Item(ItemType.TSHIRT, 10);
            final var cart = new Cart(new Item[]{tshirt}, CustomerType.STANDARD_CUSTOMER);

            //WHEN
            final var actualPrice = priceService.computePrice(cart,  cal);

            //THEN
            Assertions.assertThat(actualPrice).isEqualTo(300);
        }


        @Test
        public void should_compute_dress_price() {
            //GIVEN
            final var tshirt = new Item(ItemType.DRESS, 10);
            final var cart = new Cart(new Item[]{tshirt}, CustomerType.STANDARD_CUSTOMER);

            //WHEN
            final var actualPrice = priceService.computePrice(cart,  cal);

            //THEN
            Assertions.assertThat(actualPrice).isEqualTo(400);
        }


        @Test
        public void should_compute_jacket_price() {
            //GIVEN
            final var tshirt = new Item(ItemType.JACKET, 10);
            final var cart = new Cart(new Item[]{tshirt}, CustomerType.STANDARD_CUSTOMER);

            //WHEN
            final var actualPrice = priceService.computePrice(cart,  cal);

            //THEN
            Assertions.assertThat(actualPrice).isEqualTo(900);
        }
    }

    @Nested
    @DisplayName("Compute price without sale")
    class ComputePriceWithoutSale {

        @BeforeEach
        public void init() {
            when(discountService.isWinterOrSummerDiscountPeriod(any())).thenReturn(false);
            when(discountService.computeDiscount(any())).thenReturn(1D);
        }

        @Test
        public void should_compute_tshirt_price() {
            //GIVEN

            final var tshirt = new Item(ItemType.TSHIRT, 10);
            final var cart = new Cart(new Item[]{tshirt}, CustomerType.STANDARD_CUSTOMER);

            //WHEN
            final var actualPrice = priceService.computePrice(cart,  cal);

            //THEN
            Assertions.assertThat(actualPrice).isEqualTo(300);
        }


        @Test
        public void should_compute_dress_price() {
            //GIVEN
            final var tshirt = new Item(ItemType.DRESS, 10);
            final var cart = new Cart(new Item[]{tshirt}, CustomerType.STANDARD_CUSTOMER);

            //WHEN
            final var actualPrice = priceService.computePrice(cart,  cal);

            //THEN
            Assertions.assertThat(actualPrice).isEqualTo(500);
        }

        @Test
        public void should_compute_jacket_price() {
            //GIVEN
            final var tshirt = new Item(ItemType.JACKET, 10);
            final var cart = new Cart(new Item[]{tshirt}, CustomerType.STANDARD_CUSTOMER);

            //WHEN
            final var actualPrice = priceService.computePrice(cart,  cal);

            //THEN
            Assertions.assertThat(actualPrice).isEqualTo(1000);
        }
    }


}
