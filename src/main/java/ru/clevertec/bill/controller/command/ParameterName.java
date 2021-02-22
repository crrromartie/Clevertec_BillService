package ru.clevertec.bill.controller.command;

public class ParameterName {
    //general
    public static final String COMMAND = "command";
    //product
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_PROMO = "productPromo";
    //card
    public static final String CARD_ID = "cardId";
    public static final String CARD_NUMBER = "cardNumber";
    public static final String CARD_DISCOUNT_PERCENT = "cardDiscountPercent";
    //bill
    public static final String SAVE_BILL = "saveBill";
    //writer
    public static final String WRITING_FORMAT = "writing_format";
    public static final String TXT = "txt";
    public static final String PDF = "pdf";
    public static final String PDF_CLEVERTEC = "pdf_clevertec";

    private ParameterName() {
    }
}
