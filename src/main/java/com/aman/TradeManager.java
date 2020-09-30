package com.aman;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.aman.model.Indicator;
import com.aman.model.Stock;
import com.aman.model.Trade;

public class TradeManager {
    private List<Trade> trades;

    TradeManager() {
        trades = new ArrayList<>();
    }

    public void placeTrade(Scanner scanner, Stock stock, float price) {
        Indicator indicator = getIndicator(scanner);
        int numberOfShares = getNumberOfShares(scanner);
        Trade trade = new Trade(stock, numberOfShares, indicator, price);
        trades.add(trade);
    }

    private Indicator getIndicator(Scanner scanner) {
        System.out.println("Would you like to buy or sell? b/s");
        String placeTrade = scanner.nextLine();
        if (placeTrade.equalsIgnoreCase("b") || placeTrade.equalsIgnoreCase("buy")) {
            return Indicator.BUY;
        } else if (placeTrade.equalsIgnoreCase("s") || placeTrade.equalsIgnoreCase("sell")) {
            return Indicator.SELL;
        } else {
            return getIndicator(scanner);
        }
    }

    private int getNumberOfShares(Scanner scanner) {
        System.out.println("Please enter number of shares to trade:");
        final String input = scanner.nextLine();
        try {
            int numberOfShares = Integer.parseInt(input);
            if (numberOfShares < 1) {
                return handleNumberOfSharesException(scanner);
            }
            return numberOfShares;
        } catch (NumberFormatException ex) {
            return handleNumberOfSharesException(scanner);
        }
    }

    public float getVolumeWeightedStockPrice(Stock stock) {
        LocalDateTime time15MinsAgo = LocalDateTime.now().minus(15, ChronoUnit.MINUTES);
        List<Trade> applicableTrades = trades.stream()
                .filter(trade -> trade.getStock() == stock && trade.getTimestamp().isAfter(time15MinsAgo))
                .collect(Collectors.toList());

        float priceQuantitySummation = 0;
        float quantitySummation = 0;
        for (Trade trade : applicableTrades) {
            priceQuantitySummation += trade.getNumberOfShares() * trade.getTradedPrice();
            quantitySummation += trade.getNumberOfShares();
        }
        ;

        return priceQuantitySummation / quantitySummation;
    }

    private int handleNumberOfSharesException(Scanner scanner) {
        System.out.println("Please enter a number!");
        return getNumberOfShares(scanner);
    }

    public double getGeometricMean() {
        double sum = 1;
        for (Trade trade : trades) {
            sum *= trade.getTradedPrice();
        }

        double geoMean = Math.pow(sum, 1 / trades.size());
        return geoMean;
    }
}
