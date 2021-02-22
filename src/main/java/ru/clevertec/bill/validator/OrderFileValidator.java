package ru.clevertec.bill.validator;

public class OrderFileValidator {
    private static final String PARAMETER_REGEX = "\\d{1,2}-\\d{1,2}";
    private static final String CARD_REGEX = "card-\\d{4}";

    private OrderFileValidator() {
    }

    public static boolean isParametersValid(String element) {
        return element.matches(PARAMETER_REGEX);
    }

    public static boolean isCardValid(String element) {
        return element.matches(CARD_REGEX);
    }
}
