package ru.clevertec.bill.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Order {
    private Map<Integer, Integer> purchaseParameters;
    private int curdNumber;

    {
        purchaseParameters = new HashMap<>();
        curdNumber = 0;
    }

    public Map<Integer, Integer> getPurchaseParameters() {
        return purchaseParameters;
    }

    public void setPurchaseParameters(Map<Integer, Integer> purchaseParameters) {
        this.purchaseParameters = purchaseParameters;
    }

    public int getCurdNumber() {
        return curdNumber;
    }

    public void setCurdNumber(int curdNumber) {
        this.curdNumber = curdNumber;
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
        if (curdNumber != order.curdNumber) {
            return false;
        }
        return purchaseParameters != null ? purchaseParameters.equals(order.purchaseParameters)
                : order.purchaseParameters == null;
    }

    @Override
    public int hashCode() {
        int result = purchaseParameters != null ? purchaseParameters.hashCode() : 0;
        result = 31 * result + curdNumber;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("purchaseParameters=" + purchaseParameters)
                .add("curdNumber=" + curdNumber)
                .toString();
    }
}
