package ru.clevertec.bill.validator;

import ru.clevertec.bill.controller.command.ParameterName;

import java.util.Map;

public class DiscountCardValidator {
    private static final int MIN_CARD_NUMBER = 1000;
    private static final int MAX_CARD_NUMBER = 9999;
    private static final int MIN_DISCOUNT_PERCENT = 10;
    private static final int MAX_DISCOUNT_PERCENT = 40;

    private DiscountCardValidator() {
    }

    public static boolean isCardParametersValid(Map<String, String> cardParameters) {
        boolean isValid = true;
        if (!isCardNumberValid(cardParameters.get(ParameterName.CARD_NUMBER))) {
            isValid = false;
        }
        if (!isDiscountPercentValid(cardParameters.get(ParameterName.CARD_DISCOUNT_PERCENT))) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isCardNumberValid(String cardNumber) {
        return (Integer.parseInt(cardNumber) >= MIN_CARD_NUMBER
                && Integer.parseInt(cardNumber) <= MAX_CARD_NUMBER);
    }

    public static boolean isDiscountPercentValid(String discountPercent) {
        return (Integer.parseInt(discountPercent) >= MIN_DISCOUNT_PERCENT
                && Integer.parseInt(discountPercent) <= MAX_DISCOUNT_PERCENT);
    }
}
