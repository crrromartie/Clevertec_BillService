package ru.clevertec.bill.model.service.proxy;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.model.service.DiscountCardService;
import ru.clevertec.bill.parser.CustomJsonParser;
import ru.clevertec.bill.parser.CustomJsonParserImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DiscountCardServiceHandler implements InvocationHandler {
    static Logger logger = LogManager.getLogger();

    private DiscountCardService discountCardService;

    private static final String SPACE = " ";
    private static final String ARGS = "args=";
    private static final String RESULT = "result=";
    private static final String VOID = "void";

    private static final String FIND_CARD_BY_NUMBER = "findCardByNumber";

    public DiscountCardServiceHandler(DiscountCardService discountCardService) {
        this.discountCardService = discountCardService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        if(method.getName().equals(FIND_CARD_BY_NUMBER)){
            String log = createLog(method, args);
            logger.log(Level.DEBUG, log);
        }
        return method.invoke(discountCardService, args);
    }

    private String createLog(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        StringBuilder log = new StringBuilder();
        CustomJsonParser parser = new CustomJsonParserImpl();
        Object invoke = method.invoke(discountCardService, args);
        String methodClassName = method.getDeclaringClass().getCanonicalName();
        log.append(methodClassName)
                .append(SPACE);
        String methodName = method.getName();
        log.append(methodName)
                .append(SPACE)
                .append(ARGS);
        if (args != null) {
            log.append(parser.parseToJson(args));
        }
        log.append(SPACE)
                .append(RESULT);
        String returnType = method.getReturnType().getSimpleName();
        if (!returnType.equals(void.class.getSimpleName())) {
            log.append(parser.parseToJson(invoke));
        } else {
            log.append(VOID);
        }
        return log.toString();
    }
}