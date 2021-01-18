package ru.clevertec.bill.controller.command;

public class AttributeName {
    //general
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LANG = "lang";
    public static final String DATE = "date";
    //product
    public static final String PRODUCTS = "products";
    public static final String PURCHASES = "purchases";
    //card
    public static final String CARD_DISCOUNT_PERCENT = "cardDiscountPercent";
    public static final String CARD_DISCOUNT = "cardDiscount";
    //total
    public static final String TOTAL_FOR_PAY = "totalForPay";
    public static final String TOTAL_PROMO_DISCOUNT = "totalPromoDiscount";
    //notification
    public static final String SAVE_BILL = "saveBill";
    public static final String ADD_PRODUCT = "addProduct";
    public static final String DELETE_PRODUCT = "deleteProduct";
    public static final String CHANGE_PRICE = "changePrice";
    public static final String PROMO_YES = "promoYes";
    public static final String PROMO_NO = "promoNo";
    public static final String ADD_CARD = "addCard";
    public static final String DELETE_CARD = "deleteCard";
    public static final String CHANGE_DISCOUNT = "changeDiscount";
    //message
    public static final String NAME_PRODUCT_UNIQUE_ERROR = "nameUnique";
    public static final String INCORRECT_PRODUCT_DATA = "incorrectProductData";
    public static final String CARD_PRODUCT_UNIQUE_ERROR = "cardUnique";
    public static final String INCORRECT_CARD_DATA = "incorrectCardData";

    private AttributeName() {
    }
}
