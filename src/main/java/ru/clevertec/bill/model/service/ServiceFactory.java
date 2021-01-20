package ru.clevertec.bill.model.service;

import ru.clevertec.bill.model.service.Impl.DiscountCardServiceImpl;
import ru.clevertec.bill.model.service.Impl.ProductServiceImpl;
import ru.clevertec.bill.model.service.proxy.DiscountCardServiceHandler;
import ru.clevertec.bill.model.service.proxy.ProductServiceHandler;

import java.lang.reflect.Proxy;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final ProductService productService = new ProductServiceImpl();
    private final DiscountCardService discountCardService = new DiscountCardServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public ProductService getProductService() {
        ClassLoader productServiceClassLoader = productService.getClass().getClassLoader();
        Class<?>[] productServiceInterfaces = productService.getClass().getInterfaces();
        return (ProductService) Proxy.newProxyInstance(productServiceClassLoader,
                productServiceInterfaces, new ProductServiceHandler(productService));
    }

    public DiscountCardService getDiscountCardService() {
        ClassLoader discountCardServiceClassLoader = discountCardService.getClass().getClassLoader();
        Class<?>[] discountCardServiceInterfaces = discountCardService.getClass().getInterfaces();
        return (DiscountCardService) Proxy.newProxyInstance(discountCardServiceClassLoader,
                discountCardServiceInterfaces, new DiscountCardServiceHandler(discountCardService));
    }
}
