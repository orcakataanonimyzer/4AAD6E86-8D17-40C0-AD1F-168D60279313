package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCoinReturn {

    private CoinReturn coinReturn;

    @Before
    public void setUp() {
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
            Assert.fail("Should not have been able to add same coin twice");
        } catch (IllegalArgumentException e) {}
    }
}