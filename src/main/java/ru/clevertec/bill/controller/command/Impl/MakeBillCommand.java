package ru.clevertec.bill.controller.command.Impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.collection.CustomArrayList;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.controller.command.Command;
import ru.clevertec.bill.controller.command.PagePath;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.entity.SinglePurchase;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MakeBillCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private ServiceFactory factory = ServiceFactory.getINSTANCE();
    private ProductService productService = factory.getProductService();
    private DiscountCardService cardService = factory.getDiscountCardService();

    private static final String DATE_FORMAT = "dd/MM/yyyy E HH:mm:ss";
    private static final double DISCOUNT_FOR_PROMO = 0.1;
    private static final int QUANTITY_FOR_PROMO = 5;
    private static final int PERCENT_100 = 100;
    private static final double KOEF_1 = 1.0;

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        Map<Integer, Integer> purchaseParameters = new HashMap<>();
        List<Product> products = null;
        try {
            products = productService.findAll();
            for (Product item : products) {
                if (!request.getParameter(String.valueOf(item.getProductId())).isBlank()) {
                    purchaseParameters.put(Integer.parseInt(String.valueOf(item.getProductId())),
                            Integer.parseInt(request.getParameter(String.valueOf(item.getProductId()))));
                }
            }

            List<SinglePurchase> purchases = createSinglePurchases(purchaseParameters);
            session.setAttribute(AttributeName.PURCHASES, purchases);

            double totalForPay = totalForPay(purchases);
            session.setAttribute(AttributeName.TOTAL_FOR_PAY, totalForPay);

            double totalPromoDiscount = totalPromoDiscountCalculate(purchases);
            session.setAttribute(AttributeName.TOTAL_PROMO_DISCOUNT, totalPromoDiscount);

            int cardNumber = 0;
            if (!request.getParameter(ParameterName.CARD_NUMBER).isBlank()) {
                cardNumber = Integer.parseInt(request.getParameter(ParameterName.CARD_NUMBER));
            }
            double cardDiscountPercent = defineCardDiscountPercent(cardNumber);
            session.setAttribute(AttributeName.CARD_DISCOUNT_PERCENT, (cardDiscountPercent * PERCENT_100));

            double cardDiscount = calculateCardDiscount(totalForPay, cardDiscountPercent);
            session.setAttribute(AttributeName.CARD_DISCOUNT, cardDiscount);

            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            session.setAttribute(AttributeName.DATE, dateFormat.format(new Date()));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        router.setPage(PagePath.BILL_PAGE);
        router.setRedirect();
        return router;
    }

    private List<SinglePurchase> createSinglePurchases(Map<Integer, Integer> purchaseParameters) throws ServiceException {
        List<SinglePurchase> singlePurchaseList = new CustomArrayList<>();
        for (Map.Entry<Integer, Integer> item : purchaseParameters.entrySet()) {
            Optional<Product> optionalProduct = productService.findProductById(item.getKey());
            if (optionalProduct.isPresent()) {
                SinglePurchase singlePurchase = new SinglePurchase();
                singlePurchase.setProduct(optionalProduct.get());
                singlePurchase.setQuantity(item.getValue());
                double total = totalCalculate(optionalProduct.get(), item.getValue());
                singlePurchase.setTotal(total);
                double promoDiscount = promoDiscountCalculate(optionalProduct.get(), item.getValue());
                singlePurchase.setPromoDiscount(promoDiscount);
                singlePurchaseList.add(singlePurchase);
            }
        }
        return singlePurchaseList;
    }

    private double totalCalculate(Product product, int quantity) {
        double total = 0.0;
        if (product.isPromotional() && quantity >= QUANTITY_FOR_PROMO) {
            total = (quantity * product.getPrice()) * (KOEF_1 - DISCOUNT_FOR_PROMO);
        } else {
            total = quantity * product.getPrice();
        }
        return total;
    }

    private double promoDiscountCalculate(Product product, int quantity) {
        double promoDiscount = 0.0;
        if (!product.isPromotional() || quantity < QUANTITY_FOR_PROMO) {
            return promoDiscount;
        } else {
            double costWithoutDiscount = quantity * product.getPrice();
            double costWithDiscount = totalCalculate(product, quantity);
            promoDiscount = costWithoutDiscount - costWithDiscount;
        }
        return promoDiscount;
    }

    private double totalPromoDiscountCalculate(List<SinglePurchase> purchases) {
        double totalPromoDiscount = 0.0;
        for (SinglePurchase singlePurchase : purchases) {
            totalPromoDiscount += singlePurchase.getPromoDiscount();
        }
        return totalPromoDiscount;
    }

    private double totalForPay(List<SinglePurchase> purchases) {
        double totalForPay = 0.0;
        for (SinglePurchase singlePurchase : purchases) {
            totalForPay += singlePurchase.getTotal();
        }
        return totalForPay;
    }

    private double defineCardDiscountPercent(int curdNumber) throws ServiceException {
        double cardDiscountPercent = 0.0;
        Optional<DiscountCard> discountCard = cardService.findCardByNumber(curdNumber);
        if (discountCard.isPresent()) {
            cardDiscountPercent = discountCard.get().getDiscount();
        }
        return cardDiscountPercent;
    }

    private double calculateCardDiscount(double totalForPay, double cardDiscountPercent) {
        double cardDiscount = 0.0;
        if (cardDiscountPercent > 0.0) {
            cardDiscount = totalForPay * cardDiscountPercent;
        }
        return cardDiscount;
    }
}
