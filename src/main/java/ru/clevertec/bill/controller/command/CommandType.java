package ru.clevertec.bill.controller.command;

import ru.clevertec.bill.controller.command.impl.*;
import ru.clevertec.bill.controller.command.impl.navigation.CardPassCommand;
import ru.clevertec.bill.controller.command.impl.navigation.MainPassCommand;
import ru.clevertec.bill.controller.command.impl.navigation.ProductPassCommand;
import ru.clevertec.bill.controller.command.impl.navigation.SellerPassCommand;

public enum CommandType {
    NO_SUCH_COMMAND(new NoSuchCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    SHOW_PRODUCTS(new ShowProductsCommand()),
    MAKE_BILL(new MakeBillCommand()),
    WRITE_BILL(new WriteBillCommand()),
    ADD_PRODUCT(new AddProductCommand()),
    DELETE_PRODUCT(new DeleteProductCommand()),
    CHANGE_PRICE(new ChangePriceCommand()),
    MAKE_PROMO(new MakePromoCommand()),
    REMOVE_PROMO(new RemovePromoCommand()),
    ADD_DISCOUNT_CARD(new AddDiscountCardCommand()),
    DELETE_DISCOUNT_CARD(new DeleteDiscountCardCommand()),
    CHANGE_DISCOUNT_PERCENT(new ChangeDiscountPercentCommand()),
    MAIN_PASS(new MainPassCommand()),
    SELLER_PASS(new SellerPassCommand()),
    PRODUCT_PASS(new ProductPassCommand()),
    CARD_PASS(new CardPassCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
