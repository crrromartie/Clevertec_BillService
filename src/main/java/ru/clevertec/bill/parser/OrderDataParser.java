package ru.clevertec.bill.parser;

import ru.clevertec.bill.validator.OrderFileValidator;

import java.util.HashMap;
import java.util.Map;

public final class OrderDataParser {
    private static final String REGEX_DELIMITER = "\\s+";
    private static final String REGEX_DELIMITER_PARAM = "-";

    public Map<Long, Integer> parsOrderParameters(String line) {
        Map<Long, Integer> purchaseParams = new HashMap<>();
        String[] elements = line.strip().split(REGEX_DELIMITER);
        for (String element : elements) {
            if (OrderFileValidator.isParametersValid(element)) {
                Long key = Long.parseLong(element.strip().split(REGEX_DELIMITER_PARAM)[0]);
                Integer value = Integer.parseInt(element.strip().split(REGEX_DELIMITER_PARAM)[1]);
                purchaseParams.put(key, value);
            }
        }
        return purchaseParams;
    }

    public int parsCardNumber(String line) {
        int cardNumber = 0;
        String[] elements = line.strip().split(REGEX_DELIMITER);
        for (String element : elements) {
            if (OrderFileValidator.isCardValid(element)) {
                cardNumber = Integer.parseInt(element.strip().split(REGEX_DELIMITER_PARAM)[1]);
            }
        }
        return cardNumber;
    }
}
