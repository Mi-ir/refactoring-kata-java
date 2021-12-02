package com.sipios.refactoring.controller;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.model.Cart;
import com.sipios.refactoring.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static com.sipios.refactoring.model.CustomerType.STANDARD_CUSTOMER;

class ShoppingControllerTests extends UnitTest {

    @InjectMocks
    private ShoppingController controller;

    @Test
    void should_not_throw() {
        Assertions.assertDoesNotThrow(
            () -> controller.getPrice(new Cart(new Item[] {}, STANDARD_CUSTOMER))
        );
    }
}
