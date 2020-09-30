package com.aman;

import java.util.Scanner;

import com.aman.model.Stock;

import org.junit.Assert;
import org.junit.Test;

public class StockManagerTest {
    @Test
    public void shouldReturnCorrectStockOnInput() {
        StockManager stockManager = new StockManager();
        String invalidStock = "invalid";
        String stockToGet = "POP";
        Scanner scanner = new Scanner(invalidStock + System.lineSeparator() + stockToGet);

        Stock popStock = stockManager.getSelectedStock(scanner);
        Assert.assertEquals(popStock.getName(), stockToGet);
    }

    @Test
    public void handlesIncorrectInputAndReturnsPriceAsFloatOnCorrectInput() {
        StockManager stockManager = new StockManager();
        float expectedPrice = 321.4f;
        String invalidPrice = "invalid";
        String inputPrice = "321.4";
        Scanner scanner = new Scanner(invalidPrice + System.lineSeparator() + inputPrice);

        float price = stockManager.getPrice(scanner);
        Assert.assertEquals(expectedPrice, price, 0.01);
    }
}