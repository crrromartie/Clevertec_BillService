package ru.clevertec.bill.model.service;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface DiscountCardService {
    List<DiscountCard> findAll() throws ServiceException;

    Optional<DiscountCard> findCardByNumber(int cardNumber) throws ServiceException;

    boolean add(DiscountCard card) throws ServiceException;

    boolean delete(int cardNumber) throws ServiceException;

    boolean changeDiscountPercent(int cardNumber, double discount) throws ServiceException;

    boolean isDiscountCardNumberUnique(int cardNumber) throws ServiceException;
}
