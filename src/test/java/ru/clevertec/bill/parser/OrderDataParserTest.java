package ru.clevertec.bill.parser;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class OrderDataParserTest {
    OrderDataParser parser;
    Map<Long, Integer> parameters;
    String text1 = "1-2 2-3";
    String text2 = "1--2 2-3";

    @BeforeEach
    void setUp() {
        parser = new OrderDataParser();
        parameters = new HashMap<>();
        parameters.put(1L, 2);
        parameters.put(2L, 3);
    }

    @AfterEach
    void tearDown() {
        parser = null;
        parameters = null;
        text1 = null;
        text2 = null;
    }

    @Test
    void parsOrderParametersPositiveTest() {
        Map<Long, Integer> actual = parser.parsOrderParameters(text1);
        Map<Long, Integer> expected = parameters;
        assertEquals(actual, expected);
    }

    @Test
    void parsOrderParametersNegativeTest() {
        Map<Long, Integer> actual = parser.parsOrderParameters(text2);
        Map<Long, Integer> expected = parameters;
        assertNotEquals(actual, expected);
    }
}
