package com.lppwm.smsreceiver.models;

public class SMSModel {
    private static int lastId = 0;

    private final int id;
    private final String body;

    public SMSModel(String body) {
        this.id = lastId++;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }
}
