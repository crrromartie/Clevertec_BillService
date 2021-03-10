package ru.clevertec.bill.controller.filter;

import ru.clevertec.bill.controller.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {("/CandyShop")})
public class TimeOutSessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX_PAGE);
        } else {
            chain.doFilter(request, response);
        }
    }
}
