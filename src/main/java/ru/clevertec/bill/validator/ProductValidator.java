package ru.clevertec.bill.validator;

import java.math.BigDecimal;

public class ProductValidator {
    private static final String NAME_REGEX = "\\w{3,30}";
    private static final long MIN = 0;
    private static final long MAX = 100;

    private ProductValidator() {
    }

    public static boolean isProductNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isProductPriceValid(BigDecimal price) {
        return (price.compareTo(BigDecimal.valueOf(MIN)) > 0
                && price.compareTo(BigDecimal.valueOf(MAX)) < 0);
    }
}
