package com.memeda.assignmentv2.Model;

/**
 * Created by Errol on 19/08/16.
 */
public interface Events {
    String getId();

    String getEventTitle();

    void setEventTitle(String eventTitle);

    long getStartDate();

    void setStartDate(long startDate);

    long getEndDate();

    void setEndDate(long endDate);
}
