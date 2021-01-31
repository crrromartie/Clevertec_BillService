package ru.clevertec.bill.builder.impl;

import ru.clevertec.bill.builder.SinglePurchaseBuilder;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.entity.SinglePurchase;

import java.math.BigDecimal;

public class SinglePurchaseBuilderImpl implements SinglePurchaseBuilder {
    private long singlePurchaseId;
    private Product product;
    private int quantity;
    private BigDecimal total;
    private BigDecimal promoDiscount;

    @Override
    public void setSinglePurchaseId(long singlePurchaseId) {
        this.singlePurchaseId = singlePurchaseId;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public void setPromoDiscount(BigDecimal promoDiscount) {
        this.promoDiscount = promoDiscount;
    }

    @Override
    public SinglePurchase getSinglePurchase() {
        return new SinglePurchase(singlePurchaseId, product, quantity, total, promoDiscount);
    }
}
