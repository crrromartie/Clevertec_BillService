package ru.clevertec.bill.controller.command.impl.navigation;

import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MainPassCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(AttributeName.PRODUCTS);
        session.removeAttribute(AttributeName.BILL);
        session.removeAttribute((AttributeName.SAVE_BILL));
        session.removeAttribute(AttributeName.BILL_FILE_PATH);
        Router router = new Router();
        router.setPage(PagePath.MAIN_PAGE);
        return router;
    }
}
