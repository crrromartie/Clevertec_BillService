package ru.clevertec.bill.model.dao;

import ru.clevertec.bill.entity.Entity;
import ru.clevertec.bill.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T extends Entity> {
    Logger logger = LogManager.getLogger();

    List<T> findAll() throws DaoException;

    default void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.log(Level.WARN, "Error while closing resultSet!", e);
            }
        }
    }
}
