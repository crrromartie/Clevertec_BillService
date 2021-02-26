package ru.clevertec.bill.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.builder.OrderBuilder;
import ru.clevertec.bill.builder.impl.OrderBuilderImpl;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.*;
import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.BillService;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeBillCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(new StringBuilder()
                .append(request.getContextPath())
                .append(CommandPath.BILL_PASS).toString());
        router.setRedirect();
        HttpSession session = request.getSession();
        ProductService productService = ServiceFactory.getINSTANCE().getProductService();
        try {
            List<Product> products = productService.findAll();
            Map<Long, Integer> purchaseParameters = new HashMap<>();
            for (Product item : products) {
                if (!request.getParameter(String.valueOf(item.getProductId())).isBlank()) {
                    purchaseParameters.put(Long.parseLong(String.valueOf(item.getProductId())),
                            Integer.parseInt(request.getParameter(String.valueOf(item.getProductId()))));
                }
            }
            OrderBuilder orderBuilder = new OrderBuilderImpl();
            orderBuilder.setPurchaseParameters(purchaseParameters);
            if (!request.getParameter(ParameterName.CARD_NUMBER).isBlank()) {
                int cardNumber = Integer.parseInt(request.getParameter(ParameterName.CARD_NUMBER));
                orderBuilder.setCardNumber(cardNumber);
            }
            BillService billService = ServiceFactory.getINSTANCE().getBillService();
            Bill bill = billService.makeBill(orderBuilder.getOrder());
            session.setAttribute(AttributeName.BILL, bill);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
