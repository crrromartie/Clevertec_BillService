package ru.clevertec.bill.controller;

import ru.clevertec.bill.controller.command.PagePath;

public final class Router {

    enum Type {
        FORWARD,
        REDIRECT
    }

    private String page = PagePath.MAIN_PAGE;
    private Type type = Type.FORWARD;

    public Router() {
    }

    public Router(String currentPage) {
        this.page = currentPage;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Type getType() {
        return type;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }
}
