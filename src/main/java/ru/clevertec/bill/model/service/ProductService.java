package ru.clevertec.bill.model.service;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(long id) throws ServiceException;

    List<Product> findAll() throws ServiceException;

    boolean add(Map<String, String> productParameters) throws ServiceException;

    Optional<Product> edit(Map<String, String> editProductParameters, long id) throws ServiceException;

    void delete(long id) throws ServiceException;

    boolean isProductNameUnique(String name) throws ServiceException;
}
