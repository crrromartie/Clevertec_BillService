package ru.clevertec.bill.controller.command.Impl;

import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddDiscountCardCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        int cardNumber = Integer.parseInt(request.getParameter(ParameterName.CARD_NUMBER));
        double discount = Double.parseDouble(request.getParameter(ParameterName.CARD_DISCOUNT));
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        DiscountCardService cardService = factory.getDiscountCardService();
        try {
            if (!cardService.isDiscountCardNumberUnique(cardNumber)) {
                logger.log(Level.DEBUG, "Not unique card number");
                request.setAttribute(AttributeName.CARD_PRODUCT_UNIQUE_ERROR, true);
                router.setPage(PagePath.CARD_PAGE);
            } else {
                DiscountCard card = new DiscountCard(cardNumber, discount);
                if (cardService.add(card)) {
                    request.setAttribute(AttributeName.ADD_CARD, true);
                    router.setPage(PagePath.NOTIFICATION_PAGE);
                } else {
                    request.setAttribute(AttributeName.INCORRECT_CARD_DATA, true);
                    logger.log(Level.DEBUG, "Incorrect card data");
                    router.setPage(PagePath.CARD_PAGE);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
