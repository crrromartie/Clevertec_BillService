package ru.clevertec.bill.parser;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class OrderDataParserTest {
    OrderDataParser parser;
    Map<Integer, Integer> parameters;
    String text1 = "1-2 2-3";
    String text2 = "1--2 2-3";

    @BeforeEach
    void setUp() {
        parser = new OrderDataParser();
        parameters = new HashMap<>();
        parameters.put(1, 2);
        parameters.put(2, 3);
    }

    @AfterEach
    void tearDown() {
        parser = null;
        parameters = null;
        text1 = null;
        text2 = null;
    }

    @Test
    void parsDataParametersPositiveTest() {
        Map<Integer, Integer> actual = parser.parsDataParameters(text1);
        Map<Integer, Integer> expected = parameters;
        assertEquals(actual, expected);
    }

    @Test
    void parsDataParametersNegativeTest() {
        Map<Integer, Integer> actual = parser.parsDataParameters(text2);
        Map<Integer, Integer> expected = parameters;
        assertNotEquals(actual, expected);
    }
}
