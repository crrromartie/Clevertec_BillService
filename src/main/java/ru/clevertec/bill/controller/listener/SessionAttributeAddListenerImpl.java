package ru.clevertec.bill.controller.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.MailService;
import ru.clevertec.bill.model.service.ServiceFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeAddListenerImpl implements HttpSessionAttributeListener {
    static Logger logger = LogManager.getLogger();

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        if (se.getName().equals(AttributeName.BILL_FILE_PATH)) {
            MailService mailService = ServiceFactory.getINSTANCE().getMailService();
            try {
                mailService.sendEmail(session.getAttribute(AttributeName.BILL_FILE_PATH).toString());
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
        session.removeAttribute(AttributeName.BILL_FILE_PATH);
    }
}
