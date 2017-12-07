package org.jtodd.vend;

public class VendingMachine {

    public boolean accept(Coin coin) {
        if ((coin.diameter == 21.21 && coin.mass == 5.0) ||
            (coin.diameter == 17.91 && coin.mass == 2.268) ||
            (coin.diameter == 24.26 && coin.mass == 5.67)) {
            return true;
        } else {
            return false;
        }
    }
}
