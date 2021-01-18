package ru.clevertec.bill.parser;

import ru.clevertec.bill.validator.OrderValidator;

import java.util.HashMap;
import java.util.Map;

public final class OrderDataParser {
    private static final String REGEX_DELIMITER = "\\s+";
    private static final String REGEX_DELIMITER_PARAM = "-";

    public Map<Integer, Integer> parsDataParameters(String line) {
        Map<Integer, Integer> purchaseParams = new HashMap<>();
        String[] elements = line.strip().split(REGEX_DELIMITER);
        for (String element : elements) {
            if (OrderValidator.isParametersValid(element)) {
                Integer key = Integer.parseInt(element.strip().split(REGEX_DELIMITER_PARAM)[0]);
                Integer value = Integer.parseInt(element.strip().split(REGEX_DELIMITER_PARAM)[1]);
                purchaseParams.put(key, value);
            }
        }
        return purchaseParams;
    }

    public int parsDataCard(String line) {
        int cardNumber = 0;
        String[] elements = line.strip().split(REGEX_DELIMITER);
        for (String element : elements) {
            if (OrderValidator.isCardValid(element)) {
                cardNumber = Integer.parseInt(element.strip().split(REGEX_DELIMITER_PARAM)[1]);
            }
        }
        return cardNumber;
    }
}
