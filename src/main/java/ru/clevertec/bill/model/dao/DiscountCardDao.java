package ru.clevertec.bill.model.dao;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;

import java.util.Optional;

public interface DiscountCardDao extends Dao<DiscountCard> {
    Optional<DiscountCard> findCardByNumber(int cardNumber) throws DaoException;

    boolean add(DiscountCard card) throws DaoException;

    boolean delete(int cardNumber) throws DaoException;

    boolean changeDiscountPercent(int cardNumber, double discount) throws DaoException;
}
