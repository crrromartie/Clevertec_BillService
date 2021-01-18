package ru.clevertec.bill.model.service;

import ru.clevertec.bill.model.service.Impl.DiscountCardServiceImpl;
import ru.clevertec.bill.model.service.Impl.ProductServiceImpl;

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
        return productService;
    }

    public DiscountCardService getDiscountCardService() {
        return discountCardService;
    }
}
