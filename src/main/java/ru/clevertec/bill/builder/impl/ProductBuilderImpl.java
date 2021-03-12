package ru.clevertec.bill.builder.impl;

import ru.clevertec.bill.builder.ProductBuilder;
import ru.clevertec.bill.entity.Product;

import java.math.BigDecimal;

public class ProductBuilderImpl implements ProductBuilder {
    private long productId;
    private String name;
    private BigDecimal price;
    private boolean promo;

    @Override
    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public void setPromo(boolean promo) {
        this.promo = promo;
    }

    @Override
    public Product getProduct() {
        return new Product(productId, name, price, promo);
    }
}
