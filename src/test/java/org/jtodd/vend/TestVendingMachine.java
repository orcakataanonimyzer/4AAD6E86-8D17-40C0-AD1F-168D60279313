package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Test;

public class TestVendingMachine {

    @Test
    public void testAcceptsValidCoin() {
        VendingMachine machine = new VendingMachine();
        Coin nickel = new Coin(21.21, 5);
        Assert.assertTrue("Vending machine should have accepted valid coin", machine.accept(nickel));
    }

    @Test
    public void testDoesNotAcceptInvalidCoin() {
        VendingMachine machine = new VendingMachine();
        Coin penny = new Coin(19.05, 2.5);
        Assert.assertFalse("Vending machine should not have accepted invalid coin", machine.accept(penny));
    }
}
