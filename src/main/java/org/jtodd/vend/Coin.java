package org.jtodd.vend;

public class Coin {

    public final double diameter;
    public final double mass;

    public Coin(double diameter, double mass) {
        if (diameter <= 0 || mass <= 0) {
            throw new IllegalArgumentException(String.format("Diameter and mass values must be greater than 0: %f, %f", diameter, mass));
        }
        this.diameter = diameter;
        this.mass = mass;
    }

    public Coin(Currency currency) {
        this(currency.diameter, currency.mass);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Coin)) {
            return false;
        }
        Coin otherCoin = (Coin) other;
        return this.diameter == otherCoin.diameter && this.mass == otherCoin.mass;
    }
}
