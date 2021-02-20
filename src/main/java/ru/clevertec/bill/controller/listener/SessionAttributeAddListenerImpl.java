package ru.clevertec.bill.controller.listener;

import ru.clevertec.bill.controller.command.AttributeName;
import ru.clevertec.bill.model.service.MailService;
import ru.clevertec.bill.model.service.impl.MailServiceImpl;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeAddListenerImpl implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        if (se.getName().equals(AttributeName.BILL_FILE_PATH)) {
            MailService mailService = new MailServiceImpl();
            mailService.sendEmail(session.getAttribute(AttributeName.BILL_FILE_PATH).toString());
        }
    }
}
