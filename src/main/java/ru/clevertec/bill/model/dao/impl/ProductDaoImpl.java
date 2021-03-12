package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.builder.ProductBuilder;
import ru.clevertec.bill.builder.impl.ProductBuilderImpl;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.model.dao.ProductDao;
import ru.clevertec.bill.model.pool.ConnectionPool;
import ru.clevertec.custom.CustomArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    private static final String FIND_PRODUCT_BY_ID = "SELECT product_id, name, price, promo FROM product " +
            "WHERE product_id = ?";
    private static final String FIND_ALL_PRODUCT = "SELECT product_id, name, price, promo FROM product";
    private static final String ADD_PRODUCT = "INSERT INTO product (name, price) VALUES(?, ?) " +
            "RETURNING product_id, name, price, promo";
    private static final String EDIT_PRODUCT = "UPDATE product SET name = ?, price = ?, promo = ? " +
            "WHERE product_id = ?";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE product_id = ?";
    private static final String FIND_PRODUCT_BY_NAME = "SELECT product_id, name, price, promo " +
            "FROM product WHERE name = ?";

    @Override
    public Optional<Product> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Optional<Product> optionalProduct = Optional.empty();
            if (resultSet.next()) {
                optionalProduct = Optional.of(createFromResultSet(resultSet));
            }
            return optionalProduct;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Product> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PRODUCT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new CustomArrayList<>();
            while (resultSet.next()) {
                productList.add(createFromResultSet(resultSet));
            }
            return productList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Product> add(Product product) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            ResultSet resultSet = preparedStatement.executeQuery();
            Optional<Product> optionalProduct = Optional.empty();
            if (resultSet.next()) {
                optionalProduct = Optional.of(createFromResultSet(resultSet));
            }
            return optionalProduct;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Product> edit(Product product) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setBoolean(3, product.isPromo());
            preparedStatement.setLong(4, product.getProductId());
            preparedStatement.executeUpdate();
            return Optional.of(product);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Product> findByName(String name) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            Optional<Product> optionalProduct = Optional.empty();
            if (resultSet.next()) {
                optionalProduct = Optional.of(createFromResultSet(resultSet));
            }
            return optionalProduct;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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
