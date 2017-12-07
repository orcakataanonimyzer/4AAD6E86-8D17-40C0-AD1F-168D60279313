package org.jtodd.vend;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

// Currency specifications from
// https://www.usmint.gov/learn/coin-and-medal-programs/coin-specifications
public enum Currency {
    PENNY(1, 19.05, 2.5),
    NICKEL(5, 21.21, 5.0),
    DIME(10, 17.91, 2.268),
    QUARTER(25, 24.26, 5.67),
    HALF_DOLLAR(50, 30.61, 11.34),
    DOLLAR(100, 26.49, 8.1),
    SLUG(0, -1, -1);

    public final int value;
    public final double diameter;
    public final double mass;

    private static Map<Map.Entry<Double, Double>, Currency> lookupHash;

    static {
        lookupHash = new HashMap<Map.Entry<Double, Double>, Currency>();
        for(Currency c : Currency.values()) {
            AbstractMap.Entry<Double, Double> key = new AbstractMap.SimpleEntry<>(c.diameter, c.mass);
            lookupHash.put(key, c);
        }
    }

    private Currency(int value, double diameter, double mass) {
        this.value = value;
        this.diameter = diameter;
        this.mass = mass;
    }

    public static Currency getByDiameterAndMass(double diameter, double mass) {
        AbstractMap.SimpleEntry<Double, Double> testKey = new AbstractMap.SimpleEntry<>(diameter, mass);
        return lookupHash.getOrDefault(testKey, SLUG);
    }

    public static Currency getByCoin(Coin coin) {
        return getByDiameterAndMass(coin.diameter, coin.mass);
    }
}
