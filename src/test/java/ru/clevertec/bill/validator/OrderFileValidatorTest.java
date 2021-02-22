package ru.clevertec.bill.validator;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderFileValidatorTest {
    @Test
    void isParametersValidPositiveTest() {
        String orderParam = "1-5";
        boolean actual = OrderFileValidator.isParametersValid(orderParam);
        assertTrue(actual);
    }

    @Test
    void isParametersValidNegativeTest() {
        String orderParam = "1--2";
        boolean actual = OrderFileValidator.isParametersValid(orderParam);
        assertFalse(actual);
    }

    @Test
    void isCardValidPositiveTest() {
        String card = "card-5568";
        boolean actual = OrderFileValidator.isCardValid(card);
        assertTrue(actual);
    }

    @Test
    void isCardValidNegativeTest() {
        String card = "card-55689";
        boolean actual = OrderFileValidator.isCardValid(card);
        assertFalse(actual);
    }
}
