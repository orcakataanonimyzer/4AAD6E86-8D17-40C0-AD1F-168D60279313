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
    public void testAcceptsValidCoin() {
        Coin nickel = new Coin(21.21, 5);
        Coin dime = new Coin(17.91, 2.268);
        Coin quarter = new Coin(24.26, 5.67);
        Assert.assertTrue("Vending machine should have accepted valid coin", machine.accept(nickel));
        Assert.assertTrue("Vending machine should have accepted valid coin", machine.accept(dime));
        Assert.assertTrue("Vending machine should have accepted valid coin", machine.accept(quarter));
    }

    @Test
    public void testDoesNotAcceptInvalidCoin() {
        Coin penny = new Coin(19.05, 2.5);
        Coin halfDollar = new Coin(30.61, 11.34);
        Coin dollar = new Coin(26.49, 8.1);
        Assert.assertFalse("Vending machine should not have accepted invalid coin", machine.accept(penny));
        Assert.assertFalse("Vending machine should not have accepted invalid coin", machine.accept(halfDollar));
        Assert.assertFalse("Vending machine should not have accepted invalid coin", machine.accept(dollar));
    }

    @Test
    public void testDisplayWhenNoCoinsHaveBeenInserted() {
        Assert.assertEquals("Wrong starting display given", "INSERT COIN", machine.getDisplay());
    }
}
