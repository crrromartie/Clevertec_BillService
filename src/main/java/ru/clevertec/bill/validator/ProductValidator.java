package ru.clevertec.bill.validator;

public class ProductValidator {
    private static final String NAME_REGEX = "\\w{3,30}";

    private ProductValidator() {
    }

    public static boolean isProductNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isProductPriceValid(double price) {
        return (price > 0.0);
    }
}
