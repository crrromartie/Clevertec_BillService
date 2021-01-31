package ru.clevertec.bill.entity;

import java.util.StringJoiner;

public class DiscountCard extends Entity {
    private long cardId;
    private int cardNumber;
    private int discountPercent;

    public DiscountCard() {
    }

    public DiscountCard(long cardId, int cardNumber, int discountPercent) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.discountPercent = discountPercent;
    }

    public long getCardId() {
        return cardId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        if (cardId != that.cardId) return false;
        if (cardNumber != that.cardNumber) return false;
        return discountPercent == that.discountPercent;
    }

    @Override
    public int hashCode() {
        int result = (int) (cardId ^ (cardId >>> 32));
        result = 31 * result + cardNumber;
        result = 31 * result + discountPercent;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DiscountCard.class.getSimpleName() + "[", "]")
                .add("cardId=" + cardId)
                .add("cardNumber=" + cardNumber)
                .add("discountPercent=" + discountPercent)
                .toString();
    }
}
