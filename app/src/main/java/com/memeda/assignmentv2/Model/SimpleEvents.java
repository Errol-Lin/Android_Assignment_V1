package com.memeda.assignmentv2.Model;

/**
 * Created by Errol on 19/08/16.
 */
public class SimpleEvents implements Events {

    private String id;
    private String eventTitle;
    private long startDate;
    private long endDate;

    public SimpleEvents(String eventTitle) {
        this.eventTitle = eventTitle;
//        this.startDate = startDate;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getEventTitle() {
        return eventTitle;
    }

    @Override
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    @Override
    public long getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    @Override
    public long getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String toString(){
        return String.format("Event: " + eventTitle);
    }
}
