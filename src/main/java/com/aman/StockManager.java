package com.aman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aman.model.CommonStock;
import com.aman.model.PrefferedStock;
import com.aman.model.Stock;

public class StockManager {
    private List<Stock> stocks;

    public StockManager() {
        stocks = initializeStocks();
    }

    public Stock getSelectedStock(Scanner scanner) {
        System.out.println("Please enter stock you wish to trade, out of - TEA, POP, ALE, GIN, JOE :");
        final String desiredStock = scanner.nextLine();
        Stock selected = stocks.stream().filter(stock -> stock.getName().equalsIgnoreCase(desiredStock)).findFirst()
                .orElse(null);

        if (selected == null) {
            System.out.println("Please enter a valid stock");
            return getSelectedStock(scanner);
        }

        return selected;
    }

    public float getPrice(Scanner scanner) {
        System.out.println("Please enter price at which to trade:");
        final String input = scanner.nextLine();
        try {
            float price = Float.parseFloat(input);
            if (price < 1) {
                return handlePriceException(scanner);
            }
            return price;
        } catch (NumberFormatException ex) {
            return handlePriceException(scanner);
        }
    }

    private float handlePriceException(Scanner scanner) {
        System.out.println("Please enter a valid price!");
        return getPrice(scanner);
    }

    private List<Stock> initializeStocks() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new CommonStock("TEA", 0, 100));
        stocks.add(new CommonStock("POP", 8, 100));
        stocks.add(new CommonStock("ALE", 23, 60));
        stocks.add(new PrefferedStock("GIN", 8, 0.02f, 100));
        stocks.add(new CommonStock("JOE", 13, 250));

        return stocks;
    }
}
