package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCoinReturn {

    private VendingMachine machine;

    @Before
    public void setUp() {
        machine = new VendingMachine();
    }


    @Test
    public void testCoinReturnIsEmptyWhenNoCoinsAdded() {
        Assert.assertArrayEquals(new Coin[]{}, machine.getReturnedCoins().toArray());
    }

    @Test
    public void testInvalidCoinReturned() {
        Coin coin = new Coin(0.5, 0.5);
        machine.accept(coin);
        Assert.assertArrayEquals(new Coin[]{coin}, machine.getReturnedCoins().toArray());
    }
}