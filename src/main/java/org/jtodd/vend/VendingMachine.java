package org.jtodd.vend;

public class VendingMachine {

    public boolean accept(Coin coin) {
        if (coin.mass < 3) {
            return false;
        } else {
            return true;
        }
    }
}
