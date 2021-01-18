package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.collection.CustomArrayList;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.model.dao.ProductDao;
import ru.clevertec.bill.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    private static final String SELECT_ALL_PRODUCT = "SELECT productId, name, price, isPromotional FROM products";
    private static final String FIND_PRODUCT_BY_ID = "SELECT productId, name, price, isPromotional " +
            "FROM products WHERE productId = ?";
    private static final String FIND_PRODUCT_BY_NAME = "SELECT productId, name, price, isPromotional " +
            "FROM products WHERE name = ?";
    private static final String ADD_PRODUCT = "INSERT INTO products (name, price) VALUES(?, ?)";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE name = ?";
    private static final String PROMO_TRUE = "UPDATE products SET isPromotional = true WHERE name = ?";
    private static final String PROMO_FALSE = "UPDATE products SET isPromotional = false WHERE name = ?";
    private static final String CHANGE_PRICE = "UPDATE products SET price = ? WHERE name = ?";

    @Override
    public List<Product> findAll() throws DaoException {
        List<Product> productList = new CustomArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCT)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = createFromResultSet(resultSet);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return productList;
    }

    @Override
    public Optional<Product> findProductById(long id) throws DaoException {
        Optional<Product> optionalProduct = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Product product = null;
            if (resultSet.next()) {
                product = createFromResultSet(resultSet);
                optionalProduct = Optional.of(product);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalProduct;
    }

    @Override
    public boolean add(Product product) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(String name) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setString(1, name);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean promoTrue(String name) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(PROMO_TRUE)) {
            statement.setString(1, name);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean promoFalse(String name) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(PROMO_FALSE)) {
            statement.setString(1, name);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean changePrice(String name, double price) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_PRICE)) {
            statement.setDouble(1, price);
            statement.setString(2, name);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Product> findProductByName(String name) throws DaoException {
        Optional<Product> optionalProduct = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_NAME)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            Product product = null;
            if (resultSet.next()) {
                product = createFromResultSet(resultSet);
                optionalProduct = Optional.of(product);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalProduct;
    }

    private Product createFromResultSet(ResultSet resultSet) throws DaoException {
        Product product = new Product();
        try {
            product.setProductId(resultSet.getLong(ColumnName.PRODUCT_ID));
            product.setName(resultSet.getString(ColumnName.PRODUCT_NAME));
            product.setPrice(resultSet.getDouble(ColumnName.PRODUCT_PRICE));
            product.setPromotional(resultSet.getBoolean(ColumnName.PRODUCT_IS_PROMO));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return product;
    }
}
