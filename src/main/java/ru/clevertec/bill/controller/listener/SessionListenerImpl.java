package ru.clevertec.bill.controller.listener;

import ru.clevertec.bill.controller.command.AttributeName;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListenerImpl implements HttpSessionListener {
    private static final String EN_LANG = "en";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(AttributeName.LANG, EN_LANG);
    }
}
