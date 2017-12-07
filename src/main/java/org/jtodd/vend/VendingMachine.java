package org.jtodd.vend;

public class VendingMachine {

    public boolean accept(Coin coin) {
        Currency depositedCoin = Currency.getByCoin(coin);
        if (depositedCoin == Currency.NICKEL ||
            depositedCoin == Currency.DIME ||
            depositedCoin == Currency.QUARTER) {
            return true;
        } else {
            return false;
        }
    }

    public String getDisplay() {
        return "INSERT COIN";
    }
}
