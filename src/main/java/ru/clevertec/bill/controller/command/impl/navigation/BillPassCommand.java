package ru.clevertec.bill.controller.command.impl.navigation;

import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class BillPassCommand implements Command {
    private static final String TRUE = "true";

    @Override
    public Router execute(HttpServletRequest request) {
        if (request.getParameter(ParameterName.SAVE_BILL) != null) {
            request.setAttribute(ParameterName.SAVE_BILL,
                    request.getParameter(AttributeName.SAVE_BILL).equals(TRUE));
        }
        return new Router(PagePath.BILL_PAGE);
    }
}
