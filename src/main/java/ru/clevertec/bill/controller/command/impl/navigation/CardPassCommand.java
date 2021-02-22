package ru.clevertec.bill.controller.command.impl.navigation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CardPassCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.CARD_PAGE);
        HttpSession session = request.getSession();
        long cardId = Long.parseLong(request.getParameter(ParameterName.CARD_ID));
        DiscountCardService discountCardService = ServiceFactory.getINSTANCE().getDiscountCardService();
        try {
            Optional<DiscountCard> optionalDiscountCard = discountCardService.findById(cardId);
            if (optionalDiscountCard.isPresent()) {
                session.setAttribute(AttributeName.CARD, optionalDiscountCard.get());
            } else {
                logger.log(Level.WARN, "Discount card is not found");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
