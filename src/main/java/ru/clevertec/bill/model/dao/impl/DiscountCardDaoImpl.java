package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.builder.DiscountCardBuilder;
import ru.clevertec.bill.builder.impl.DiscountCardBuilderImpl;
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
    private static final String FIND_CARD_BY_ID = "SELECT card_id, card_number, discount_percent " +
            "FROM discount_card WHERE card_id = ?";
    private static final String FIND_ALL_CARDS = "SELECT card_id, card_number, discount_percent FROM discount_card";
    private static final String ADD_CARD = "INSERT INTO discount_card (card_number, discount_percent) VALUES(?, ?)";
    private static final String UPDATE_DISCOUNT_CARD = "UPDATE discount_card " +
            "SET card_number = ?, discount_percent = ? WHERE card_id = ?";
    private static final String DELETE_CARD_BY_ID = "DELETE FROM discount_card WHERE card_id = ?";
    private static final String SELECT_CARD_BY_NUMBER = "SELECT card_id, card_number, discount_percent " +
            "FROM discount_card WHERE card_number = ?";

    @Override
    public Optional<DiscountCard> findById(long id) throws DaoException {
        Optional<DiscountCard> cardOptional = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CARD_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DiscountCard discountCard = createFromResultSet(resultSet);
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
    public List<DiscountCard> findAll() throws DaoException {
        List<DiscountCard> cardList = new CustomArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CARDS)) {
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
    public boolean add(DiscountCard card) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_CARD)) {
            statement.setInt(1, card.getCardNumber());
            statement.setInt(2, card.getDiscountPercent());
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<DiscountCard> edit(DiscountCard discountCard) throws DaoException {
        Optional<DiscountCard> optionalDiscountCard = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement updateStatment = connection.prepareStatement(UPDATE_DISCOUNT_CARD);
             PreparedStatement statement = connection.prepareStatement(FIND_CARD_BY_ID)) {
            updateStatment.setInt(1, discountCard.getCardNumber());
            updateStatment.setInt(2, discountCard.getDiscountPercent());
            updateStatment.setLong(3, discountCard.getCardId());
            updateStatment.executeUpdate();

            statement.setLong(1, discountCard.getCardId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DiscountCard updatedDiscountCard = createFromResultSet(resultSet);
                optionalDiscountCard = Optional.of(updatedDiscountCard);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalDiscountCard;
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CARD_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<DiscountCard> findByNumber(int cardNumber) throws DaoException {
        Optional<DiscountCard> cardOptional = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CARD_BY_NUMBER)) {
            statement.setInt(1, cardNumber);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DiscountCard discountCard = createFromResultSet(resultSet);
                cardOptional = Optional.of(discountCard);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return cardOptional;
    }

    private DiscountCard createFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            DiscountCardBuilder discountCardBuilder = new DiscountCardBuilderImpl();
            discountCardBuilder.setCardId(resultSet.getLong(ColumnName.CARD_ID));
            discountCardBuilder.setCardNumber(resultSet.getInt(ColumnName.CARD_NUMBER));
            discountCardBuilder.setDiscountPercent(resultSet.getInt(ColumnName.CARD_DISCOUNT));
            return discountCardBuilder.getDiscountCard();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
