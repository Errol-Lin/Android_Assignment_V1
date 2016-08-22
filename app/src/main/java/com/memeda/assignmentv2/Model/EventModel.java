package com.memeda.assignmentv2.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Errol on 19/08/16.
 */
public class EventModel {

    private final String LOG_TAG = this.getClass().getName();
    List<Events> events = new ArrayList<Events>();

    private static EventModel singletonInstance;

    public static EventModel getSingletonInstance() {
        if(singletonInstance == null){
            singletonInstance = new EventModel();
        }
        return singletonInstance;
    }

    private EventModel(){
        for(int i = 20; i>= 1; i--){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH,i);
//            Events event = new SimpleEvents("event"+i, calendar.getTimeInMillis());
//            addEvent(event);
//            Log.i(LOG_TAG,event.toString());
        }
    }

    public Events getEventByID(String id){
        for(Events event : events){
            if(event.getId().equals(id)){
                return event;
            }
        }

        return null;
    }

    public List<Events> getEvents() {
        return events;
    }

    public boolean addEvent(Events event){
        return events.add(event);
    }
}
