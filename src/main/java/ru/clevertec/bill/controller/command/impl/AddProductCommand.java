package ru.clevertec.bill.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.builder.ProductBuilder;
import ru.clevertec.bill.builder.impl.ProductBuilderImpl;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.model.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class AddProductCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String name = request.getParameter(ParameterName.PRODUCT_NAME);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter(ParameterName.PRODUCT_PRICE)));
        ProductService productService = ProductServiceImpl.getINSTANCE().getProductService();
        ProductBuilder productBuilder = new ProductBuilderImpl();
        productBuilder.setName(name);
        productBuilder.setPrice(price);
        try {
            if (productService.add(productBuilder.getProduct())) {
                request.setAttribute(AttributeName.ADD_PRODUCT, true);
                router.setPage(PagePath.NOTIFICATION_PAGE);
            } else {
                request.setAttribute(AttributeName.INCORRECT_PRODUCT_DATA, true);
                router.setPage(PagePath.PRODUCT_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
