package ru.clevertec.bill.builder;

import ru.clevertec.bill.entity.DiscountCard;

public interface DiscountCardBuilder {

    void setCardId(long cardId);

    void setCardNumber(int cardNumber);

    void setDiscountPercent(int discountPercent);

    DiscountCard getDiscountCard();
}
