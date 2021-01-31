package ru.clevertec.bill.builder.impl;

import ru.clevertec.bill.builder.OrderBuilder;
import ru.clevertec.bill.entity.Order;

import java.util.Map;

public class OrderBuilderImpl implements OrderBuilder {
    private long orderId;
    private Map<Long, Integer> purchaseParameters;
    private int cardNumber;

    @Override
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public void setPurchaseParameters(Map<Long, Integer> purchaseParameters) {
        this.purchaseParameters = purchaseParameters;
    }

    @Override
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public Order getOrder() {
        return new Order(orderId, purchaseParameters, cardNumber);
    }


}
