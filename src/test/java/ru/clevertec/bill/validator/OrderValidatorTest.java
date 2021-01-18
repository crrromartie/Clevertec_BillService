package ru.clevertec.bill.validator;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {
    @Test
    void isParametersValidPositiveTest() {
        String orderParam = "1-5";
        boolean actual = OrderValidator.isParametersValid(orderParam);
        assertTrue(actual);
    }

    @Test
    void isParametersValidNegativeTest() {
        String orderParam = "1--2";
        boolean actual = OrderValidator.isParametersValid(orderParam);
        assertFalse(actual);
    }

    @Test
    void isCardValidPositiveTest() {
        String card = "card-5568";
        boolean actual = OrderValidator.isCardValid(card);
        assertTrue(actual);
    }

    @Test
    void isCardValidNegativeTest() {
        String card = "card-55689";
        boolean actual = OrderValidator.isCardValid(card);
        assertFalse(actual);
    }
}
