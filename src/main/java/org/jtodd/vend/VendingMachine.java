package org.jtodd.vend;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class VendingMachine {

    private final String INSERT_COIN = "INSERT COIN";
    private CurrencyAcceptor acceptor;
    private Display display;
    private CoinReturn returnedCoins;
    private ProductDispenser dispenser;
    private Map<Coin, Currency> depositedCoins;
    private Map<Currency, Integer> bank;
    private int depositedAmount;
    private Map<ProductExample, Integer> inventory;

    public VendingMachine() {
        this(makeDefaultInventory(), makeDefaultBank());
    }

    public VendingMachine(Map<ProductExample, Integer> inventory, Map<Currency, Integer> bank) {
        acceptor = new CurrencyAcceptor();
        display = new Display();
        returnedCoins = new CoinReturn();
        dispenser = new ProductDispenser();
        depositedCoins = new HashMap<>();
        this.inventory = inventory;
        this.bank = bank;
        depositedAmount = 0;
    }

    public void accept(Coin coin) {
        if (depositedCoins.containsKey(coin)) {
            throw new IllegalArgumentException("Cannot insert the same coin more than once");
        }
        Optional<Currency> testResult = acceptor.accept(coin);
        if (testResult.isPresent()) {
            Currency c = testResult.get();
            depositedCoins.put(coin, c);
            depositedAmount += c.value;
            display.updateAmount(depositedAmount);
        } else {
            returnedCoins.add(coin);
        }
    }

    public void select(ProductExample example) {
        if (inventory.get(example) < 1) {
            display.displaySoldOut();
        } else if (depositedAmount >= example.price) {
            Set<Coin> change = makeChange(depositedAmount - example.price);
            for (Coin c : change) {
                returnedCoins.add(c);
            }
            depositedAmount = 0;
            display.updateAmount(depositedAmount);
            display.setMessageAndExpiration(Display.THANK_YOU, 1);
            dispenser.add(new Product(example));
            inventory.put(example, inventory.get(example) - 1);
        } else {
            display.displayPrice(example);
        }
    }

    public void cancel() {
        for (Coin c : depositedCoins.keySet()) {
            returnedCoins.add(c);
        }
        depositedCoins.clear();
        depositedAmount = 0;
        display.updateAmount(depositedAmount);
    }

    public String getDisplay() {
        return display.getMessage();
    }

    public Collection<Coin> getReturnedCoins() {
        Set<Coin> coins = new HashSet<>();
        coins.addAll(returnedCoins.getReturnedCoins());
        returnedCoins.clear();
        return coins;
    }

    public Collection<Product> getPurchasedProduct() {
        Set<Product> dispensed = new HashSet<>();
        dispensed.addAll(dispenser.getDispensedProducts());
        dispenser.getDispensedProducts().clear();
        return dispensed;
    }

    public Map<Coin, Currency> getDepositedCoins() {
        return depositedCoins;
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

    public static Map<ProductExample, Integer> makeDefaultInventory() {
        HashMap<ProductExample, Integer> inventory = new HashMap<>();
        inventory.put(ProductExample.CHIPS, 20);
        inventory.put(ProductExample.CANDY, 20);
        inventory.put(ProductExample.COLA, 20);
        return inventory;
    }

    public static Map<Currency, Integer> makeDefaultBank() {
        HashMap<Currency, Integer> bank = new HashMap<>();
        bank.put(Currency.NICKEL, 20);
        bank.put(Currency.DIME, 20);
        bank.put(Currency.QUARTER, 20);
        return bank;
    }
}
