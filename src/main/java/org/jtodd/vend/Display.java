package org.jtodd.vend;

public class Display {

    public static final String INSERT_COIN = "INSERT COIN";
    private String message;

    public Display() {
        message = INSERT_COIN;
    }

    public String getMessage() {
        return message;
    }

    public void updateAmount(int amount) {
        message = String.format("$%.2f", ((double) amount / 100));
    }

    public void reset() {
        message = INSERT_COIN;
    }
}
