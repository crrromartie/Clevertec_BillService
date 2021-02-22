package ru.clevertec.bill.model.dao;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;

import java.util.Optional;

public interface DiscountCardDao extends Dao<DiscountCard> {

    Optional<DiscountCard> findByNumber(int cardNumber) throws DaoException;
}
