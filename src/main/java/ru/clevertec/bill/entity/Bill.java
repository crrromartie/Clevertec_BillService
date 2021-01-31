package ru.clevertec.bill.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.StringJoiner;

public class Bill extends Entity {
    private long billId;
    private List<SinglePurchase> singlePurchases;
    private BigDecimal cardDiscount;
    private int cardDiscountPercent;
    private BigDecimal promoDiscount;
    private BigDecimal totalForPay;
    private String date;

    public Bill() {
    }

    public Bill(long billId, List<SinglePurchase> singlePurchases,
                BigDecimal cardDiscount,
                int cardDiscountPercent,
                BigDecimal promoDiscount,
                BigDecimal totalForPay,
                String date) {
        this.billId = billId;
        this.singlePurchases = singlePurchases;
        this.cardDiscount = cardDiscount;
        this.cardDiscountPercent = cardDiscountPercent;
        this.promoDiscount = promoDiscount;
        this.totalForPay = totalForPay;
        this.date = date;
    }

    public long getBillId() {
        return billId;
    }

    public List<SinglePurchase> getSinglePurchases() {
        return singlePurchases;
    }

    public BigDecimal getCardDiscount() {
        return cardDiscount;
    }

    public int getCardDiscountPercent() {
        return cardDiscountPercent;
    }

    public BigDecimal getPromoDiscount() {
        return promoDiscount;
    }

    public BigDecimal getTotalForPay() {
        return totalForPay;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bill bill = (Bill) o;
        if (billId != bill.billId) {
            return false;
        }
        if (cardDiscountPercent != bill.cardDiscountPercent) {
            return false;
        }
        if (singlePurchases != null ? !singlePurchases.equals(bill.singlePurchases) : bill.singlePurchases != null) {
            return false;
        }
        if (cardDiscount != null ? !cardDiscount.equals(bill.cardDiscount) : bill.cardDiscount != null) {
            return false;
        }
        if (promoDiscount != null ? !promoDiscount.equals(bill.promoDiscount) : bill.promoDiscount != null) {
            return false;
        }
        if (totalForPay != null ? !totalForPay.equals(bill.totalForPay) : bill.totalForPay != null) {
            return false;
        }
        return date != null ? date.equals(bill.date) : bill.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (billId ^ (billId >>> 32));
        result = 31 * result + (singlePurchases != null ? singlePurchases.hashCode() : 0);
        result = 31 * result + (cardDiscount != null ? cardDiscount.hashCode() : 0);
        result = 31 * result + cardDiscountPercent;
        result = 31 * result + (promoDiscount != null ? promoDiscount.hashCode() : 0);
        result = 31 * result + (totalForPay != null ? totalForPay.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Bill.class.getSimpleName() + "[", "]")
                .add("billId=" + billId)
                .add("singlePurchases=" + singlePurchases)
                .add("cardDiscount=" + cardDiscount)
                .add("cardDiscountPercent=" + cardDiscountPercent)
                .add("promoDiscount=" + promoDiscount)
                .add("totalForPay=" + totalForPay)
                .add("date='" + date + "'")
                .toString();
    }
}
