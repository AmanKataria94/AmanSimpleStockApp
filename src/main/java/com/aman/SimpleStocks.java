package com.aman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aman.model.CommonStock;
import com.aman.model.PrefferedStock;
import com.aman.model.Stock;

public class SimpleStocks {
    private StockManager stockManager;
    private TradeManager tradeManager;

    public void run() {
        final Scanner scanner = new Scanner(System.in);
        tradeManager = new TradeManager();
        stockManager = new StockManager();
        getInputForNewStock(scanner);

        scanner.close();
    }

    private void getInputForNewStock(Scanner scanner) {
        Stock stock = stockManager.getSelectedStock(scanner);
        float price = stockManager.getPrice(scanner);

        System.out.println("Dividend yield for stock is - " + stock.dividendYield(price));
        System.out.println("P/E ratio for stock is - " + stock.peRatio(price));
        checkForTrade(scanner, stock, price);
    }

    private void checkForTrade(Scanner scanner, Stock stock, float price) {
        System.out.println("Would you like to place a trade? y/n");
        String placeTrade = scanner.nextLine();
        if (placeTrade.equalsIgnoreCase("y")) {
            tradeManager.placeTrade(scanner, stock, price);
            displayVolumeWeightedStockPrice(stock);
            displayGeometricMean();
        }
        getInputForNewStock(scanner);
    }

    public void displayVolumeWeightedStockPrice(Stock stock) {
        System.out.println("Volume weighted stock price for " + stock.getName() + " is "
                + tradeManager.getVolumeWeightedStockPrice(stock));
    }

    public void displayGeometricMean() {
        System.out.println("GBCE All Share Index is " + tradeManager.getGeometricMean());
    }
}
