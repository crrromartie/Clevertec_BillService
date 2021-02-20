package ru.clevertec.bill.observer;

import ru.clevertec.bill.observer.entity.State;

public interface EventListener {

    void update(State eventType, String message);
}
