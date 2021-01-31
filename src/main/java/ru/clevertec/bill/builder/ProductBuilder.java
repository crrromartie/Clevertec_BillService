package ru.clevertec.bill.builder;

import ru.clevertec.bill.entity.Product;

import java.math.BigDecimal;

public interface ProductBuilder {

    void setProductId(long productId);

    void setName(String name);

    void setPrice(BigDecimal price);

    void setPromo(boolean promo);

    Product getProduct();
}
