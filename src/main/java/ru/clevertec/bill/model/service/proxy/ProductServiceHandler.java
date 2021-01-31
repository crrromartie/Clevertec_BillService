package ru.clevertec.bill.model.service.proxy;

import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.parser.CustomJsonParser;
import ru.clevertec.bill.parser.impl.CustomJsonParserImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProductServiceHandler implements InvocationHandler {
    private final ProductService productService;

    private static final String EMPTY_STRING = "";

    private static final String FIND_ALL = "findAll";

    public ProductServiceHandler(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Logger logger = LogManager.getLogger(method.getDeclaringClass().getCanonicalName());
        CustomJsonParser parser = new CustomJsonParserImpl();
        Gson gson = new Gson();
        Object invoke = method.invoke(productService, args);
        if (method.getName().equals(FIND_ALL)) {

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
        return method.invoke(productService, args);
    }
}
