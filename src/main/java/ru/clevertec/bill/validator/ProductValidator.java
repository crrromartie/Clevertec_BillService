package ru.clevertec.bill.validator;

import ru.clevertec.bill.controller.command.ParameterName;

import java.util.Map;

public class ProductValidator {
    private static final String NAME_REGEX = "\\S{3,30}";
    private static final long MIN = 1;
    private static final long MAX = 99;

    private ProductValidator() {
    }

    public static boolean isProductParametersValid(Map<String, String> productParameters) {
        boolean isValid = true;
        if (!isProductNameValid(productParameters.get(ParameterName.PRODUCT_NAME))) {
            isValid = false;
        }
        if (!isProductPriceValid(productParameters.get(ParameterName.PRODUCT_PRICE))) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isProductNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isProductPriceValid(String price) {
        boolean isValid = false;
        if (price != null && !price.isEmpty()) {
            double doublePrice = Double.parseDouble(price);
            isValid = (doublePrice >= MIN && doublePrice <= MAX);
        }
        return isValid;
    }
}
