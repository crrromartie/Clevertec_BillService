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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EditProductCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute(AttributeName.PRODUCT);
        Map<String, String> editProductParameters = fillEditProductParameters(request);
        ProductService productService = ServiceFactory.getINSTANCE().getProductService();
        try {
            if (productService.isProductNameUnique(editProductParameters.get(ParameterName.PRODUCT_NAME))
                    || product.getName().equals(editProductParameters.get(ParameterName.PRODUCT_NAME))) {
                Optional<Product> optionalProduct = productService.edit(editProductParameters, product.getProductId());
                if (optionalProduct.isPresent()) {
                    router.setPage(new StringBuilder()
                            .append(request.getContextPath())
                            .append(CommandPath.PRODUCTS_PASS).toString());
                    router.setRedirect();
                } else {
                    request.setAttribute(AttributeName.INCORRECT_PRODUCT_DATA, true);
                    router.setPage(PagePath.PRODUCT_PAGE);
                }
            } else {
                request.setAttribute(AttributeName.UNIQUE_PRODUCT_NAME_ERROR, true);
                router.setPage(PagePath.PRODUCT_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }

    private Map<String, String> fillEditProductParameters(HttpServletRequest request) {
        String name = request.getParameter(ParameterName.PRODUCT_NAME);
        String price = request.getParameter(ParameterName.PRODUCT_PRICE);
        String promo = request.getParameter(ParameterName.PRODUCT_PROMO);
        Map<String, String> editProductParameters = new HashMap<>();
        editProductParameters.put(ParameterName.PRODUCT_NAME, name);
        editProductParameters.put(ParameterName.PRODUCT_PRICE, price);
        editProductParameters.put(ParameterName.PRODUCT_PROMO, promo);
        return editProductParameters;
    }
}
