package com.lppwm.smsreceiver.models;

public class StatModel {
    private static int lastId = 0;

    private int id;
    private String name;
    private String value;

    public StatModel(String name, String value) {
        this.id = lastId++;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
