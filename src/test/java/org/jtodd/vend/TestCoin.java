package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Test;

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
}
