package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.model.dao.ProductDao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    private final ProductDaoImplementation productDao;

    public ProductDaoImpl() {
        this.productDao = new ProductDaoImplementation();
    }

    @Override
    public void setConnection(Connection connection) {
        productDao.setConnection(connection);
    }

    @Override
    public List<Product> findAll() throws DaoException {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> findById(long id) throws DaoException {
        return productDao.findById(id);
    }

    @Override
    public boolean add(Product product) throws DaoException {
        return productDao.add(product);
    }

    @Override
    public void delete(long id) throws DaoException {
        productDao.delete(id);
    }

    @Override
    public Optional<Product> findByName(String name) throws DaoException {
        return productDao.findByName(name);
    }

    @Override
    public void delete(String name) throws DaoException {
        productDao.delete(name);
    }

    @Override
    public boolean changePrice(long id, BigDecimal price) throws DaoException {
        return productDao.changePrice(id, price);
    }

    @Override
    public boolean changePrice(String name, BigDecimal price) throws DaoException {
        return productDao.changePrice(name, price);
    }

    @Override
    public boolean makePromo(long id) throws DaoException {
        return productDao.makePromo(id);
    }

    @Override
    public boolean makePromo(String name) throws DaoException {
        return productDao.makePromo(name);
    }

    @Override
    public boolean removePromo(long id) throws DaoException {
        return productDao.removePromo(id);
    }

    @Override
    public boolean removePromo(String name) throws DaoException {
        return productDao.removePromo(name);
    }
}
