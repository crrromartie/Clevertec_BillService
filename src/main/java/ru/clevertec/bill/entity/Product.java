package ru.clevertec.bill.entity;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Product extends Entity {
    private long productId;
    private String name;
    private BigDecimal price;
    private boolean promo;

    public Product() {
    }

    public Product(long productId,
                   String name,
                   BigDecimal price,
                   boolean promo) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.promo = promo;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isPromo() {
        return promo;
    }

    public void setPromo(boolean promo) {
        this.promo = promo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        if (productId != product.productId) {
            return false;
        }
        if (promo != product.promo) {
            return false;
        }
        if (name != null ? !name.equals(product.name) : product.name != null) {
            return false;
        }
        return price != null ? price.equals(product.price) : product.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (promo ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("productId=" + productId)
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("promo=" + promo)
                .toString();
    }
}
