package ru.clevertec.bill.model.service.proxy;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.parser.CustomJsonParser;
import ru.clevertec.bill.parser.impl.CustomJsonParserImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DiscountCardServiceHandler implements InvocationHandler {
    private final DiscountCardService discountCardService;

    private static final String EMPTY_STRING = "";

    private static final String FIND_BY_NUMBER = "findByNumber";

    public DiscountCardServiceHandler(DiscountCardService discountCardService) {
        this.discountCardService = discountCardService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Logger logger = LogManager.getLogger(method.getDeclaringClass().getCanonicalName());
        CustomJsonParser parser = new CustomJsonParserImpl();
        Object invoke = method.invoke(discountCardService, args);
        if (method.getName().equals(FIND_BY_NUMBER)) {

            String arguments = EMPTY_STRING;
            if (args != null) {
                arguments = parser.parseToJson(args);
            }

            String result = EMPTY_STRING;
            if (invoke != null) {
                result = parser.parseToJson(invoke);
            }

            logger.log(Level.DEBUG, "{} args={}", method.getName(), arguments);
            logger.log(Level.DEBUG, "{} result={}", method.getName(), result);
        }
        return method.invoke(discountCardService, args);
    }
}