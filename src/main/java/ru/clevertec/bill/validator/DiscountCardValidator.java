package ru.clevertec.bill.validator;

public class DiscountCardValidator {
    public static boolean isCardNumberValid(int cardNumberValid) {
        return (cardNumberValid > 999 && cardNumberValid < 10000);
    }

    public static boolean isPromoDiscountValid(double promoDiscount) {
        return (promoDiscount > 0.0 && promoDiscount < 0.6);
    }

    private DiscountCardValidator() {
    }
}
