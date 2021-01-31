package ru.clevertec.bill.model.dao;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductDao extends Dao<Product> {

    Optional<Product> findByName(String name) throws DaoException;

    void delete(String name) throws DaoException;

    boolean changePrice(long id, BigDecimal price) throws DaoException;

    boolean changePrice(String name, BigDecimal price) throws DaoException;

    boolean makePromo(long id) throws DaoException;

    boolean makePromo(String name) throws DaoException;

    boolean removePromo(long id) throws DaoException;

    boolean removePromo(String name) throws DaoException;
}
