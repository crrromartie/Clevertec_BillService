package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.collection.CustomArrayList;
import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.model.dao.DiscountCardDao;
import ru.clevertec.bill.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DiscountCardDaoImpl implements DiscountCardDao {
    private static final String SELECT_ALL_CARD = "SELECT cardId, cardNumber, discount FROM discountCards";
    private static final String SELECT_CARD_BY_NUMBER = "SELECT cardID, cardNumber, discount " +
            "FROM discountCards WHERE cardNumber = ?";
    private static final String ADD_DISCOUNT_CARD = "INSERT INTO discountCards (cardNumber, discount) VALUES(?, ?)";
    private static final String DELETE_CARD = "DELETE FROM discountCards WHERE cardNumber = ?";
    private static final String CHANGE_DISCOUNT_CARD_PERCENT = "UPDATE discountCards SET discount = ? WHERE cardNumber = ?";

    @Override
    public List<DiscountCard> findAll() throws DaoException {
        List<DiscountCard> cardList = new CustomArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CARD)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DiscountCard card = createFromResultSet(resultSet);
                cardList.add(card);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return cardList;
    }

    @Override
    public Optional<DiscountCard> findCardByNumber(int cardNumber) throws DaoException {
        Optional<DiscountCard> cardOptional = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CARD_BY_NUMBER)) {
            statement.setInt(1, cardNumber);
            resultSet = statement.executeQuery();
            DiscountCard discountCard = null;
            if (resultSet.next()) {
                discountCard = createFromResultSet(resultSet);
                cardOptional = Optional.of(discountCard);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return cardOptional;
    }

    @Override
    public boolean add(DiscountCard card) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_DISCOUNT_CARD)) {
            statement.setInt(1, card.getCardNumber());
            statement.setDouble(2, card.getDiscount());
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(int cardNumber) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CARD)) {
            statement.setInt(1, cardNumber);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean changeDiscountPercent(int cardNumber, double discount) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_DISCOUNT_CARD_PERCENT)) {
            statement.setDouble(1, discount);
            statement.setInt(2, cardNumber);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private DiscountCard createFromResultSet(ResultSet resultSet) throws DaoException {
        DiscountCard card = new DiscountCard();
        try {
            card.setCardId(resultSet.getLong(ColumnName.CARD_ID));
            card.setCardNumber(resultSet.getInt(ColumnName.CARD_NUMBER));
            card.setDiscount(resultSet.getDouble(ColumnName.CARD_DISCOUNT));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return card;
    }
}
