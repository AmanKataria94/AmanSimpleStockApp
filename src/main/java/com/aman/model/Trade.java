package com.aman.model;

import java.time.LocalDateTime;

public class Trade {
    private Stock stock;
    private int numberOfShares;
    private Indicator indicator;
    private LocalDateTime timestamp;
    private float tradedPrice;

    public Trade(Stock stock, int numberOfShares, Indicator indicator, float tradedPrice) {
        this.stock = stock;
        this.numberOfShares = numberOfShares;
        this.indicator = indicator;
        this.tradedPrice = tradedPrice;
        timestamp = LocalDateTime.now();
    }

    public Stock getStock() {
        return stock;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public float getTradedPrice() {
        return tradedPrice;
    }
}
