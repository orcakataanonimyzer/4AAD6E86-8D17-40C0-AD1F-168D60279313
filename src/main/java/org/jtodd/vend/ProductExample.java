package org.jtodd.vend;

public enum ProductExample {
    COLA(100), CHIPS(50), CANDY(65);

    public final int price;

    private ProductExample(int price) {
        this.price = price;
    }
}
