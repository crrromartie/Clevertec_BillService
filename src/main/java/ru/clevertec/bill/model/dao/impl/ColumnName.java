package ru.clevertec.bill.model.dao.impl;

class ColumnName {
    //table 'products'
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_IS_PROMO = "is_promo";
    //table 'discountCards'
    public static final String CARD_ID = "card_id";
    public static final String CARD_NUMBER = "card_number";
    public static final String CARD_DISCOUNT = "discount_percent";

    private ColumnName() {
    }
}
