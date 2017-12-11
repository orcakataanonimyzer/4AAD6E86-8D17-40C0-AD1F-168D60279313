package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCoin {

    @Test
    public void testOnlyValidValuesAreAccepted() {
        try {
            new Coin(0, 0);
            Assert.fail("Should have thrown on invalid constructor arguments");
        } catch (Exception e) {}
    }

    @Test
    public void testConstructorWithCurrency() {
        Coin coin = new Coin(Currency.PENNY);
        Assert.assertEquals(Currency.PENNY.diameter, coin.diameter, 1e-6);
        Assert.assertEquals(Currency.PENNY.mass, coin.mass, 1e-6);
    }

    @Test
    public void testEquals() {
        Coin n1 = new Coin(Currency.NICKEL);
        Coin n2 = new Coin(Currency.NICKEL);
        Coin d  = new Coin(Currency.DIME);
        Assert.assertTrue(Coin.compare(n1, n2));
        Assert.assertFalse(Coin.compare(n1, d));
    }

    @Test
    public void testCompareCoinLists() {
        List<Coin> l1 = new ArrayList<>();
        l1.add(new Coin(1, 1));
        l1.add(new Coin(Currency.NICKEL));
        l1.add(new Coin(Currency.DIME));
        l1.add(new Coin(Currency.PENNY));
        l1.add(new Coin(0.5, 0.5));
        l1.add(new Coin(Currency.NICKEL));

        List<Coin> l2 = new ArrayList<>();
        l2.add(new Coin(2, 2));
        l2.add(new Coin(3, 3));
        l2.add(new Coin(Currency.PENNY));
        l2.add(new Coin(Currency.NICKEL));
        l2.add(new Coin(Currency.NICKEL));
        l2.add(new Coin(Currency.DIME));

        List<Coin> l3 = new ArrayList<>();

        Assert.assertTrue(Coin.compareCoinLists(l1, l2));
        Assert.assertFalse(Coin.compareCoinLists(l1, l3));
    }
}
