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

    public static boolean compare(Coin c1, Coin c2) {
        Currency cr1 = Currency.getByCoin(c1);
        Currency cr2 = Currency.getByCoin(c2);
        return cr1 == cr2;
    }
}
