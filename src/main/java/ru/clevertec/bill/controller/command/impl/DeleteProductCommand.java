package ru.clevertec.bill.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.CommandPath;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class DeleteProductCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(new StringBuilder()
                .append(request.getContextPath())
                .append(CommandPath.PRODUCTS_PASS).toString());
        router.setRedirect();
        long productId = Long.parseLong(request.getParameter(ParameterName.PRODUCT_ID));
        ProductService productService = ServiceFactory.getINSTANCE().getProductService();
        try {
            productService.delete(productId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
