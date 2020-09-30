package com.aman.model;

public abstract class Stock {
    private String name;

    private int lastDividend;

    protected int parValue;

    public Stock(String name, int lastDividend, int parValue) {
        this.name = name;
        this.lastDividend = lastDividend;
        this.parValue = parValue;
    }

    public String getName() {
        return this.name;
    }

    public float dividendYield(float price) {
        return lastDividend / price;
    }

    public float peRatio(float price) {
        return price / lastDividend;
    }
}
