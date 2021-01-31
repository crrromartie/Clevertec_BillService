package ru.clevertec.bill.controller.command.impl;

import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String requestLang = request.getParameter(AttributeName.LANG);
        router.setPage(currentPage);
        session.setAttribute(AttributeName.LANG, requestLang);
        return router;
    }
}
