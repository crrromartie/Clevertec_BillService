package ru.clevertec.bill.parser.test_data;

import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private int Age;
    private StudentStatus status;
    private Map<Integer, Map<String, List<Car>>> cars;
    private int[] marks;

    public Student(String name, int age, StudentStatus status, Map<Integer, Map<String, List<Car>>> cars, int[] marks) {
        this.name = name;
        Age = age;
        this.status = status;
        this.cars = cars;
        this.marks = marks;
    }
}