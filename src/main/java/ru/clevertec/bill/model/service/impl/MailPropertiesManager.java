package ru.clevertec.bill.model.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class MailPropertiesManager {
    static Logger logger = LogManager.getLogger();

    private static final Properties properties;

    private static final String PROPERTIES_FILE = "properties/mail.properties";
    private static final String USER_NAME = "mail.user.name";
    private static final String USER_PASSWORD = "mail.user.password";
    private static final String MAIL_TO = "mail.client.to";
    private static final String MAIL_SUBJECT = "mail.client.subject";
    private static final String MAIL_TEXT = "mail.client.text";

    private MailPropertiesManager() {
    }

    static {
        properties = new Properties();
        try {
            InputStream inputStream = MailPropertiesManager.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
            if (inputStream == null) {
                logger.log(Level.ERROR, "Properties file is not found!");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getUserName() {
        return properties.getProperty(USER_NAME);
    }

    public static String getUserPassword() {
        return properties.getProperty(USER_PASSWORD);
    }

    public static String getMailTo() {
        return properties.getProperty(MAIL_TO);
    }

    public static String getMailSubject() {
        return properties.getProperty(MAIL_SUBJECT);
    }

    public static String getMailText() {
        return properties.getProperty(MAIL_TEXT);
    }
}
