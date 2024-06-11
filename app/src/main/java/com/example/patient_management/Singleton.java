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

    private int val;

    public int getValue() {
        return val;
    }

    public void setValue(int value) {
        this.val = value;
    }
}
