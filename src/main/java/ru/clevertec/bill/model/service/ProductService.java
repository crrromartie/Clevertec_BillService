package ru.clevertec.bill.model.service;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll() throws ServiceException;

    Optional<Product> findById(long id) throws ServiceException;

    boolean add(Product product) throws ServiceException;

    void delete(long id) throws ServiceException;

    Optional<Product> findByName(String name) throws ServiceException;

    void delete(String name) throws ServiceException;

    boolean changePrice(long id, BigDecimal price) throws ServiceException;

    boolean changePrice(String name, BigDecimal price) throws ServiceException;

    boolean makePromo(long id) throws ServiceException;

    boolean makePromo(String name) throws ServiceException;

    boolean removePromo(long id) throws ServiceException;

    boolean removePromo(String name) throws ServiceException;

    boolean addPromoProduct(Product product) throws ServiceException;
}
