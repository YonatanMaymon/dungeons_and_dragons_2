package event_handler;

import java.util.ArrayList;
import java.util.HashMap;

public class EventManager{
    private static EventManager instance;
    private final HashMap<String, ArrayList<EventListener>> listeners  = new HashMap<>();

    public static EventManager getInstance(){
        if (instance == null)
            instance = new EventManager();
        return instance;
    }

    public void subscribe(String eventType, EventListener listener){
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void un_subscribe(String eventType, EventListener listener){
        ArrayList<EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners!=null){
            eventListeners.remove(listener);
            if (eventListeners.isEmpty())
                listeners.remove(eventType);
        }
    }

    public void notify(String eventType){
        ArrayList<EventListener> eventListeners = listeners.get(eventType);
        for(EventListener listener : eventListeners){
            listener.update();
        }
    }
}
