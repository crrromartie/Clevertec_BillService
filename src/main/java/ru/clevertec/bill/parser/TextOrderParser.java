package ru.clevertec.bill.parser;

import java.util.Map;

public interface TextOrderParser {

    Map<Long, Integer> parsOrderParameters(String line);

    int parsCardNumber(String line);
}
