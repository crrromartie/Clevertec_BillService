package ru.clevertec.bill.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.*;
import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EditCardCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        DiscountCard card = (DiscountCard) session.getAttribute(AttributeName.CARD);
        Map<String, String> editDiscountCardParameters = fillEditDiscountCardParameters(request);
        DiscountCardService discountCardService = ServiceFactory.getINSTANCE().getDiscountCardService();
        try {
            if (discountCardService.isDiscountCardNumberUnique(editDiscountCardParameters
                    .get(ParameterName.CARD_NUMBER))
                    || String.valueOf(card.getCardNumber()).equals(editDiscountCardParameters
                    .get(ParameterName.CARD_NUMBER))) {
                Optional<DiscountCard> optionalDiscountCard = discountCardService
                        .edit(editDiscountCardParameters, card.getCardId());
                if (optionalDiscountCard.isPresent()) {
                    router.setPage(new StringBuilder()
                            .append(request.getContextPath())
                            .append(CommandPath.CARDS_PASS).toString());
                    router.setRedirect();
                } else {
                    request.setAttribute(AttributeName.INCORRECT_CARD_DATA, true);
                    router.setPage(PagePath.CARD_PAGE);
                }
            } else {
                request.setAttribute(AttributeName.UNIQUE_CARD_NUMBER_ERROR, true);
                router.setPage(PagePath.CARD_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }

        return router;
    }

    private Map<String, String> fillEditDiscountCardParameters(HttpServletRequest request) {
        String cardNumber = request.getParameter(ParameterName.CARD_NUMBER);
        String discountPercent = request.getParameter(ParameterName.CARD_DISCOUNT_PERCENT);
        Map<String, String> editDiscountCardParameters = new HashMap<>();
        editDiscountCardParameters.put(ParameterName.CARD_NUMBER, cardNumber);
        editDiscountCardParameters.put(ParameterName.CARD_DISCOUNT_PERCENT, discountPercent);
        return editDiscountCardParameters;
    }
}
