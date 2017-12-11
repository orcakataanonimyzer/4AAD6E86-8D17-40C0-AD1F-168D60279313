package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class TestVendingMachine {

    private VendingMachine machine;

    @Before
    public void setUp() {
        machine = new VendingMachine();
    }

    @Test
    public void testMakeChangeValidAmount() {
        Set<Coin> change;
        change = machine.makeChange(10);
        Assert.assertEquals(1, change.size());
        Assert.assertEquals(Currency.DIME, Currency.getByCoin(change.iterator().next()));
        change = machine.makeChange(5);
        Assert.assertEquals(1, change.size());
        Assert.assertEquals(Currency.NICKEL, Currency.getByCoin(change.iterator().next()));
    }

    @Test
    public void testMakeChangeInvalidAmount() {
        try {
            machine.makeChange(-1);
            Assert.fail("Should have failed on attmept to make change for negative amount");
        } catch (IllegalArgumentException e) {}
        try {
            machine.makeChange(1);
            Assert.fail("Should have failed on attmept to make change for amount not divisible by 5");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testCoinRepository() {
        Coin q1 = new Coin(Currency.QUARTER);
        Coin q2 = new Coin(Currency.QUARTER);
        Coin d1 = new Coin(Currency.DIME);
        machine.accept(q1);
        machine.accept(q2);
        machine.accept(d1);
        machine.accept(new Coin(Currency.PENNY));
        Assert.assertArrayEquals(new Coin [] {q1, q2, d1}, machine.getDepositedCoins().keySet().toArray());
    }

    @Test
    public void testCannotInsertSameCoinMoreThanOnce() {
        Coin q = new Coin(Currency.QUARTER);
        machine.accept(q);
        try {
            machine.accept(q);
            Assert.fail("Cannot insert same coin more than once");
        } catch (IllegalArgumentException e) {}
    }
}
