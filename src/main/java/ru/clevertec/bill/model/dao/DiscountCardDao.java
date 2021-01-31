package ru.clevertec.bill.model.dao;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;

import java.util.Optional;

public interface DiscountCardDao extends Dao<DiscountCard> {

    void delete(int cardNumber) throws DaoException;

    Optional<DiscountCard> findByNumber(int cardNumber) throws DaoException;

    boolean changeDiscountPercent(long id, int discountPercent) throws DaoException;

    boolean changeDiscountPercent(int cardNumber, int discountPercent) throws DaoException;
}
