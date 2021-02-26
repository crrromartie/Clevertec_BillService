package ru.clevertec.bill.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.*;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AddProductCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Map<String, String> productParameters = fillProductParameters(request);
        ProductService productService = ServiceFactory.getINSTANCE().getProductService();
        try {
            if (productService.isProductNameUnique(productParameters.get(ParameterName.PRODUCT_NAME))) {
                Optional<Product> optionalProduct = productService.add(productParameters);
                if (optionalProduct.isPresent()) {
                    router.setPage(new StringBuilder()
                            .append(request.getContextPath())
                            .append(CommandPath.PRODUCTS_PASS).toString());
                    router.setRedirect();
                } else {
                    request.setAttribute(AttributeName.PRODUCT_PARAMETERS, productParameters);
                    request.setAttribute(AttributeName.INCORRECT_PRODUCT_DATA, true);
                    router.setPage(PagePath.NEW_PRODUCT_PAGE);
                }
            } else {
                request.setAttribute(AttributeName.PRODUCT_PARAMETERS, productParameters);
                request.setAttribute(AttributeName.UNIQUE_PRODUCT_NAME_ERROR, true);
                router.setPage(PagePath.NEW_PRODUCT_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }

    private Map<String, String> fillProductParameters(HttpServletRequest request) {
        String name = request.getParameter(ParameterName.PRODUCT_NAME);
        String price = request.getParameter(ParameterName.PRODUCT_PRICE);
        Map<String, String> productParameters = new HashMap<>();
        productParameters.put(ParameterName.PRODUCT_NAME, name);
        productParameters.put(ParameterName.PRODUCT_PRICE, price);
        return productParameters;
    }
}
