package org.jtodd.vend;

import java.util.Optional;

public class CurrencyAcceptor {

    public Optional<Currency> accept(Coin coin) {
        Currency depositedCoin = Currency.getByCoin(coin);
        if (depositedCoin == Currency.NICKEL ||
                depositedCoin == Currency.DIME ||
                depositedCoin == Currency.QUARTER) {
            return Optional.of(depositedCoin);
        } else {
            return Optional.empty();
        }
    }
}
