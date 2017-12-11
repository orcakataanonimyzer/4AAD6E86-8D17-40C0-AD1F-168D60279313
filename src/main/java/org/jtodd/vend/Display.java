package org.jtodd.vend;

public class Display {

    public static final String INSERT_COIN = "INSERT COIN";
    public static final String THANK_YOU = "THANK YOU";
    public static final String SOLD_OUT = "SOLD OUT";
    private String message;
    private String alternateMessage;
    private int alternateMessageRepetitions;

    public Display() {
        message = INSERT_COIN;
        alternateMessage = "";
        alternateMessageRepetitions = 0;
    }

    public String getMessage() {
        if (alternateMessageRepetitions > 0) {
            --alternateMessageRepetitions;
            return alternateMessage;
        } else {
            return message;
        }
    }

    public void updateAmount(int amount) {
        if (amount == 0) {
            message = INSERT_COIN;
        } else {
            message = String.format("$%.2f", ((double) amount / 100));
        }
    }

    public void setMessageAndExpiration(String alternateMessage, int alternateMessageRepetitions) {
        if (alternateMessageRepetitions < 0) {
            throw new IllegalArgumentException("Alternate message cannot be set with negative number of repetitions: " + alternateMessageRepetitions);
        }
        this.alternateMessage = alternateMessage;
        this.alternateMessageRepetitions = alternateMessageRepetitions;
    }

    public void displayPrice(ProductExample example) {
        setMessageAndExpiration(String.format("Price: $%.2f", ((double) example.price) / 100), 1);
    }

    public void displaySoldOut() {
        setMessageAndExpiration(SOLD_OUT, 1);
    }
}
