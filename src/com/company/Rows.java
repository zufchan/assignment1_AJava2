package com.company;

import java.text.DateFormat;
import java.util.Date;

public class Rows {
    private String name;
    private String id;
    private Date published_from;
    private Date published_to;
    private double avg_content_length;
    private int total_length;
    private int total_publishes;


    public Rows(String name, String id, Date published_from, Date published_to, double avg_content_length, int total_length, int total_publishes) {
        this.name = name;
        this.id = id;
        this.published_from = published_from;
        this.published_to = published_to;
        this.avg_content_length = avg_content_length;
        this.total_length = total_length;
        this.total_publishes = total_publishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPublished_from() {
        return published_from;
    }

    public void setPublished_from(Date published_from) {
        this.published_from = published_from;
    }

    public Date getPublished_to() {
        return published_to;
    }

    public void setPublished_to(Date published_to) {
        this.published_to = published_to;
    }

    public int getTotal_length() {
        return total_length;
    }

    public void setTotal_length(int total_length) {
        this.total_length = total_length;
    }

    public int getTotal_publishes() {
        return total_publishes;
    }

    public void setTotal_publishes(int total_publishes) {
        this.total_publishes = total_publishes;
    }

    public double getAvg_content_length() {
        return avg_content_length;
    }

    public void setAvg_content_length(double avg_content_length) {
        this.avg_content_length = avg_content_length;
    }
}
