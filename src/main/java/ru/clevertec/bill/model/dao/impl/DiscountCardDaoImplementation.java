package ru.clevertec.bill.model.dao.impl;

import ru.clevertec.bill.builder.DiscountCardBuilder;
import ru.clevertec.bill.builder.impl.DiscountCardBuilderImpl;
import ru.clevertec.bill.collection.CustomArrayList;
import ru.clevertec.bill.entity.DiscountCard;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.model.dao.AbstractDao;
import ru.clevertec.bill.model.dao.DiscountCardDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

class DiscountCardDaoImplementation extends AbstractDao implements DiscountCardDao {
    private static final String SELECT_ALL_CARD = "SELECT card_id, card_number, discount_percent FROM discount_card";
    private static final String SELECT_CARD_BY_ID = "SELECT card_id, card_number, discount_percent " +
            "FROM discount_card WHERE card_id = ?";
    private static final String ADD_CARD = "INSERT INTO discount_card (card_number, discount_percent) VALUES(?, ?)";
    private static final String DELETE_CARD_BY_ID = "DELETE FROM discount_card WHERE card_id = ?";
    private static final String DELETE_CARD_BY_CARD_NUMBER = "DELETE FROM discount_card WHERE card_number = ?";
    private static final String SELECT_CARD_BY_NUMBER = "SELECT card_id, card_number, discount_percent " +
            "FROM discount_card WHERE card_number = ?";
    private static final String CHANGE_DISCOUNT_PERCENT_BY_ID = "UPDATE discount_card SET discount_percent = ? " +
            "WHERE card_id = ?";
    private static final String CHANGE_DISCOUNT_PERCENT_BY_CARD_NUMBER = "UPDATE discount_card " +
            "SET discount_percent = ? WHERE card_number = ?";

    @Override
    public List<DiscountCard> findAll() throws DaoException {
        List<DiscountCard> cardList = new CustomArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CARD)) {
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
    public Optional<DiscountCard> findById(long id) throws DaoException {
        Optional<DiscountCard> cardOptional = Optional.empty();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CARD_BY_ID)) {
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
    public boolean add(DiscountCard card) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_CARD)) {
            statement.setInt(1, card.getCardNumber());
            statement.setInt(2, card.getDiscountPercent());
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CARD_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(int cardNumber) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CARD_BY_CARD_NUMBER)) {
            statement.setInt(1, cardNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<DiscountCard> findByNumber(int cardNumber) throws DaoException {
        Optional<DiscountCard> cardOptional = Optional.empty();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CARD_BY_NUMBER)) {
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
    public boolean changeDiscountPercent(long id, int discountPercent) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_DISCOUNT_PERCENT_BY_ID)) {
            statement.setLong(1, id);
            statement.setInt(2, discountPercent);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean changeDiscountPercent(int cardNumber, int discountPercent) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_DISCOUNT_PERCENT_BY_CARD_NUMBER)) {
            statement.setInt(1, cardNumber);
            statement.setInt(2, discountPercent);
            return (statement.executeUpdate() == 1);
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
