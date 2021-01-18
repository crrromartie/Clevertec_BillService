package ru.clevertec.bill.model.service.Impl;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.dao.Dao;
import ru.clevertec.bill.model.dao.DaoFactory;
import ru.clevertec.bill.model.dao.DiscountCardDao;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.validator.DiscountCardValidator;

import java.util.List;
import java.util.Optional;

public class DiscountCardServiceImpl implements DiscountCardService {
    @Override
    public List<DiscountCard> findAll() throws ServiceException {
        List<DiscountCard> cardList = null;
        DaoFactory factory = DaoFactory.getINSTANCE();
        Dao dao = factory.getDiscountCardDao();
        try {
            cardList = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return cardList;
    }

    @Override
    public Optional<DiscountCard> findCardByNumber(int cardNumber) throws ServiceException {
        Optional<DiscountCard> discountCard = Optional.empty();
        DaoFactory factory = DaoFactory.getINSTANCE();
        DiscountCardDao discountCardDao = factory.getDiscountCardDao();
        try {
            discountCard = discountCardDao.findCardByNumber(cardNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return discountCard;
    }

    @Override
    public boolean add(DiscountCard card) throws ServiceException {
        boolean isAdded = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        DiscountCardDao discountCardDao = factory.getDiscountCardDao();
        if (DiscountCardValidator.isCardNumberValid(card.getCardNumber())
                && DiscountCardValidator.isPromoDiscountValid(card.getDiscount())) {
            try {
                isAdded = discountCardDao.add(card);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isAdded;
    }

    @Override
    public boolean delete(int cardNumber) throws ServiceException {
        boolean isDeleted = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        DiscountCardDao discountCardDao = factory.getDiscountCardDao();
        if (DiscountCardValidator.isCardNumberValid(cardNumber)) {
            try {
                isDeleted = discountCardDao.delete(cardNumber);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isDeleted;
    }

    @Override
    public boolean changeDiscountPercent(int cardNumber, double discount) throws ServiceException {
        boolean isChanged = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        DiscountCardDao discountCardDao = factory.getDiscountCardDao();
        if (DiscountCardValidator.isCardNumberValid(cardNumber) &&
                DiscountCardValidator.isPromoDiscountValid(discount)) {
            try {
                isChanged = discountCardDao.changeDiscountPercent(cardNumber, discount);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isChanged;
    }

    @Override
    public boolean isDiscountCardNumberUnique(int cardNumber) throws ServiceException {
        boolean isUnique = true;
        DaoFactory factory = DaoFactory.getINSTANCE();
        DiscountCardDao discountCardDao = factory.getDiscountCardDao();
        if (DiscountCardValidator.isCardNumberValid(cardNumber)) {
            try {
                if (!discountCardDao.findCardByNumber(cardNumber).isEmpty()) {
                    isUnique = false;
                }
            } catch (DaoException e) {
                throw new ServiceException("Card number unique exception!", e);
            }
        }
        return isUnique;
    }
}
