package org.jtodd.vend;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VendingMachine {

    private final String INSERT_COIN = "INSERT COIN";
    private CurrencyAcceptor acceptor;
    private String display;
    private int depositedAmount;
    private List<Coin> returnedCoins;

    public VendingMachine() {
        acceptor = new CurrencyAcceptor();
        display = INSERT_COIN;
        depositedAmount = 0;
        returnedCoins = new ArrayList<>();
    }

    public void accept(Coin coin) {
        Optional<Currency> testResult = acceptor.accept(coin);
        if (testResult.isPresent()) {
            depositedAmount += testResult.get().value;
            display = String.format("$%.2f", ((double) depositedAmount / 100));
        } else {
            returnedCoins.add(coin);
        }
    }

    public String getDisplay() {
        return display;
    }

    public List<Coin> getReturnedCoins() {
        return returnedCoins;
    }
}
