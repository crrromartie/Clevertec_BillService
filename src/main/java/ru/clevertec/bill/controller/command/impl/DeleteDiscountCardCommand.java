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

public class DeleteDiscountCardCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(new StringBuilder()
                .append(request.getContextPath())
                .append(CommandPath.CARDS_PASS).toString());
        router.setRedirect();
        long cardId = Long.parseLong(request.getParameter(ParameterName.CARD_ID));
        DiscountCardService cardService = ServiceFactory.getINSTANCE().getDiscountCardService();
        try {
            cardService.delete(cardId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
