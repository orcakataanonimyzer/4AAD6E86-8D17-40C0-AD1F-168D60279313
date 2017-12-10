package org.jtodd.vend;

import java.util.Collection;
import java.util.Optional;

public class VendingMachine {

    private final String INSERT_COIN = "INSERT COIN";
    private CurrencyAcceptor acceptor;
    private Display display;
    private CoinReturn returnedCoins;
    private ProductDispenser dispenser;
    private int depositedAmount;

    public VendingMachine() {
        acceptor = new CurrencyAcceptor();
        display = new Display();
        returnedCoins = new CoinReturn();
        dispenser = new ProductDispenser();
        depositedAmount = 0;
    }

    public void accept(Coin coin) {
        Optional<Currency> testResult = acceptor.accept(coin);
        if (testResult.isPresent()) {
            depositedAmount += testResult.get().value;
            display.updateAmount(depositedAmount);
        } else {
            returnedCoins.add(coin);
        }
    }

    public void select(ProductExample example) {
        if (depositedAmount >= example.price) {
            depositedAmount -= example.price;
            display.updateAmount(depositedAmount);
            display.setMessageAndExpiration(Display.THANK_YOU, 1);
            dispenser.add(new Product(example));
        } else {
            display.displayPrice(example);
        }
    }

    public String getDisplay() {
        return display.getMessage();
    }

    public Collection<Coin> getReturnedCoins() {
        return returnedCoins.getReturnedCoins();
    }

    public Collection<Product> getPurchasedProduct() {
        return dispenser.getDispensedProducts();
    }
}
