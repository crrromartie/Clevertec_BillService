package ru.clevertec.bill.controller.command;

import ru.clevertec.bill.controller.command.impl.*;
import ru.clevertec.bill.controller.command.impl.navigation.*;

public enum CommandType {
    NO_SUCH_COMMAND(new NoSuchCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    MAKE_BILL(new MakeBillCommand()),
    WRITE_BILL(new WriteBillCommand()),
    ADD_PRODUCT(new AddProductCommand()),
    DELETE_PRODUCT(new DeleteProductCommand()),
    EDIT_PRODUCT(new EditProductCommand()),
    ADD_CARD(new AddDiscountCardCommand()),
    DELETE_CARD(new DeleteDiscountCardCommand()),
    EDIT_CARD(new EditCardCommand()),
    HOME_PASS(new HomePassCommand()),
    BILL_PASS(new BillPassCommand()),
    PRODUCT_PASS(new ProductPassCommand()),
    PRODUCTS_PASS(new ProductsPassCommand()),
    ADD_PRODUCT_PASS(new AddProductPassCommand()),
    CARD_PASS(new CardPassCommand()),
    CARDS_PASS(new CardsPassCommand()),
    ADD_CARD_PASS(new AddCardPassCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
