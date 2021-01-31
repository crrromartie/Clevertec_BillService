package ru.clevertec.bill.builder.impl;

import ru.clevertec.bill.builder.BillBuilder;
import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.entity.SinglePurchase;

import java.math.BigDecimal;
import java.util.List;

public class BillBuilderImpl implements BillBuilder {
    private long billId;
    private List<SinglePurchase> singlePurchases;
    private BigDecimal cardDiscount;
    private int cardDiscountPercent;
    private BigDecimal promoDiscount;
    private BigDecimal totalForPay;
    private String date;

    @Override
    public void setBillId(long billId) {
        this.billId = billId;
    }

    @Override
    public void setSinglePurchases(List<SinglePurchase> singlePurchases) {
        this.singlePurchases = singlePurchases;
    }

    @Override
    public void setCardDiscount(BigDecimal cardDiscount) {
        this.cardDiscount = cardDiscount;
    }

    @Override
    public void setCardDiscountPercent(int cardDiscountPercent) {
        this.cardDiscountPercent = cardDiscountPercent;
    }

    @Override
    public void setPromoDiscount(BigDecimal promoDiscount) {
        this.promoDiscount = promoDiscount;
    }

    @Override
    public void setTotalForPay(BigDecimal totalForPay) {
        this.totalForPay = totalForPay;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public Bill getBill() {
        return new Bill(billId,
                singlePurchases,
                cardDiscount,
                cardDiscountPercent,
                promoDiscount,
                totalForPay,
                date);
    }
}
