package ru.clevertec.bill.model.service.impl;

import ru.clevertec.bill.builder.BillBuilder;
import ru.clevertec.bill.builder.SinglePurchaseBuilder;
import ru.clevertec.bill.builder.impl.BillBuilderImpl;
import ru.clevertec.bill.builder.impl.SinglePurchaseBuilderImpl;
import ru.clevertec.bill.collection.CustomLinkedList;
import ru.clevertec.bill.entity.*;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.BillService;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.ProductService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class BillServiceImplementation implements BillService {
    private static final String DATE_FORMAT = "dd/MM/yyyy E HH:mm:ss";
    private static final int PERCENT_100 = 100;
    private static final int SCALE = 2;

    private static final int DISCOUNT_PERCENT_PROMO = 10;
    private static final int QUANTITY_FOR_PROMO = 5;

    @Override
    public Bill makeBill(Order order) throws ServiceException {
        List<SinglePurchase> singlePurchaseList = createSinglePurchases(order);
        int cardDiscountPercent = defineCardDiscountPercent(order.getCardNumber());
        BigDecimal totalForPay = calculateTotalForPay(singlePurchaseList);
        BigDecimal promoDiscount = calculateTotalPromoDiscount(singlePurchaseList);
        BigDecimal cardDiscount = calculateCardDiscount(totalForPay, cardDiscountPercent);
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        BigDecimal totalForPayFinal = calculateTotalForPayFinal(totalForPay, cardDiscount);

        BillBuilder billBuilder = new BillBuilderImpl();
        billBuilder.setSinglePurchases(singlePurchaseList);
        billBuilder.setCardDiscountPercent(cardDiscountPercent);
        billBuilder.setPromoDiscount(promoDiscount);
        billBuilder.setCardDiscount(cardDiscount);
        billBuilder.setDate(dateFormat.format(new Date()));
        billBuilder.setTotalForPay(totalForPayFinal);
        return billBuilder.getBill();
    }

    private List<SinglePurchase> createSinglePurchases(Order order) throws ServiceException {
        ProductService productService = ProductServiceImpl.getINSTANCE().getProductService();
        List<SinglePurchase> singlePurchaseList = new CustomLinkedList<>();
        for (Map.Entry<Long, Integer> item : order.getPurchaseParameters().entrySet()) {
            Optional<Product> optionalProduct = productService.findById(item.getKey());
            if (optionalProduct.isPresent()) {
                BigDecimal total = calculateTotal(optionalProduct.get(), item.getValue());
                BigDecimal promoDiscount = calculatePromoDiscount(optionalProduct.get(), item.getValue());
                SinglePurchaseBuilder singlePurchaseBuilder = new SinglePurchaseBuilderImpl();
                singlePurchaseBuilder.setProduct(optionalProduct.get());
                singlePurchaseBuilder.setQuantity(item.getValue());
                singlePurchaseBuilder.setTotal(total);
                singlePurchaseBuilder.setPromoDiscount(promoDiscount);
                singlePurchaseList.add(singlePurchaseBuilder.getSinglePurchase());
            }
        }
        return singlePurchaseList;
    }

    private BigDecimal calculateTotal(Product product, int quantity) {
        BigDecimal total;
        if (product.isPromo() && quantity >= QUANTITY_FOR_PROMO) {
            total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            BigDecimal discount = new BigDecimal(DISCOUNT_PERCENT_PROMO)
                    .divide(BigDecimal.valueOf(PERCENT_100), SCALE, RoundingMode.HALF_UP);
            discount = discount.multiply(total);
            total = total.subtract(discount);
        } else {
            total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        }
        return total;
    }

    private BigDecimal calculatePromoDiscount(Product product, int quantity) {
        BigDecimal promoDiscount;
        if (product.isPromo() && quantity >= QUANTITY_FOR_PROMO) {
            BigDecimal costWithoutDiscount = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            BigDecimal costWithDiscount = calculateTotal(product, quantity);
            promoDiscount = costWithoutDiscount.subtract(costWithDiscount);
        } else {
            promoDiscount = BigDecimal.valueOf(0.0);
        }
        return promoDiscount;
    }

    private BigDecimal calculateTotalPromoDiscount(List<SinglePurchase> purchases) {
        BigDecimal totalPromoDiscount = BigDecimal.valueOf(0.0);
        for (SinglePurchase singlePurchase : purchases) {
            totalPromoDiscount = totalPromoDiscount.add(singlePurchase.getPromoDiscount());
        }
        return totalPromoDiscount;
    }

    private BigDecimal calculateTotalForPay(List<SinglePurchase> purchases) {
        BigDecimal totalForPay = BigDecimal.valueOf(0.0);
        for (SinglePurchase singlePurchase : purchases) {
            totalForPay = totalForPay.add(singlePurchase.getTotal());
        }
        return totalForPay;
    }

    private int defineCardDiscountPercent(int curdNumber) throws ServiceException {
        DiscountCardService cardService = DiscountCardServiceImpl.getINSTANCE().getDiscountCardService();
        Optional<DiscountCard> discountCard = cardService.findByNumber(curdNumber);
        int cardDiscountPercent = 0;
        if (discountCard.isPresent()) {
            cardDiscountPercent = discountCard.get().getDiscountPercent();
        }
        return cardDiscountPercent;
    }

    private BigDecimal calculateCardDiscount(BigDecimal totalForPay, int cardDiscountPercent) {
        BigDecimal cardDiscount = BigDecimal.valueOf(0.0);
        if (cardDiscountPercent > 0) {
            cardDiscount = totalForPay.multiply(BigDecimal.valueOf(cardDiscountPercent));
            cardDiscount = cardDiscount.divide(BigDecimal.valueOf(PERCENT_100), SCALE, RoundingMode.HALF_UP);
        }
        return cardDiscount;
    }

    private BigDecimal calculateTotalForPayFinal(BigDecimal totalForPay, BigDecimal cardDiscountCalculate) {
        return totalForPay.subtract(cardDiscountCalculate);
    }
}
