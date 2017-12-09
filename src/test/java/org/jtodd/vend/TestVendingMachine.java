package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestVendingMachine {

    private VendingMachine machine;

    @Before
    public void setUp() {
        machine = new VendingMachine();
    }

    @Test
    public void testSimplePurchase() {
        // John wants a snack. The vending machine invites him to deposit some coins.
        Assert.assertEquals("INSERT COIN", machine.getDisplay());

        // He checks the coin return to see if the previous customer left any coins
        // behind. They didn't.
        Assert.assertArrayEquals(new Coin [] {}, machine.getReturnedCoins().toArray());

        // Fortunately, he has a pocket full of change, so he deposits some into the
        // vending machine. It tells him how much has been deposited.
        Coin nickel = new Coin(Currency.NICKEL);
        Coin dime = new Coin(Currency.DIME);
        Coin quarter = new Coin(Currency.QUARTER);
        machine.accept(nickel);
        machine.accept(dime);
        machine.accept(quarter);
        Assert.assertEquals("$0.40", machine.getDisplay());
    }

    @Test
    public void testInsertsInvalidCurrency() {
        // John wants a snack, but he only has a dime, a nickel, and a penny. Inserting the valid
        // currency increases the amount deposited, but the penny is returned.
        Coin penny = new Coin(Currency.PENNY);
        Coin nickel = new Coin(Currency.NICKEL);
        Coin dime = new Coin(Currency.DIME);
        machine.accept(penny);
        machine.accept(nickel);
        machine.accept(dime);
        Assert.assertEquals("$0.15", machine.getDisplay());
        Assert.assertArrayEquals(new Coin [] {penny}, machine.getReturnedCoins().toArray());
    }
}
