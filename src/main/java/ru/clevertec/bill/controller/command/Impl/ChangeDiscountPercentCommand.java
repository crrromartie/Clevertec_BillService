package ru.clevertec.bill.controller.command.Impl;

import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeDiscountPercentCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        int cardNumber = Integer.parseInt(request.getParameter(ParameterName.CARD_NUMBER));
        double discount = Double.parseDouble(request.getParameter(ParameterName.CARD_DISCOUNT));
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        DiscountCardService cardService = factory.getDiscountCardService();
        try {
            if (cardService.changeDiscountPercent(cardNumber, discount)) {
                request.setAttribute(AttributeName.CHANGE_DISCOUNT, true);
                router.setPage(PagePath.NOTIFICATION_PAGE);
            } else {
                request.setAttribute(AttributeName.INCORRECT_CARD_DATA, true);
                logger.log(Level.DEBUG, "Incorrect card data");
                router.setPage(PagePath.CARD_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
