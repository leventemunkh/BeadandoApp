package com.example.beadandoapp;

public class Record {
    private int id;
    private String name;
    private double length;
    private int stations;

    public Record(int id, String name, double length, int stations) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.stations = stations;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public int getStations() {
        return stations;
    }
}
