package ru.clevertec.bill.model.dao.impl;

public class ColumnName {
    //table 'products'
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_IS_PROMO = "isPromotional";
    //table 'discountCards'
    public static final String CARD_ID = "cardId";
    public static final String CARD_NUMBER = "cardNumber";
    public static final String CARD_DISCOUNT = "discount";

    private ColumnName() {
    }
}
