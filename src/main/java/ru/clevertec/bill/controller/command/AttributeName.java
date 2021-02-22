package ru.clevertec.bill.controller.command;

public class AttributeName {
    //general
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LANG = "lang";
    public static final String BILL_FILE_PATH = "billFilePath";
    //product
    public static final String PRODUCT = "product";
    public static final String PRODUCTS = "products";
    public static final String PRODUCT_PARAMETERS = "productParameters";
    //card
    public static final String CARD = "card";
    public static final String CARDS = "cards";
    public static final String CARD_PARAMETERS = "cardParameters";
    //bill
    public static final String BILL = "bill";
    public static final String SAVE_BILL = "saveBill";
    //message
    public static final String UNIQUE_PRODUCT_NAME_ERROR = "uniqueProductNameError";
    public static final String UNIQUE_CARD_NUMBER_ERROR = "uniqueCardNumberError";
    public static final String INCORRECT_PRODUCT_DATA = "incorrectProductData";
    public static final String INCORRECT_CARD_DATA = "incorrectCardData";

    private AttributeName() {
    }
}
