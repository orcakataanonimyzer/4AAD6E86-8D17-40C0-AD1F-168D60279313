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
}
