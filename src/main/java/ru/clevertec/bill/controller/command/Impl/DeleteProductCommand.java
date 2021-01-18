package ru.clevertec.bill.controller.command.Impl;

import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteProductCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String name = request.getParameter(ParameterName.PRODUCT_NAME);
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        ProductService productService = factory.getProductService();
        try {
            if (productService.delete(name)) {
                request.setAttribute(AttributeName.DELETE_PRODUCT, true);
                router.setPage(PagePath.NOTIFICATION_PAGE);
            } else {
                request.setAttribute(AttributeName.INCORRECT_PRODUCT_DATA, true);
                logger.log(Level.DEBUG, "Incorrect product data");
                router.setPage(PagePath.PRODUCT_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
