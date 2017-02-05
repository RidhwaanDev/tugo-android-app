package com.ridhwaan.any.tugo;

import java.util.ArrayList;

/**
 * Created by Ridhwaan on 2/3/17.
 */
public class EventStash {



    private static EventStash ourInstance = new EventStash();

    public ArrayList<EventObject> sListOfEvents = new ArrayList<>();

    public static EventStash getInstance() {

        return ourInstance;
    }

    private EventStash() {

    }

    public void addEvent(EventObject e){
        sListOfEvents.add(e);
    }
}
