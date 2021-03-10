package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.builder.DiscountCardBuilder;
import ru.clevertec.bill.builder.impl.DiscountCardBuilderImpl;
import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.model.dao.DiscountCardDao;
import ru.clevertec.bill.model.pool.ConnectionPool;
import ru.clevertec.custom.CustomArrayList;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DiscountCardDaoImpl implements DiscountCardDao {
    private static final String FIND_CARD_BY_ID = "SELECT card_id, card_number, discount_percent " +
            "FROM discount_card WHERE card_id = ?";
    private static final String FIND_ALL_CARDS = "SELECT card_id, card_number, discount_percent FROM discount_card";
    private static final String ADD_CARD = "INSERT INTO discount_card (card_number, discount_percent) VALUES(?, ?)";
    private static final String EDIT_DISCOUNT_CARD = "UPDATE discount_card " +
            "SET card_number = ?, discount_percent = ? WHERE card_id = ?";
    private static final String DELETE_CARD_BY_ID = "DELETE FROM discount_card WHERE card_id = ?";
    private static final String FIND_CARD_BY_NUMBER = "SELECT card_id, card_number, discount_percent " +
            "FROM discount_card WHERE card_number = ?";

    @Override
    public Optional<DiscountCard> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARD_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Optional<DiscountCard> cardOptional = Optional.empty();
            if (resultSet.next()) {
                cardOptional = Optional.of(createFromResultSet(resultSet));
            }
            return cardOptional;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<DiscountCard> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CARDS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<DiscountCard> cardList = new CustomArrayList<>();
            while (resultSet.next()) {
                cardList.add(createFromResultSet(resultSet));
            }
            return cardList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<DiscountCard> add(DiscountCard card) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(ADD_CARD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, card.getCardNumber());
            preparedStatement.setInt(2, card.getDiscountPercent());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                card.setCardId(generatedKeys.getLong(ColumnName.CARD_ID));
            }
            return Optional.of(card);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<DiscountCard> edit(DiscountCard discountCard) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(EDIT_DISCOUNT_CARD)) {
            preparedStatement.setInt(1, discountCard.getCardNumber());
            preparedStatement.setInt(2, discountCard.getDiscountPercent());
            preparedStatement.setLong(3, discountCard.getCardId());
            preparedStatement.executeUpdate();
            return Optional.of(discountCard);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARD_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<DiscountCard> findByNumber(int cardNumber) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARD_BY_NUMBER)) {
            preparedStatement.setInt(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            Optional<DiscountCard> cardOptional = Optional.empty();
            if (resultSet.next()) {
                cardOptional = Optional.of(createFromResultSet(resultSet));
            }
            return cardOptional;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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
