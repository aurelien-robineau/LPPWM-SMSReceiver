package com.lppwm.smsreceiver.models;

public class StatModel {
    private static int lastId = 0;

    private final int id;
    private final String name;
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
