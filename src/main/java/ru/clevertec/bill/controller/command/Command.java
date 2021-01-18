package ru.clevertec.bill.controller.command;

import ru.clevertec.bill.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);
}
