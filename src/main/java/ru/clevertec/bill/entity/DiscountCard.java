package ru.clevertec.bill.entity;

import java.util.StringJoiner;

public class DiscountCard extends Entity {
    private long cardId;
    private int cardNumber;
    private double discount;

    public DiscountCard(int cardNumber, double discount) {
        this.cardNumber = cardNumber;
        this.discount = discount;
    }

    public DiscountCard() {
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiscountCard card = (DiscountCard) o;
        if (cardId != card.cardId) {
            return false;
        }
        if (cardNumber != card.cardNumber) {
            return false;
        }
        return Double.compare(card.discount, discount) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (cardId ^ (cardId >>> 32));
        result = 31 * result + cardNumber;
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DiscountCard.class.getSimpleName() + "[", "]")
                .add("cardId=" + cardId)
                .add("cardNumber=" + cardNumber)
                .add("discount=" + discount)
                .toString();
    }
}
