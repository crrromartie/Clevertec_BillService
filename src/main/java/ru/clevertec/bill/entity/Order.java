package ru.clevertec.bill.entity;

import java.util.Map;
import java.util.StringJoiner;

public class Order extends Entity {
    private long orderId;
    private Map<Long, Integer> purchaseParameters;
    private int cardNumber;

    public Order() {
    }

    public Order(long orderId, Map<Long, Integer> purchaseParameters, int cardNumber) {
        this.orderId = orderId;
        this.purchaseParameters = purchaseParameters;
        this.cardNumber = cardNumber;
    }

    public long getOrderId() {
        return orderId;
    }

    public Map<Long, Integer> getPurchaseParameters() {
        return purchaseParameters;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setPurchaseParameters(Map<Long, Integer> purchaseParameters) {
        this.purchaseParameters = purchaseParameters;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        if (orderId != order.orderId) {
            return false;
        }
        if (cardNumber != order.cardNumber) {
            return false;
        }
        return purchaseParameters != null ? purchaseParameters.equals(order.purchaseParameters) : order.purchaseParameters == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (purchaseParameters != null ? purchaseParameters.hashCode() : 0);
        result = 31 * result + cardNumber;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("orderId=" + orderId)
                .add("purchaseParameters=" + purchaseParameters)
                .add("cardNumber=" + cardNumber)
                .toString();
    }
}
