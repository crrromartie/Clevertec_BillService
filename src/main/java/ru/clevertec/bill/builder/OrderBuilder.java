package ru.clevertec.bill.builder;

import ru.clevertec.bill.entity.Order;

import java.util.Map;

public interface OrderBuilder {

    void setOrderId(long orderId);

    void setPurchaseParameters(Map<Long, Integer> purchaseParameters);

    void setCardNumber(int cardNumber);

    Order getOrder();
}
