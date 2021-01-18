package ru.clevertec.bill.controller.listener;

import ru.clevertec.bill.controller.command.AttributeName;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerImpl implements HttpSessionListener {
    private final static String EN_LANG = "en";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(AttributeName.LANG, EN_LANG);
    }
}
