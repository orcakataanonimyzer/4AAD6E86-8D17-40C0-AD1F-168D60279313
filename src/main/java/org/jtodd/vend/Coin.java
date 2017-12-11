package org.jtodd.vend;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static boolean compareCoinLists(List<Coin> l1, List<Coin> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        List<Map.Entry<Coin, Currency>> valuedList1 = l1.stream().map(c -> new AbstractMap.SimpleEntry<>(c, Currency.getByCoin(c))).collect(Collectors.toList());
        List<Map.Entry<Coin, Currency>> valuedList2 = l2.stream().map(c -> new AbstractMap.SimpleEntry<>(c, Currency.getByCoin(c))).collect(Collectors.toList());
        Collections.sort(valuedList1, (e1, e2) -> e1.getValue().value - e2.getValue().value);
        Collections.sort(valuedList2, (e1, e2) -> e1.getValue().value - e2.getValue().value);
        for (int i = 0; i < valuedList1.size(); ++i) {
            if (valuedList1.get(i).getValue().value != valuedList2.get(i).getValue().value) {
                return false;
            }
        }
        return true;
    }
}
