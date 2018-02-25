package com.example.st1drawermenu.NavPackage.Notices;

/**
 * Created by Administrator on 2018-02-09.
 */

public class Statistics {

    String Statistics;
    String name;
    String date;


    public Statistics(String statistics, String name, String date) {
        Statistics = statistics;
        this.name = name;
        this.date = date;
    }

    public String getStatistics() {
        return Statistics;
    }

    public void setStatistics(String statistics) {
        Statistics = statistics;
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
