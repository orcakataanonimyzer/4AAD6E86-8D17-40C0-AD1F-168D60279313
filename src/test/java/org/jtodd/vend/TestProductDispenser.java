package org.jtodd.vend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestProductDispenser {

    private ProductDispenser dispenser;

    @Before
    public void setUp() {
        dispenser = new ProductDispenser();
    }

    @Test
    public void testDispenserReturnIsEmptyWhenNoProductsAdded() {
        Assert.assertArrayEquals(new Product[]{}, dispenser.getDispensedProducts().toArray());
    }

    @Test
    public void testCannotAddProductTwice() {
        Product product = new Product(ProductExample.CANDY);
        try {
            dispenser.add(product);
            dispenser.add(product);
            Assert.fail("Should not have been able to add same product twice");
        } catch (IllegalArgumentException e) {}
    }
}
