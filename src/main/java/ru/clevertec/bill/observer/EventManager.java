package ru.clevertec.bill.observer;

import ru.clevertec.bill.observer.entity.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<State, List<EventListener>> listeners = new HashMap<>();

    public EventManager(State... states) {
        for (State state : states) {
            this.listeners.put(state, new ArrayList<>());
        }
    }

    public void subscribe(State eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(State eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(State eventType, String message) {
        List<EventListener> eventListeners = listeners.get(eventType);
        for (EventListener eventListener : eventListeners) {
            eventListener.update(eventType, message);
        }
    }
}
