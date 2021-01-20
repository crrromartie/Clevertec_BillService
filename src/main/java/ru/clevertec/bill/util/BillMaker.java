package ru.clevertec.bill.util;

import ru.clevertec.bill.collection.CustomLinkedList;
import ru.clevertec.bill.entity.Order;
import ru.clevertec.bill.entity.SinglePurchase;
import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.model.service.ServiceFactory;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public final class BillMaker {
    private ProductService productService;
    private DiscountCardService cardService;
    private BillConfigurator billConfigurator;

    private static final String DATE_FORMAT = "dd/MM/yyyy E HH:mm:ss";
    private static final double DISCOUNT_FOR_PROMO = 0.1;
    private static final int QUANTITY_FOR_PROMO = 5;
    private static final int PERCENT_100 = 100;
    private static final double KOEF_1 = 1.0;

    {
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        productService = factory.getProductService();
        cardService = factory.getDiscountCardService();
        billConfigurator = new BillConfigurator();
    }

    public String makeBill(Order order) {
        String bill = null;
        try {
            List<SinglePurchase> singlePurchaseList = createSinglePurchases(order);
            double cardDiscountPercent = cardDiscountPercentDefine(order);
            double totalForPay = totalForPayCalculate(singlePurchaseList);
            double promoDiscount = totalPromoDiscountCalculate(singlePurchaseList);
            double cardDiscount = cardDiscountCalculate(totalForPay, cardDiscountPercent);
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            bill = billConfigurator.configureBill(singlePurchaseList, cardDiscount, (cardDiscountPercent * PERCENT_100),
                    promoDiscount, totalForPay, dateFormat.format(new Date()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return bill;
    }

    public ByteArrayOutputStream makePDFBill(Order order) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            List<SinglePurchase> singlePurchaseList = createSinglePurchases(order);
            double cardDiscountPercent = cardDiscountPercentDefine(order);
            double totalForPay = totalForPayCalculate(singlePurchaseList);
            double promoDiscount = totalPromoDiscountCalculate(singlePurchaseList);
            double cardDiscount = cardDiscountCalculate(totalForPay, cardDiscountPercent);
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            byteArrayOutputStream = billConfigurator.configurePDFBill(singlePurchaseList, cardDiscount,
                    (cardDiscountPercent * PERCENT_100), promoDiscount, totalForPay, dateFormat.format(new Date()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream;
    }

    private List<SinglePurchase> createSinglePurchases(Order order) throws ServiceException {
        List<SinglePurchase> singlePurchaseList = new CustomLinkedList<>();
        for (Map.Entry<Integer, Integer> item : order.getPurchaseParameters().entrySet()) {
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

    private double totalForPayCalculate(List<SinglePurchase> purchases) {
        double totalForPay = 0.0;
        for (SinglePurchase singlePurchase : purchases) {
            totalForPay += singlePurchase.getTotal();
        }
        return totalForPay;
    }

    private double cardDiscountPercentDefine(Order order) throws ServiceException {
        double cardDiscountPercent = 0.0;
        Optional<DiscountCard> discountCard = cardService.findCardByNumber(order.getCurdNumber());
        if (discountCard.isPresent()) {
            cardDiscountPercent = discountCard.get().getDiscount();
        }
        return cardDiscountPercent;
    }

    private double cardDiscountCalculate(double totalForPay, double cardDiscountPercent) {
        double cardDiscount = 0.0;
        if (cardDiscountPercent > 0.0) {
            cardDiscount = totalForPay * cardDiscountPercent;
        }
        return cardDiscount;
    }
}

