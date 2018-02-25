package com.example.st1drawermenu.NavPackage.Event;

/**
 * Created by Administrator on 2018-02-09.
 */

public class Event {

    String event;
    String name;
    String date;

    public Event(String event, String name, String date) {
        this.event = event;
        this.name = name;
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
