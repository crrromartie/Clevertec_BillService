package ru.clevertec.bill.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.builder.DiscountCardBuilder;
import ru.clevertec.bill.builder.impl.DiscountCardBuilderImpl;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.impl.DiscountCardServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class AddDiscountCardCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        int cardNumber = Integer.parseInt(request.getParameter(ParameterName.CARD_NUMBER));
        int discountPercent = Integer.parseInt(request.getParameter(ParameterName.CARD_DISCOUNT_PERCENT));
        DiscountCardService cardService = DiscountCardServiceImpl.getINSTANCE().getDiscountCardService();
        DiscountCardBuilder discountCardBuilder = new DiscountCardBuilderImpl();
        discountCardBuilder.setCardNumber(cardNumber);
        discountCardBuilder.setDiscountPercent(discountPercent);
        try {
            if (cardService.add(discountCardBuilder.getDiscountCard())) {
                request.setAttribute(AttributeName.ADD_CARD, true);
                router.setPage(PagePath.NOTIFICATION_PAGE);
            } else {
                request.setAttribute(AttributeName.INCORRECT_CARD_DATA, true);
                router.setPage(PagePath.CARD_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
