package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.stream.IntStream;

public class TestVendingMachine {

    private VendingMachine machine;

    @Before
    public void setUp() {
        machine = new VendingMachine();
    }

    @Test
    public void testSimplePurchase() {
        // John wants a snack. The vending machine invites him to deposit some coins.
        Assert.assertEquals(Display.INSERT_COIN, machine.getDisplay());

        // He checks the coin return to see if the previous customer left any coins
        // behind. They didn't.
        Assert.assertArrayEquals(new Coin [] {}, machine.getReturnedCoins().toArray());

        // He tries getting a free bag of chips. The display tells him how much they
        // cost, but the machine does not give him a product. The next time he checks
        // the display the default message is shown.
        machine.select(ProductExample.CHIPS);
        Assert.assertEquals("Price: $0.50", machine.getDisplay());
        Assert.assertEquals(Display.INSERT_COIN, machine.getDisplay());
        Assert.assertArrayEquals(new Product[] {}, machine.getPurchasedProduct().toArray());

        // Fortunately, he has a pocket full of change, so he deposits some into the
        // vending machine. It tells him how much has been deposited.
        Coin nickel = new Coin(Currency.NICKEL);
        Coin dime = new Coin(Currency.DIME);
        Coin quarter = new Coin(Currency.QUARTER);
        machine.accept(nickel);
        machine.accept(dime);
        machine.accept(quarter);
        Assert.assertEquals("$0.40", machine.getDisplay());

        // He selects chips again, but he hasn't inserted enough money, so the display shows
        // him the price and no product is dispensed. The next time he checks the display,
        // it shows him how much he has deposited.
        machine.select(ProductExample.CHIPS);
        Assert.assertEquals("Price: $0.50", machine.getDisplay());
        Assert.assertArrayEquals(new Product[] {}, machine.getPurchasedProduct().toArray());
        Assert.assertEquals("$0.40", machine.getDisplay());

        // He digs in his pocket and finds another dime. When he inserts it and chooses
        // chips again, a bag of chips is dispensed. The display says "THANK YOU" and theN
        // resets to default.
        machine.accept(new Coin(Currency.DIME));
        machine.select(ProductExample.CHIPS);
        Collection<Product> dispensedProduct = machine.getPurchasedProduct();
        Assert.assertEquals(1, dispensedProduct.size());
        Assert.assertEquals(ProductExample.CHIPS, dispensedProduct.iterator().next().type);
        Assert.assertEquals(Display.THANK_YOU, machine.getDisplay());
        Assert.assertEquals(Display.INSERT_COIN, machine.getDisplay());
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
