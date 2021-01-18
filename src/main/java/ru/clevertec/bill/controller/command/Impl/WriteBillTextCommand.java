package ru.clevertec.bill.controller.command.Impl;

import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.entity.SinglePurchase;
import ru.clevertec.bill.util.BillConfigurator;
import ru.clevertec.bill.writer.BillTextWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

public class WriteBillTextCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<SinglePurchase> purchases = (List<SinglePurchase>) session.getAttribute(AttributeName.PURCHASES);
        double cardDiscount = Double.parseDouble(session.getAttribute(AttributeName.CARD_DISCOUNT).toString());
        double cardDiscountPercent = Double.parseDouble(session
                .getAttribute(AttributeName.CARD_DISCOUNT_PERCENT).toString());
        double promoDiscount = Double.parseDouble(session.getAttribute(AttributeName.TOTAL_PROMO_DISCOUNT).toString());
        double totalForPay = Double.parseDouble(session.getAttribute(AttributeName.TOTAL_FOR_PAY).toString());
        String date = session.getAttribute(AttributeName.DATE).toString();
        BillConfigurator billConfigurator = new BillConfigurator();
        String bill = billConfigurator.configureBill(purchases, cardDiscount,
                cardDiscountPercent, promoDiscount, totalForPay, date);
        if (BillTextWriter.writeBill(bill)) {
            session.setAttribute(AttributeName.SAVE_BILL, true);
        } else {
            session.setAttribute(AttributeName.SAVE_BILL, false);
        }
        Router router = new Router(PagePath.NOTIFICATION_PAGE);
        router.setRedirect();
        return router;
    }
}
