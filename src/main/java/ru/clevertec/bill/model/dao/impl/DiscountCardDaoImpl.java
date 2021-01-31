package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.model.dao.DiscountCardDao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DiscountCardDaoImpl implements DiscountCardDao {
    private final DiscountCardDaoImplementation discountCardDao;

    public DiscountCardDaoImpl() {
        this.discountCardDao = new DiscountCardDaoImplementation();
    }

    @Override
    public void setConnection(Connection connection) {
        discountCardDao.setConnection(connection);
    }

    @Override
    public List<DiscountCard> findAll() throws DaoException {
        return discountCardDao.findAll();
    }

    @Override
    public Optional<DiscountCard> findById(long id) throws DaoException {
        return discountCardDao.findById(id);
    }

    @Override
    public boolean add(DiscountCard card) throws DaoException {
        return discountCardDao.add(card);
    }

    @Override
    public void delete(long id) throws DaoException {
        discountCardDao.delete(id);
    }

    @Override
    public void delete(int cardNumber) throws DaoException {
        discountCardDao.delete(cardNumber);
    }

    @Override
    public Optional<DiscountCard> findByNumber(int cardNumber) throws DaoException {
        return discountCardDao.findByNumber(cardNumber);
    }

    @Override
    public boolean changeDiscountPercent(long id, int discountPercent) throws DaoException {
        return discountCardDao.changeDiscountPercent(id, discountPercent);
    }

    @Override
    public boolean changeDiscountPercent(int cardNumber, int discountPercent) throws DaoException {
        return discountCardDao.changeDiscountPercent(cardNumber, discountPercent);
    }
}
