package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDisplay {

    private VendingMachine machine;

    @Before
    public void setUp() {
        machine = new VendingMachine();
    }

    @Test
    public void testDisplayWhenNoCoinsHaveBeenInserted() {
        Assert.assertEquals("Wrong starting display given", "INSERT COIN", machine.getDisplay());
    }

    @Test
    public void testDisplaysCorrectDepositedAmount() {
        machine.accept(new Coin(Currency.NICKEL));
        Assert.assertEquals("$0.05", machine.getDisplay());
        machine.accept(new Coin(Currency.QUARTER));
        Assert.assertEquals("$0.30", machine.getDisplay());
    }

    @Test
    public void testDisplayInsertCoinWhenInvalidCoinDeposited() {
        machine.accept(new Coin(0.5, 0.5));
        Assert.assertEquals("INSERT COIN", machine.getDisplay());
    }

    @Test
    public void testDisplayInsertCoinWhenValidCoinThenInvalidCoinDeposited() {
        machine.accept(new Coin(Currency.NICKEL));
        machine.accept(new Coin(0.5, 0.5));
        Assert.assertEquals("$0.05", machine.getDisplay());
    }

    @Test
    public void testDisplaysCorrectDepositedAmountWhenInvalidCoinThenValidCoinDeposited() {
        machine.accept(new Coin(0.5, 0.5));
        machine.accept(new Coin(Currency.NICKEL));
        Assert.assertEquals("$0.05", machine.getDisplay());
    }
}
