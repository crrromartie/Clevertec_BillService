package ru.clevertec.bill.validator;

public class DiscountCardValidator {
    private static final int MIN_CARD_NUMBER = 1000;
    private static final int MAX_CARD_NUMBER = 9999;
    private static final int MIN_DISCOUNT_PERCENT = 10;
    private static final int MAX_DISCOUNT_PERCENT = 40;

    private DiscountCardValidator() {
    }

    public static boolean isCardNumberValid(int cardNumber) {
        return (cardNumber >= MIN_CARD_NUMBER && cardNumber <= MAX_CARD_NUMBER);
    }

    public static boolean isDiscountPercentValid(int discountPercent) {
        return (discountPercent >= MIN_DISCOUNT_PERCENT && discountPercent <= MAX_DISCOUNT_PERCENT);
    }
}
