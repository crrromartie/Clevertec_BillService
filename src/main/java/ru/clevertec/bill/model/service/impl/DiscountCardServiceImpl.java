package ru.clevertec.bill.model.service.impl;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.model.service.proxy.DiscountCardServiceHandler;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

public class DiscountCardServiceImpl implements DiscountCardService {
    private static final DiscountCardServiceImpl INSTANCE = new DiscountCardServiceImpl();

    private final DiscountCardService discountCardService = new DiscountCardServiceImplementation();

    private DiscountCardServiceImpl() {
    }

    public static DiscountCardServiceImpl getINSTANCE() {
        return INSTANCE;
    }

    public DiscountCardService getDiscountCardService() {
        ClassLoader discountCardServiceClassLoader = discountCardService.getClass().getClassLoader();
        Class<?>[] discountCardServiceInterfaces = discountCardService.getClass().getInterfaces();
        return (DiscountCardService) Proxy.newProxyInstance(discountCardServiceClassLoader,
                discountCardServiceInterfaces, new DiscountCardServiceHandler(discountCardService));
    }

    @Override
    public List<DiscountCard> findAll() throws ServiceException {
        return discountCardService.findAll();
    }

    @Override
    public Optional<DiscountCard> findById(long id) throws ServiceException {
        return discountCardService.findById(id);
    }

    @Override
    public boolean add(DiscountCard card) throws ServiceException {
        return discountCardService.add(card);
    }

    @Override
    public void delete(long id) throws ServiceException {
        discountCardService.delete(id);
    }

    @Override
    public void delete(int cardNumber) throws ServiceException {
        discountCardService.delete(cardNumber);
    }

    @Override
    public Optional<DiscountCard> findByNumber(int cardNumber) throws ServiceException {
        return discountCardService.findByNumber(cardNumber);
    }

    @Override
    public boolean changeDiscountPercent(long id, int discountPercent) throws ServiceException {
        return discountCardService.changeDiscountPercent(id, discountPercent);
    }

    @Override
    public boolean changeDiscountPercent(int cardNumber, int discountPercent) throws ServiceException {
        return discountCardService.changeDiscountPercent(cardNumber, discountPercent);
    }
}
