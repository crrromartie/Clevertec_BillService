package ru.clevertec.bill.model.dao;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;

import java.util.Optional;

public interface ProductDao extends Dao<Product> {
    Optional<Product> findProductById(long id) throws DaoException;

    boolean add(Product product) throws DaoException;

    boolean delete(String name) throws DaoException;

    boolean promoTrue(String name) throws DaoException;

    boolean promoFalse(String name) throws DaoException;

    boolean changePrice(String name, double price) throws DaoException;

    Optional<Product> findProductByName(String name) throws DaoException;
}
