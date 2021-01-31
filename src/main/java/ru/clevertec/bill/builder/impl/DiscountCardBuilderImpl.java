package ru.clevertec.bill.builder.impl;

import ru.clevertec.bill.builder.DiscountCardBuilder;
import ru.clevertec.bill.entity.DiscountCard;

public class DiscountCardBuilderImpl implements DiscountCardBuilder {
    private long cardId;
    private int cardNumber;
    private int discountPercent;

    @Override
    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    @Override
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public DiscountCard getDiscountCard() {
        return new DiscountCard(cardId, cardNumber, discountPercent);
    }
}
