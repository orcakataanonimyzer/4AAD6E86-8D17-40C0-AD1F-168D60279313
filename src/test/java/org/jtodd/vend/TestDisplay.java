package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDisplay {

    private Display display;

    @Before
    public void setUp() {
        display = new Display();
    }

    @Test
    public void testDisplayWhenNoCoinsHaveBeenInserted() {
        Assert.assertEquals("Wrong starting display given", Display.INSERT_COIN, display.getMessage());
    }

    @Test
    public void testDisplaysCorrectDepositedAmount() {
        display.updateAmount(5);
        Assert.assertEquals("$0.05", display.getMessage());
        display.updateAmount(125);
        Assert.assertEquals("$1.25", display.getMessage());
    }

    @Test
    public void testDisplayCorrectAmountWhenResetToZero() {
        display.updateAmount(0);
        Assert.assertEquals(display.INSERT_COIN, display.getMessage());
    }

    @Test
    public void testSetMessageThatExpiresAfterNReads() {
        String message = "Price: $0.50";
        display.setMessageAndExpiration(message, 2);
        Assert.assertEquals(message, display.getMessage());
        Assert.assertEquals(message, display.getMessage());
        Assert.assertEquals(Display.INSERT_COIN, display.getMessage());
    }

    @Test
    public void testExpiringMessageCannotBeSetWithNegativeN() {
        int repetitions = -1;
        try {
            display.setMessageAndExpiration("", repetitions);
            Assert.fail("Should not have been able to set repetitions less than 1: " + repetitions);
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testExpiringMessageWithZeroRepetitionsHasNoEffect() {
        display.setMessageAndExpiration("", 0);
        Assert.assertEquals(Display.INSERT_COIN, display.getMessage());
    }

    @Test
    public void testDisplayProductExamplePriceAndThenDefault() {
        display.displayPrice(ProductExample.CHIPS);
        Assert.assertEquals(String.format("Price: $%.2f", ((double) ProductExample.CHIPS.price) / 100), display.getMessage());
        Assert.assertEquals(Display.INSERT_COIN, display.getMessage());
    }

    @Test
    public void testDisplayProductExamplePriceAndThenDepositedAmount() {
        display.updateAmount(65);
        display.displayPrice(ProductExample.CHIPS);
        Assert.assertEquals(String.format("Price: $%.2f", ((double) ProductExample.CHIPS.price) / 100), display.getMessage());
        Assert.assertEquals("$0.65", display.getMessage());
    }
}
