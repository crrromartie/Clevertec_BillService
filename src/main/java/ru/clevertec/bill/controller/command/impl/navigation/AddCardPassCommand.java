package ru.clevertec.bill.controller.command.impl.navigation;

import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class AddCardPassCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.NEW_CARD_PAGE);
    }
}
