package ru.clevertec.bill.controller.command.impl.navigation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ProductPassCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.PRODUCT_PAGE);
        HttpSession session = request.getSession();
        long productId = Long.parseLong(request.getParameter(ParameterName.PRODUCT_ID));
        ProductService productService = ServiceFactory.getINSTANCE().getProductService();
        try {
            Optional<Product> optionalProduct = productService.findById(productId);
            if (optionalProduct.isPresent()) {
                session.setAttribute(AttributeName.PRODUCT, optionalProduct.get());
            } else {
                logger.log(Level.WARN, "Product is not found");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
