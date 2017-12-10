package org.jtodd.vend;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
            Set<Coin> change = makeChange(depositedAmount - example.price);
            for (Coin c : change) {
                returnedCoins.add(c);
            }
            depositedAmount = 0;
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

    public Set<Coin> makeChange(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot make change for negative amount");
        }
        if (amount % 5 != 0) {
            throw new IllegalArgumentException("Cannot make change for amount not divisible by 5");
        }
        Set<Coin> change = new HashSet<>();
        while (amount >= 25) {
            change.add(new Coin(Currency.QUARTER));
            amount -= 25;
        }
        while (amount >= 10) {
            change.add(new Coin(Currency.DIME));
            amount -= 10;
        }
        while (amount > 0) {
            change.add(new Coin(Currency.NICKEL));
            amount -= 5;
        }
         return change;
    }
}
