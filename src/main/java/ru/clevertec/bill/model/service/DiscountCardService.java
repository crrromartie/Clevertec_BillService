package ru.clevertec.bill.model.service;

import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DiscountCardService {

    Optional<DiscountCard> findById(long id) throws ServiceException;

    List<DiscountCard> findAll() throws ServiceException;

    boolean add(Map<String, String> cardParameters) throws ServiceException;

    Optional<DiscountCard> edit(Map<String, String> editDiscountCardParameters, long id) throws ServiceException;

    void delete(long id) throws ServiceException;

    boolean isDiscountCardNumberUnique(String cardNumber) throws ServiceException;
}
