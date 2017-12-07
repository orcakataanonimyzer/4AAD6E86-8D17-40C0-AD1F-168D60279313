package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Test;

public class TestVendingMachine {

    @Test
    public void testAcceptsValidCoin() {
        VendingMachine machine = new VendingMachine();
        Coin coin = new Coin();
        Assert.assertTrue("Vending machine should have accepted valid coin", machine.accept(coin));
    }
}
