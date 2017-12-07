package org.jtodd.vend;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private final String INSERT_COIN = "INSERT COIN";
    private String display;
    private int depositedAmount;
    private List<Coin> returnedCoins;

    public VendingMachine() {
        display = INSERT_COIN;
        depositedAmount = 0;
        returnedCoins = new ArrayList<>();
    }

    public boolean accept(Coin coin) {
        Currency depositedCoin = Currency.getByCoin(coin);
        if (depositedCoin == Currency.NICKEL ||
            depositedCoin == Currency.DIME ||
            depositedCoin == Currency.QUARTER) {
            depositedAmount += depositedCoin.value;
            display = String.format("$%.2f", ((double) depositedAmount / 100));
            return true;
        } else {
            returnedCoins.add(coin);
            return false;
        }
    }

    public String getDisplay() {
        return display;
    }

    public List<Coin> getReturnedCoins() {
        return returnedCoins;
    }
}
