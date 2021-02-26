package ru.clevertec.bill.model.service.impl;

import ru.clevertec.bill.builder.DiscountCardBuilder;
import ru.clevertec.bill.builder.impl.DiscountCardBuilderImpl;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.dao.DaoFactory;
import ru.clevertec.bill.model.dao.DiscountCardDao;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.validator.DiscountCardValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DiscountCardServiceImpl implements DiscountCardService {
    private final DiscountCardDao cardDao = DaoFactory.getINSTANCE().getDiscountCardDao();

    @Override
    public List<DiscountCard> findAll() throws ServiceException {
        try {
            return cardDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<DiscountCard> findById(long id) throws ServiceException {
        try {
            return cardDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<DiscountCard> add(Map<String, String> cardParameters) throws ServiceException {
        if (!DiscountCardValidator.isCardParametersValid(cardParameters)) {
            return Optional.empty();
        }
        DiscountCardBuilder discountCardBuilder = new DiscountCardBuilderImpl();
        discountCardBuilder.setCardNumber(Integer.parseInt(cardParameters.get(ParameterName.CARD_NUMBER)));
        discountCardBuilder.setDiscountPercent(Integer.parseInt(cardParameters.get(ParameterName.CARD_DISCOUNT_PERCENT)));
        try {
            return cardDao.add(discountCardBuilder.getDiscountCard());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<DiscountCard> edit(Map<String, String> editDiscountCardParameters, long id)
            throws ServiceException {
        if (!DiscountCardValidator.isCardParametersValid(editDiscountCardParameters)) {
            return Optional.empty();
        }
        DiscountCardBuilder discountCardBuilder = new DiscountCardBuilderImpl();
        discountCardBuilder.setCardId(id);
        discountCardBuilder.setCardNumber(Integer.parseInt(editDiscountCardParameters.get(ParameterName.CARD_NUMBER)));
        discountCardBuilder.setDiscountPercent(Integer.parseInt(editDiscountCardParameters
                .get(ParameterName.CARD_DISCOUNT_PERCENT)));
        try {
            return cardDao.edit(discountCardBuilder.getDiscountCard());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            cardDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isDiscountCardNumberUnique(String cardNumber) throws ServiceException {
        try {
            return cardDao.findByNumber(Integer.parseInt(cardNumber)).isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
