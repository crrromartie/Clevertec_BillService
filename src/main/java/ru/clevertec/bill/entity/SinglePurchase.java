package ru.clevertec.bill.entity;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class SinglePurchase extends Entity {
    private long singlePurchaseId;
    private Product product;
    private int quantity;
    private BigDecimal total;
    private BigDecimal promoDiscount;

    public SinglePurchase() {
    }

    public SinglePurchase(long singlePurchaseId,
                          Product product,
                          int quantity,
                          BigDecimal total,
                          BigDecimal promoDiscount) {
        this.singlePurchaseId = singlePurchaseId;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
        this.promoDiscount = promoDiscount;
    }

    public long getSinglePurchaseId() {
        return singlePurchaseId;
    }

    public void setSinglePurchaseId(long singlePurchaseId) {
        this.singlePurchaseId = singlePurchaseId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getPromoDiscount() {
        return promoDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SinglePurchase that = (SinglePurchase) o;
        if (singlePurchaseId != that.singlePurchaseId) {
            return false;
        }
        if (quantity != that.quantity) {
            return false;
        }
        if (product != null ? !product.equals(that.product) : that.product != null) {
            return false;
        }
        if (total != null ? !total.equals(that.total) : that.total != null) {
            return false;
        }
        return promoDiscount != null ? promoDiscount.equals(that.promoDiscount) : that.promoDiscount == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (singlePurchaseId ^ (singlePurchaseId >>> 32));
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (promoDiscount != null ? promoDiscount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SinglePurchase.class.getSimpleName() + "[", "]")
                .add("singlePurchaseId=" + singlePurchaseId)
                .add("product=" + product)
                .add("quantity=" + quantity)
                .add("total=" + total)
                .add("promoDiscount=" + promoDiscount)
                .toString();
    }
}
