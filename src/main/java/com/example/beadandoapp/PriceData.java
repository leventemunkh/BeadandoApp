package com.example.beadandoapp;

public class PriceData {
    private final String date;
    private final double price;

    public PriceData(String date, double price) {
        this.date = date;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }
}
