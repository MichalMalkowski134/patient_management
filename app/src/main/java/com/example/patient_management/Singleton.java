package com.example.patient_management;

public class Singleton {

    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    private Singleton() {
    }

    private String val;

    public String getValue() {
        return val;
    }

    public void setValue(String value) {
        this.val = value;
    }
}
