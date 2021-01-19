package ru.clevertec.bill.model.service.Impl;

import ru.clevertec.bill.annotations.LoggingAnnotation;
import ru.clevertec.bill.annotations.LoggingLevel;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.dao.DaoFactory;
import ru.clevertec.bill.model.dao.ProductDao;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.validator.ProductValidator;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAll() throws ServiceException {
        List<Product> productList = null;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ProductDao productDao = factory.getProductDao();
        try {
            productList = productDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return productList;
    }

    @LoggingAnnotation(LoggingLevel.INFO)
    @Override
    public Optional<Product> findProductById(long id) throws ServiceException {
        Optional<Product> optionalProduct = Optional.empty();
        DaoFactory factory = DaoFactory.getINSTANCE();
        ProductDao productDao = factory.getProductDao();
        try {
            optionalProduct = productDao.findProductById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalProduct;
    }

    @Override
    public boolean add(Product product) throws ServiceException {
        boolean isAdded = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ProductDao productDao = factory.getProductDao();
        if (ProductValidator.isProductNameValid(product.getName())
                && ProductValidator.isProductPriceValid(product.getPrice())) {
            try {
                isAdded = productDao.add(product);

            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isAdded;
    }

    @Override
    public boolean delete(String name) throws ServiceException {
        boolean isDeleted = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ProductDao productDao = factory.getProductDao();
        if (ProductValidator.isProductNameValid(name)) {
            try {
                isDeleted = productDao.delete(name);

            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isDeleted;
    }

    @Override
    public boolean promoTrue(String name) throws ServiceException {
        boolean isPromoTrue = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ProductDao productDao = factory.getProductDao();
        if (ProductValidator.isProductNameValid(name)) {
            try {
                isPromoTrue = productDao.promoTrue(name);

            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isPromoTrue;
    }

    @Override
    public boolean promoFalse(String name) throws ServiceException {
        boolean isPromoFalse = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ProductDao productDao = factory.getProductDao();
        if (ProductValidator.isProductNameValid(name)) {
            try {
                isPromoFalse = productDao.promoFalse(name);

            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isPromoFalse;
    }

    @Override
    public boolean changePrice(String name, double price) throws ServiceException {
        boolean isChanged = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ProductDao productDao = factory.getProductDao();
        if (ProductValidator.isProductNameValid(name)
                && ProductValidator.isProductPriceValid(price)) {
            try {
                isChanged = productDao.changePrice(name, price);

            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isChanged;
    }

    @Override
    public boolean isProductUnique(String name) throws ServiceException {
        boolean isUnique = true;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ProductDao productDao = factory.getProductDao();
        if (ProductValidator.isProductNameValid(name)) {
            try {
                if (!productDao.findProductByName(name).isEmpty()) {
                    isUnique = false;
                }
            } catch (DaoException e) {
                throw new ServiceException("Product name unique exception!", e);
            }
        }
        return isUnique;
    }
}
