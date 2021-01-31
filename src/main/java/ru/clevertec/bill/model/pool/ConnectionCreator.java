package ru.clevertec.bill.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionCreator {
    static Logger logger = LogManager.getLogger();

    private static final String DATABASE_URL = DBPropertiesManager.getURL();
    private static final String DATABASE_USER = DBPropertiesManager.getUser();
    private static final String DATABASE_PASSWORD = DBPropertiesManager.getPassword();

    private ConnectionCreator() {
    }

    static ProxyConnection createConnection() {
        Connection connection;
        ProxyConnection proxyConnection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            proxyConnection = new ProxyConnection(connection);
        } catch (SQLException e) {
            logger.log(Level.FATAL, e.getMessage());
            throw new RuntimeException("Connection not created!", e);
        }
        return proxyConnection;
    }
}
