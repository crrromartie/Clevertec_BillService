package ru.clevertec.bill.model.dao;

import ru.clevertec.bill.model.dao.impl.DiscountCardDaoImpl;
import ru.clevertec.bill.model.dao.impl.ProductDaoImpl;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    private final ProductDao productDao = new ProductDaoImpl();
    private final DiscountCardDao discountCardDao = new DiscountCardDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public DiscountCardDao getDiscountCardDao() {
        return discountCardDao;
    }
}
