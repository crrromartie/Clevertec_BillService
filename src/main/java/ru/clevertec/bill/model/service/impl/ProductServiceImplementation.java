package ru.clevertec.bill.model.service.impl;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.dao.ProductDao;
import ru.clevertec.bill.model.dao.impl.ProductDaoImpl;
import ru.clevertec.bill.model.service.EntityTransaction;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.validator.ProductValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

class ProductServiceImplementation implements ProductService {

    @Override
    public List<Product> findAll() throws ServiceException {
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public Optional<Product> findById(long id) throws ServiceException {
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean add(Product product) throws ServiceException {
        if (!ProductValidator.isProductNameValid(product.getName())
                || !ProductValidator.isProductPriceValid(product.getPrice())) {
            return false;
        }
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.add(product);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            productDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public Optional<Product> findByName(String name) throws ServiceException {
        if (!ProductValidator.isProductNameValid(name)) {
            return Optional.empty();
        }
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public void delete(String name) throws ServiceException {
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            productDao.delete(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean changePrice(long id, BigDecimal price) throws ServiceException {
        if (!ProductValidator.isProductPriceValid(price)) {
            return false;
        }
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.changePrice(id, price);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean changePrice(String name, BigDecimal price) throws ServiceException {
        if (!ProductValidator.isProductNameValid(name)
                || !ProductValidator.isProductPriceValid(price)) {
            return false;
        }
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.changePrice(name, price);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean makePromo(long id) throws ServiceException {
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.makePromo(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean makePromo(String name) throws ServiceException {
        if (!ProductValidator.isProductNameValid(name)) {
            return false;
        }
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.makePromo(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean removePromo(long id) throws ServiceException {
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.removePromo(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean removePromo(String name) throws ServiceException {
        if (!ProductValidator.isProductNameValid(name)) {
            return false;
        }
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.removePromo(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }

    @Override
    public boolean addPromoProduct(Product product) throws ServiceException {
        if (!ProductValidator.isProductNameValid(product.getName())
                || !ProductValidator.isProductPriceValid(product.getPrice())) {
            return false;
        }
        boolean isAdded = false;
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(productDao);
        try {
            if (productDao.add(product)
                    && productDao.makePromo(product.getName())) {
                isAdded = true;
            }
            transaction.commitTransaction();
        } catch (DaoException e) {
            transaction.rollbackTransaction();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
        return isAdded;
    }

    private boolean isProductNameUnique(String name) throws ServiceException {
        if (!ProductValidator.isProductNameValid(name)) {
            return false;
        }
        ProductDao productDao = new ProductDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleOperation(productDao);
        try {
            return productDao.findByName(name).isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleOperation();
        }
    }
}
