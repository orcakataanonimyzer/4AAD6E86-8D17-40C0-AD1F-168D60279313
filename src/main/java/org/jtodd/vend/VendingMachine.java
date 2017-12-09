package org.jtodd.vend;

import java.util.Collection;
import java.util.Optional;

public class VendingMachine {

    private final String INSERT_COIN = "INSERT COIN";
    private CurrencyAcceptor acceptor;
    private Display display;
    private int depositedAmount;
    private CoinReturn returnedCoins;

    public VendingMachine() {
        acceptor = new CurrencyAcceptor();
        display = new Display();
        returnedCoins = new CoinReturn();
        depositedAmount = 0;
    }

    public void accept(Coin coin) {
        Optional<Currency> testResult = acceptor.accept(coin);
        if (testResult.isPresent()) {
            depositedAmount += testResult.get().value;
            display.updateAmount(depositedAmount);
        } else {
            returnedCoins.add(coin);
        }
    }

    public String getDisplay() {
        return display.getMessage();
    }

    public Collection<Coin> getReturnedCoins() {
        return returnedCoins.getReturnedCoins();
    }
}
