package ru.clevertec.bill.controller.command;

public class AttributeName {
    //general
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LANG = "lang";
    public static final String BILL_FILE_PATH = "billFilePath";
    //product
    public static final String PRODUCTS = "products";
    //bill
    public static final String BILL = "bill";
    //notification
    public static final String SAVE_BILL = "saveBill";
    public static final String ADD_PRODUCT = "addProduct";
    public static final String DELETE_PRODUCT = "deleteProduct";
    public static final String CHANGE_PRICE = "changePrice";
    public static final String MAKE_PROMO = "makePromo";
    public static final String REMOVE_PROMO = "removePromo";
    public static final String ADD_CARD = "addCard";
    public static final String DELETE_CARD = "deleteCard";
    public static final String CHANGE_DISCOUNT = "changeDiscount";
    //message
    public static final String INCORRECT_PRODUCT_DATA = "incorrectProductData";
    public static final String INCORRECT_CARD_DATA = "incorrectCardData";

    private AttributeName() {
    }
}
