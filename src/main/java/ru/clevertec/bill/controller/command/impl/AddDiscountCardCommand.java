package ru.clevertec.bill.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.*;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AddDiscountCardCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Map<String, String> cardParameters = fillCardParameters(request);
        DiscountCardService cardService = ServiceFactory.getINSTANCE().getDiscountCardService();
        try {
            if (cardService.isDiscountCardNumberUnique(cardParameters.get(ParameterName.CARD_NUMBER))) {
                if (cardService.add(cardParameters)) {
                    router.setPage(new StringBuilder()
                            .append(request.getContextPath())
                            .append(CommandPath.CARDS_PASS).toString());
                    router.setRedirect();
                } else {
                    request.setAttribute(AttributeName.CARD_PARAMETERS, cardParameters);
                    request.setAttribute(AttributeName.INCORRECT_CARD_DATA, true);
                    router.setPage(PagePath.NEW_CARD_PAGE);
                }
            } else {
                request.setAttribute(AttributeName.CARD_PARAMETERS, cardParameters);
                request.setAttribute(AttributeName.UNIQUE_CARD_NUMBER_ERROR, true);
                router.setPage(PagePath.NEW_CARD_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }

    private Map<String, String> fillCardParameters(HttpServletRequest request) {
        String cardNumber = request.getParameter(ParameterName.CARD_NUMBER);
        String discountCardPercent = request.getParameter(ParameterName.CARD_DISCOUNT_PERCENT);
        Map<String, String> cardParameters = new HashMap<>();
        cardParameters.put(ParameterName.CARD_NUMBER, cardNumber);
        cardParameters.put(ParameterName.CARD_DISCOUNT_PERCENT, discountCardPercent);
        return cardParameters;
    }
}
