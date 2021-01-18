package ru.clevertec.bill.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class DBPropertiesManager {
    static Logger logger = LogManager.getLogger();

    private static final Properties properties;

    private static final String PROPERTIES_FILE = "properties/database.properties";
    private static final String DRIVER = "database.driver";
    private static final String URL = "database.url";
    private static final String USER = "database.user";
    private static final String PASSWORD = "database.password";
    private static final String POOL_SIZE = "pool.size";

    private DBPropertiesManager() {
    }

    static {
        properties = new Properties();
        try {
            InputStream inputStream = DBPropertiesManager.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
            if (inputStream == null) {
                logger.log(Level.FATAL, "Properties file not found!");
                throw new RuntimeException("Properties file not found!");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.FATAL, "I/O error: " + e.getMessage());
            throw new RuntimeException("I/O error: ", e);
        }
    }

    static String getDriver() {
        return properties.getProperty(DRIVER);
    }

    static String getURL() {
        return properties.getProperty(URL);
    }

    static String getUser() {
        return properties.getProperty(USER);
    }

    static String getPassword() {
        return properties.getProperty(PASSWORD);
    }

    static String getPoolSize() {
        return properties.getProperty(POOL_SIZE);
    }
}
