package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCoinReturn {

    private VendingMachine machine;
    private CoinReturn coinReturn;

    @Before
    public void setUp() {
        machine = new VendingMachine();
        coinReturn = new CoinReturn();
    }

    @Test
    public void testCoinReturnIsEmptyWhenNoCoinsAdded() {
        Assert.assertArrayEquals(new Coin[]{}, coinReturn.getReturnedCoins().toArray());
    }

    @Test
    public void testCannotAddCoinTwice() {
        Coin penny = new Coin(Currency.PENNY);
        try {
            coinReturn.add(penny);
            coinReturn.add(penny);
            Assert.fail("Shoul not have been able to add same coin twice");
        } catch (IllegalArgumentException e) {}
    }
}