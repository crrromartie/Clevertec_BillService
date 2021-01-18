package ru.clevertec.bill.controller.command.Impl.navigation;

import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ProductPassCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.PRODUCT_PAGE);
    }
}
