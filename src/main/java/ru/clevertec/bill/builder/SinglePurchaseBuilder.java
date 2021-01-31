package ru.clevertec.bill.builder;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.entity.SinglePurchase;

import java.math.BigDecimal;

public interface SinglePurchaseBuilder {

    void setSinglePurchaseId(long singlePurchaseId);

    void setProduct(Product product);

    void setQuantity(int quantity);

    void setTotal(BigDecimal total);

    void setPromoDiscount(BigDecimal promoDiscount);

    SinglePurchase getSinglePurchase();
}
