package ru.clevertec.bill.model.service;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll() throws ServiceException;

    Optional<Product> findProductById(long id) throws ServiceException;

    boolean add(Product product) throws ServiceException;

    boolean delete(String name) throws ServiceException;

    boolean promoTrue(String name) throws ServiceException;

    boolean promoFalse(String name) throws ServiceException;

    boolean changePrice(String name, double price) throws ServiceException;

    boolean isProductUnique(String name) throws ServiceException;
}
