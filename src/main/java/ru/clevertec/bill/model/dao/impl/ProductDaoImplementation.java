package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.builder.ProductBuilder;
import ru.clevertec.bill.builder.impl.ProductBuilderImpl;
import ru.clevertec.bill.collection.CustomArrayList;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.model.dao.AbstractDao;
import ru.clevertec.bill.model.dao.ProductDao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

class ProductDaoImplementation extends AbstractDao implements ProductDao {
    private static final String SELECT_ALL_PRODUCT = "SELECT product_id, name, price, is_promo FROM product";
    private static final String FIND_PRODUCT_BY_ID = "SELECT product_id, name, price, is_promo FROM " +
            "product WHERE product_id = ?";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE product_id = ?";
    private static final String FIND_PRODUCT_BY_NAME = "SELECT product_id, name, price, is_promo " +
            "FROM product WHERE name = ?";
    private static final String DELETE_PRODUCT_BY_NAME = "DELETE FROM product WHERE name = ?";
    private static final String CHANGE_PRICE_BY_ID = "UPDATE product SET price = ? WHERE product_id = ?";
    private static final String CHANGE_PRICE_BY_NAME = "UPDATE product SET price = ? WHERE name = ?";
    private static final String ADD_PRODUCT = "INSERT INTO product (name, price) VALUES(?, ?)";
    private static final String MAKE_PROMO_BY_ID = "UPDATE product SET is_promo = true WHERE product_id = ?";
    private static final String MAKE_PROMO_BY_NAME = "UPDATE product SET is_promo = true WHERE name = ?";
    private static final String REMOVE_PROMO_BY_ID = "UPDATE product SET is_promo = false WHERE product_id = ?";
    private static final String REMOVE_PROMO_BY_NAME = "UPDATE product SET is_promo = false WHERE name = ?";

    @Override
    public List<Product> findAll() throws DaoException {
        List<Product> productList = new CustomArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCT)) {
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
    public Optional<Product> findById(long id) throws DaoException {
        Optional<Product> optionalProduct = Optional.empty();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
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
        try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID)) {
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
        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_NAME)) {
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

    @Override
    public void delete(String name) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_NAME)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean changePrice(long id, BigDecimal price) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_PRICE_BY_ID)) {
            statement.setBigDecimal(1, price);
            statement.setLong(2, id);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean changePrice(String name, BigDecimal price) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_PRICE_BY_NAME)) {
            statement.setBigDecimal(1, price);
            statement.setString(2, name);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean makePromo(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(MAKE_PROMO_BY_ID)) {
            statement.setLong(1, id);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean makePromo(String name) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(MAKE_PROMO_BY_NAME)) {
            statement.setString(1, name);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean removePromo(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_PROMO_BY_ID)) {
            statement.setLong(1, id);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean removePromo(String name) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_PROMO_BY_NAME)) {
            statement.setString(1, name);
            return (statement.executeUpdate() == 1);
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
