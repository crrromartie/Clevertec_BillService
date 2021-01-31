package ru.clevertec.bill.builder;

import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.entity.SinglePurchase;

import java.math.BigDecimal;
import java.util.List;

public interface BillBuilder {

    void setBillId(long billId);

    void setSinglePurchases(List<SinglePurchase> singlePurchases);

    void setCardDiscount(BigDecimal cardDiscount);

    void setCardDiscountPercent(int cardDiscountPercent);

    void setPromoDiscount(BigDecimal promoDiscount);

    void setTotalForPay(BigDecimal totalForPay);

    void setDate(String date);

    Bill getBill();
}
