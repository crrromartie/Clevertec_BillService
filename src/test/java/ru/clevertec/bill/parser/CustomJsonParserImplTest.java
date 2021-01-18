package ru.clevertec.bill.parser;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.bill.parser.test_data.Car;
import ru.clevertec.bill.parser.test_data.Student;
import ru.clevertec.bill.parser.test_data.StudentStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
    void parseToJsonTest() throws IllegalAccessException {
        Car mazda = new Car("Mazda", "3");
        Car reno = new Car("Reno", "Clio");
        Car lada = new Car("Lada", "Vesta");
        Car ford = new Car("Ford", "Focus");

        List<Car> everyDayPetr = new ArrayList<>();
        everyDayPetr.add(mazda);
        everyDayPetr.add(lada);

        List<Car> everyDayIvan = new ArrayList<>();
        everyDayIvan.add(reno);
        everyDayIvan.add(ford);

        Car bugatti = new Car("Bugatti", "Chiron");
        Car ferrari = new Car("Ferrari", "F-10");
        Car audi = new Car("Audi", "R8");
        Car bmw = new Car("BMW", "M5");

        List<Car> weekendPetr = new ArrayList<>();
        weekendPetr.add(bugatti);
        weekendPetr.add(audi);

        List<Car> weekendIvan = new ArrayList<>();
        weekendIvan.add(ferrari);
        weekendIvan.add(bmw);

        Map<String, List<Car>> petrCar = new HashMap<>();
        petrCar.put("everyday", everyDayPetr);
        petrCar.put("weekend", weekendPetr);

        Map<String, List<Car>> ivanCar = new HashMap<>();
        ivanCar.put("everyday", everyDayIvan);
        ivanCar.put("weekend", weekendIvan);

        Map<Integer, Map<String, List<Car>>> mapCarPetr = new HashMap<>();
        mapCarPetr.put(200, petrCar);
        mapCarPetr.put(300, ivanCar);

        Map<Integer, Map<String, List<Car>>> mapCarIvan = new HashMap<>();
        mapCarIvan.put(100, ivanCar);
        mapCarIvan.put(400, petrCar);

        Student petr = new Student("Petr", 20, StudentStatus.STUDY, mapCarPetr, new int[]{5, 6, 8});
        Student ivan = new Student("Ivan", 23, StudentStatus.GRADUATE, mapCarIvan, new int[]{5, 9, 7});

        List<Student> students = new ArrayList<>();
        students.add(petr);
        students.add(ivan);

        String actual = customParser.parseToJson(students);
        String expected = gsonParser.toJson(students);

        assertEquals(expected, actual);

        Student[] studentArray = new Student[2];
        studentArray[0] = petr;
        studentArray[1] = ivan;

        String actualArrayArg = customParser.parseToJson(studentArray);
        String expectedArrayArg = gsonParser.toJson(studentArray);

        assertEquals(expectedArrayArg, actualArrayArg);
    }
}