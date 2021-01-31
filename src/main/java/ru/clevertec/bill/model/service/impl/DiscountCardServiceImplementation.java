package ru.clevertec.bill.model.service.impl;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.dao.DiscountCardDao;
import ru.clevertec.bill.model.dao.impl.DiscountCardDaoImpl;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.EntityTransaction;
import ru.clevertec.bill.validator.DiscountCardValidator;

import java.util.List;
import java.util.Optional;

class DiscountCardServiceImplementation implements DiscountCardService {

    @Override
    public List<DiscountCard> findAll() throws ServiceException {
        DiscountCardDao cardDao = new DiscountCardDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(cardDao);
        try {
            return cardDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public Optional<DiscountCard> findById(long id) throws ServiceException {
        DiscountCardDao cardDao = new DiscountCardDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(cardDao);
        try {
            return cardDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean add(DiscountCard card) throws ServiceException {
        if (!DiscountCardValidator.isCardNumberValid(card.getCardNumber())
                || !DiscountCardValidator.isDiscountPercentValid(card.getDiscountPercent())) {
            return false;
        }
        DiscountCardDao cardDao = new DiscountCardDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(cardDao);
        try {
            return cardDao.add(card);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        DiscountCardDao cardDao = new DiscountCardDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(cardDao);
        try {
            cardDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public void delete(int cardNumber) throws ServiceException {
        DiscountCardDao cardDao = new DiscountCardDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(cardDao);
        try {
            cardDao.delete(cardNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public Optional<DiscountCard> findByNumber(int cardNumber) throws ServiceException {
        DiscountCardDao cardDao = new DiscountCardDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(cardDao);
        try {
            return cardDao.findByNumber(cardNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean changeDiscountPercent(long id, int discountPercent) throws ServiceException {
        DiscountCardDao cardDao = new DiscountCardDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(cardDao);
        try {
            return cardDao.changeDiscountPercent(id, discountPercent);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean changeDiscountPercent(int cardNumber, int discountPercent) throws ServiceException {
        if (!DiscountCardValidator.isCardNumberValid(cardNumber)
                || !DiscountCardValidator.isDiscountPercentValid(discountPercent)) {
            return false;
        }
        DiscountCardDao cardDao = new DiscountCardDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(cardDao);
        try {
            return cardDao.changeDiscountPercent(cardNumber, discountPercent);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    private boolean isDiscountCardNumberUnique(int cardNumber) throws ServiceException {
        if (!DiscountCardValidator.isCardNumberValid(cardNumber)) {
            return false;
        }
        DiscountCardDao cardDao = new DiscountCardDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(cardDao);
        try {
            return cardDao.findByNumber(cardNumber).isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }
}
