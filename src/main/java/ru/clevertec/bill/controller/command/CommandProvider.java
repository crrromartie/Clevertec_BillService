package ru.clevertec.bill.controller.command;

import ru.clevertec.bill.controller.command.Impl.navigation.CardPassCommand;
import ru.clevertec.bill.controller.command.Impl.navigation.MainPassCommand;
import ru.clevertec.bill.controller.command.Impl.navigation.ProductPassCommand;
import ru.clevertec.bill.controller.command.Impl.navigation.SellerPassCommand;
import ru.clevertec.bill.controller.command.Impl.*;

import java.util.EnumMap;

public enum CommandProvider {
    PROVIDER;

    public enum CommandName {
        CHANGE_LOCALE, NO_SUCH_COMMAND, VIEW_PRODUCTS, MAKE_BILL, WRITE_BILL_TXT, WRITE_BILL_PDF,
        WRITE_BILL_PDF_TEMPLATE, MAIN_PASS, DELETE_PRODUCT, ADD_PRODUCT, CHANGE_PRICE, PROMO_YES, PROMO_NO,
        ADD_CARD, DELETE_CARD, CHANGE_PROMO, SELLER_PASS, PRODUCT_PASS, CARD_PASS
    }

    private EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    CommandProvider() {
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.VIEW_PRODUCTS, new ViewProductsCommand());
        commands.put(CommandName.MAKE_BILL, new MakeBillCommand());
        commands.put(CommandName.WRITE_BILL_TXT, new WriteBillTextCommand());
        commands.put(CommandName.WRITE_BILL_PDF, new WriteBillPDFCommand());
        commands.put(CommandName.WRITE_BILL_PDF_TEMPLATE, new WriteBillPDFTemplateCommand());
        commands.put(CommandName.DELETE_PRODUCT, new DeleteProductCommand());
        commands.put(CommandName.ADD_PRODUCT, new AddProductCommand());
        commands.put(CommandName.CHANGE_PRICE, new ChangePriceCommand());
        commands.put(CommandName.PROMO_YES, new PromoYesCommand());
        commands.put(CommandName.PROMO_NO, new PromoNoCommand());
        commands.put(CommandName.ADD_CARD, new AddDiscountCardCommand());
        commands.put(CommandName.DELETE_CARD, new DeleteDiscountCardCommand());
        commands.put(CommandName.CHANGE_PROMO, new ChangeDiscountPercentCommand());
        commands.put(CommandName.MAIN_PASS, new MainPassCommand());
        commands.put(CommandName.SELLER_PASS, new SellerPassCommand());
        commands.put(CommandName.PRODUCT_PASS, new ProductPassCommand());
        commands.put(CommandName.CARD_PASS, new CardPassCommand());
    }

    public Command takeCommand(String command) {
        if (command == null || command.isBlank()) {
            return commands.get(CommandName.NO_SUCH_COMMAND);
        }
        Command value;
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase());
            value = commands.get(commandName);
        } catch (IllegalArgumentException e) {
            value = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return value;
    }
}
