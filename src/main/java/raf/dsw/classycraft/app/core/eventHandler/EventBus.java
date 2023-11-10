package raf.dsw.classycraft.app.core.eventHandler;

import raf.dsw.classycraft.app.core.observer.ISubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private static EventBus instance = null;
    private final Map<EventType, List<ISubscriber>> subscribers = new HashMap<>();

    private EventBus() {}

    public static synchronized EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public synchronized void subscribe(EventType eventType, ISubscriber subscriber) {
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(subscriber);
    }

    public synchronized void unsubscribe(EventType eventType, ISubscriber subscriber) {
        subscribers.getOrDefault(eventType, new ArrayList<>()).remove(subscriber);
    }

    public synchronized void publish(EventType eventType, Object notification) {
        List<ISubscriber> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null) {
            for (ISubscriber subscriber : new ArrayList<>(eventSubscribers)) {
                subscriber.update(notification, eventType);
            }
        }
    }
}
