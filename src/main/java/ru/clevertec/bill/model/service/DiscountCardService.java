package ru.clevertec.bill.model.service;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface DiscountCardService {

    List<DiscountCard> findAll() throws ServiceException;

    Optional<DiscountCard> findById(long id) throws ServiceException;

    boolean add(DiscountCard card) throws ServiceException;

    void delete(long id) throws ServiceException;

    void delete(int cardNumber) throws ServiceException;

    Optional<DiscountCard> findByNumber(int cardNumber) throws ServiceException;

    boolean changeDiscountPercent(long id, int discountPercent) throws ServiceException;

    boolean changeDiscountPercent(int cardNumber, int discountPercent) throws ServiceException;
}
