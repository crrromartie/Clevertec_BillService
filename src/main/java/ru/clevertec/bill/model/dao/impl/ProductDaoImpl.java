package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.builder.ProductBuilder;
import ru.clevertec.bill.builder.impl.ProductBuilderImpl;
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
    private static final String FIND_PRODUCT_BY_ID = "SELECT product_id, name, price, is_promo FROM product " +
            "WHERE product_id = ?";
    private static final String SELECT_ALL_PRODUCT = "SELECT product_id, name, price, is_promo FROM product";
    private static final String ADD_PRODUCT = "INSERT INTO product (name, price) VALUES(?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, price = ?, is_promo = ? " +
            "WHERE product_id = ?";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE product_id = ?";
    private static final String FIND_PRODUCT_BY_NAME = "SELECT product_id, name, price, is_promo " +
            "FROM product WHERE name = ?";

    @Override
    public Optional<Product> findById(long id) throws DaoException {
        Optional<Product> optionalProduct = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = createFromResultSet(resultSet);
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
    public boolean add(Product product) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Product> edit(Product product) throws DaoException {
        Optional<Product> optionalProduct = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement updateStatment = connection.prepareStatement(UPDATE_PRODUCT);
             PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
            updateStatment.setString(1, product.getName());
            updateStatment.setBigDecimal(2, product.getPrice());
            updateStatment.setBoolean(3, product.isPromo());
            updateStatment.setLong(4, product.getProductId());
            updateStatment.executeUpdate();

            statement.setLong(1, product.getProductId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product updatedProduct = createFromResultSet(resultSet);
                optionalProduct = Optional.of(updatedProduct);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalProduct;
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Product> findByName(String name) throws DaoException {
        Optional<Product> optionalProduct = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_NAME)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = createFromResultSet(resultSet);
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
        try {
            ProductBuilder productBuilder = new ProductBuilderImpl();
            productBuilder.setProductId(resultSet.getLong(ColumnName.PRODUCT_ID));
            productBuilder.setName(resultSet.getString(ColumnName.PRODUCT_NAME));
            productBuilder.setPrice(resultSet.getBigDecimal(ColumnName.PRODUCT_PRICE));
            productBuilder.setPromo(resultSet.getBoolean(ColumnName.PRODUCT_IS_PROMO));
            return productBuilder.getProduct();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
