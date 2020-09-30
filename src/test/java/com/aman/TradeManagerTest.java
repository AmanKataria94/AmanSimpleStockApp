package com.aman;

import java.util.Scanner;

import com.aman.model.PrefferedStock;
import com.aman.model.Stock;

import org.junit.Assert;
import org.junit.Test;

public class TradeManagerTest {
    @Test
    public void shouldPlaceTradeAndCalculateVolumeWeightedStockPrice() {
        TradeManager tradeManager = new TradeManager();
        Stock stock = new PrefferedStock("prefStock", 40, 0.04f, 100);
        float buyPrice = 55;
        int numberOfBuyShares = 500;
        float sellPrice = 60;
        int numberOfSellShares = 400;

        String buyStockSeries = "b" + System.lineSeparator() + numberOfBuyShares;
        String sellStockSeries = "s" + System.lineSeparator() + numberOfSellShares;

        float expectedVolumeWeightedStockPrice = (numberOfBuyShares * buyPrice + numberOfSellShares * sellPrice)
                / (numberOfBuyShares + numberOfSellShares);

        Scanner scanner = new Scanner(buyStockSeries + System.lineSeparator() + sellStockSeries);
        tradeManager.placeTrade(scanner, stock, buyPrice);
        tradeManager.placeTrade(scanner, stock, sellPrice);

        float volumeWeightedStockPrice = tradeManager.getVolumeWeightedStockPrice(stock);
        double geometricMean = tradeManager.getGeometricMean();

        Assert.assertEquals(expectedVolumeWeightedStockPrice, volumeWeightedStockPrice, 0.01);
        Assert.assertEquals(1, geometricMean, 0.01);
    }
}
