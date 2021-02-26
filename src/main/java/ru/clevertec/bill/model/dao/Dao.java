package ru.clevertec.bill.model.dao;

import ru.clevertec.bill.entity.Entity;
import ru.clevertec.bill.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {

    Optional<T> findById(long id) throws DaoException;

    List<T> findAll() throws DaoException;

    Optional<T> add(T t) throws DaoException;

    Optional<T> edit(T t) throws DaoException;

    void delete(long id) throws DaoException;
}
