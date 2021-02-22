package ru.clevertec.bill.model.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.entity.Entity;
import ru.clevertec.bill.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    Logger logger = LogManager.getLogger();

    Optional<T> findById(long id) throws DaoException;

    List<T> findAll() throws DaoException;

    boolean add(T t) throws DaoException;

    Optional<T> edit(T t) throws DaoException;

    void delete(long id) throws DaoException;

    default void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.log(Level.WARN, e.getMessage());
            }
        }
    }
}
