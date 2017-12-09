package org.jtodd.vend;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ProductDispenser {

    private Set<Product> dispensedProducts;

    public ProductDispenser() {
        dispensedProducts = new HashSet<>();
    }

    public void add(Product product) {
        if (dispensedProducts.contains(product)) {
            throw new IllegalArgumentException("Cannot add the same product to the dispensary more than once");
        }
        dispensedProducts.add(product);
    }

    public Collection<Product> getDispensedProducts() {
        return dispensedProducts;
    }
}
