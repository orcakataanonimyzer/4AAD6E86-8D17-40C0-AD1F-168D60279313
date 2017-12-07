package org.jtodd.vend;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private final String INSERT_COIN = "INSERT COIN";
    private String display;
    private int depositedAmount;

    public VendingMachine() {
        display = INSERT_COIN;
        depositedAmount = 0;
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
            return false;
        }
    }

    public String getDisplay() {
        return display;
    }

    public List<Coin> getReturnedCoins() {
        return new ArrayList<Coin>();
    }
}
