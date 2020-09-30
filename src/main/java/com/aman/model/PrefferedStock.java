package com.aman.model;

public class PrefferedStock extends Stock {
    private float fixedDividend;

    public PrefferedStock(String name, int lastDividend, float fixedDividend, int parValue) {
        super(name, lastDividend, parValue);
        this.fixedDividend = fixedDividend;
    }

    @Override
    public float dividendYield(float price) {
        return fixedDividend * parValue / price;
    }
}
