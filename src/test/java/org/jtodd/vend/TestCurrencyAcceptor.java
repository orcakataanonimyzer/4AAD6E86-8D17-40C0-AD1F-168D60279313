package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCurrencyAcceptor {

    private CurrencyAcceptor acceptor;

    @Before
    public void setUp() {
        acceptor = new CurrencyAcceptor();
    }

    @Test
    public void testAcceptsValidCoin() {
        Coin nickel = new Coin(21.21, 5);
        Coin dime = new Coin(17.91, 2.268);
        Coin quarter = new Coin(24.26, 5.67);
        Assert.assertEquals("Currency acceptor should have accepted valid coin", Currency.NICKEL, acceptor.accept(nickel).get());
        Assert.assertEquals("Currency acceptor should have accepted valid coin", Currency.DIME, acceptor.accept(dime).get());
        Assert.assertEquals("Currency acceptor should have accepted valid coin", Currency.QUARTER, acceptor.accept(quarter).get());
    }

    @Test
    public void testDoesNotAcceptInvalidCoin() {
        Coin penny = new Coin(19.05, 2.5);
        Coin halfDollar = new Coin(30.61, 11.34);
        Coin dollar = new Coin(26.49, 8.1);
        Assert.assertFalse("Currency acceptor should not have accepted invalid coin", acceptor.accept(penny).isPresent());
        Assert.assertFalse("Currency acceptor should not have accepted invalid coin", acceptor.accept(halfDollar).isPresent());
        Assert.assertFalse("Currency acceptor should not have accepted invalid coin", acceptor.accept(dollar).isPresent());
    }
}
