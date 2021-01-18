package ru.clevertec.bill.entity;

import java.util.StringJoiner;

public class SinglePurchase extends Entity {
    private Product product;
    private int quantity;
    private double total;
    private double promoDiscount;

    {
        promoDiscount = 0.0;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPromoDiscount() {
        return promoDiscount;
    }

    public void setPromoDiscount(double promoDiscount) {
        this.promoDiscount = promoDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SinglePurchase purchase = (SinglePurchase) o;
        if (quantity != purchase.quantity) {
            return false;
        }
        if (Double.compare(purchase.total, total) != 0) {
            return false;
        }
        if (Double.compare(purchase.promoDiscount, promoDiscount) != 0) {
            return false;
        }
        return product != null ? product.equals(purchase.product) : purchase.product == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = product != null ? product.hashCode() : 0;
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(promoDiscount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SinglePurchase.class.getSimpleName() + "[", "]")
                .add("product=" + product)
                .add("quantity=" + quantity)
                .add("total=" + total)
                .add("promoDiscount=" + promoDiscount)
                .toString();
    }
}
