package ru.clevertec.bill.observer.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.model.service.MailService;
import ru.clevertec.bill.observer.EventListener;
import ru.clevertec.bill.observer.entity.State;

public class MailListener implements EventListener {
    static Logger logger = LogManager.getLogger();

    private final MailService mailService;

    public MailListener(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void update(State eventType, String message) {
        logger.log(Level.INFO, "Event type {}", eventType.toString());
        mailService.sendEmail(message);
    }
}
