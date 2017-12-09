package org.jtodd.vend;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CoinReturn {

    private Set<Coin> returnedCoins;

    public CoinReturn() {
        returnedCoins = new HashSet<>();
    }

    public void add(Coin coin) {
        if (returnedCoins.contains(coin)) {
            throw new IllegalArgumentException("Cannot add the same coin to the coin return more than once");
        }
        returnedCoins.add(coin);
    }

    public Collection<Coin> getReturnedCoins() {
        return returnedCoins;
    }
}
