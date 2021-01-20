package ru.clevertec.bill.parser;

public interface CustomJsonParser {
    String parseToJson(Object[] objects) throws IllegalAccessException;

    String parseToJson(Object object) throws IllegalAccessException;
}
