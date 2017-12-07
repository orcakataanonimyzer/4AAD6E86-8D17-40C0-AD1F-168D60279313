package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Test;

public class TestRecognizeCurrency {

    @Test
    public void testCorrectCurrencyRetrievedByDiameterAndMass() {
        Assert.assertEquals(Currency.PENNY, Currency.getByDiameterAndMass(19.05, 2.5));
    }

    @Test
    public void testNoCurrencyRetrievedByInvalidDiameterAndMass() {
        Assert.assertEquals(Currency.SLUG, Currency.getByDiameterAndMass(19.05, 0.5));
    }

    @Test
    public void testCorrectCurrencyRetrievedByValidCoin() {
        Coin penny = new Coin(19.05, 2.5);
        Assert.assertEquals(Currency.PENNY, Currency.getByCoin(penny));
    }

    @Test
    public void testNoCurrencyRetrievedByInvalidCoin() {
        Coin slug = new Coin(19.05, 0.5);
        Assert.assertEquals(Currency.SLUG, Currency.getByCoin(slug));
    }
}
