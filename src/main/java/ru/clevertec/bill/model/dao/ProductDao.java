package ru.clevertec.bill.model.dao;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;

import java.util.Optional;

public interface ProductDao extends Dao<Product> {

    Optional<Product> findByName(String name) throws DaoException;
}
