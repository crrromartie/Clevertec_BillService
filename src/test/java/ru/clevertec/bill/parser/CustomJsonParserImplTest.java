package ru.clevertec.bill.parser;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.bill.builder.OrderBuilder;
import ru.clevertec.bill.builder.impl.OrderBuilderImpl;
import ru.clevertec.bill.collection.CustomArrayList;
import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.BillService;
import ru.clevertec.bill.model.service.impl.BillServiceImpl;
import ru.clevertec.bill.parser.impl.CustomJsonParserImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomJsonParserImplTest {
    private CustomJsonParser customParser;
    private Gson gsonParser;

    @BeforeEach
    void setUp() {
        customParser = new CustomJsonParserImpl();
        gsonParser = new Gson();
    }

    @AfterEach
    void tearDown() {
        customParser = null;
        gsonParser = null;
    }

    @Test
    void parseToJsonTest() throws IllegalAccessException, ServiceException {
        Map<Long, Integer> purchasesParameters1 = new HashMap<>();
        purchasesParameters1.put(1L, 6);
        purchasesParameters1.put(2L, 5);
        OrderBuilder orderBuilder1 = new OrderBuilderImpl();
        orderBuilder1.setPurchaseParameters(purchasesParameters1);
        orderBuilder1.setCardNumber(1111);

        Map<Long, Integer> purchasesParameters2 = new HashMap<>();
        purchasesParameters2.put(1L, 6);
        purchasesParameters2.put(3L, 8);
        OrderBuilder orderBuilder2 = new OrderBuilderImpl();
        orderBuilder2.setPurchaseParameters(purchasesParameters2);
        orderBuilder2.setCardNumber(2222);

        BillService billService = BillServiceImpl.getINSTANCE();
        Bill bill1 = billService.makeBill(orderBuilder1.getOrder());
        Bill bill2 = billService.makeBill(orderBuilder2.getOrder());

        List<Bill> bills = new CustomArrayList<>();
        bills.add(bill1);
        bills.add(bill2);

        String actual = customParser.parseToJson(bills);
        String expected = gsonParser.toJson(bills);

        assertEquals(expected, actual);

        Bill[] billArray = new Bill[2];
        billArray[0] = bill1;
        billArray[1] = bill2;

        String actualArrayArg = customParser.parseToJson(billArray);
        String expectedArrayArg = gsonParser.toJson(billArray);

        assertEquals(expectedArrayArg, actualArrayArg);
    }
}