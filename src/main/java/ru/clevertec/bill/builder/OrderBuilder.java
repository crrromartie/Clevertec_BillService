package ru.clevertec.bill.builder;

import ru.clevertec.bill.parser.OrderDataParser;
import ru.clevertec.bill.entity.Order;
import ru.clevertec.bill.reader.DataReader;

import java.io.File;
import java.util.Map;

public final class OrderBuilder {
    public Order buildOrder(File file) {
        DataReader reader = new DataReader();
        String text = reader.readData(file);

        OrderDataParser parser = new OrderDataParser();
        Map<Integer, Integer> purchaseParameters = parser.parsDataParameters(text);
        int cardNumber = parser.parsDataCard(text);

        Order order = new Order();
        order.setPurchaseParameters(purchaseParameters);
        order.setCurdNumber(cardNumber);
        return order;
    }
}
