package org.jtodd.vend;

import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Driver {

    VendingMachine machine;
    Scanner in;
    PrintStream out;

    public Driver() {
        machine = new VendingMachine();
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    void run() {
        char option;
        String arg;
        out.println("Vending machine simulator");
        Map.Entry<Character, String> action = showOptions(out, in);
        option = action.getKey();
        arg = action.getValue();
        while (option != 'X') {
            switch (option) {
                case 'D':
                    out.println("Display: " + machine.getDisplay());
                    break;
                case 'I':
                    Coin dc = null;
                    switch (arg.charAt(0)) {
                        case 'p':
                            dc = new Coin(Currency.PENNY);
                            break;
                        case 'n':
                            dc = new Coin(Currency.NICKEL);
                            break;
                        case 'd':
                            dc = new Coin(Currency.DIME);
                            break;
                        case 'q':
                            dc = new Coin(Currency.QUARTER);
                            break;
                        case 'h':
                            dc = new Coin(Currency.HALF_DOLLAR);
                            break;
                        case 'o':
                            dc = new Coin(Currency.DOLLAR);
                            break;
                        case 's':
                            String[] dimensionsString = arg.substring(1).split(",");
                            double diameter = Double.parseDouble(dimensionsString[0]);
                            double mass = Double.parseDouble(dimensionsString[1]);
                            dc = new Coin(diameter, mass);
                            break;
                        default:
                            out.println("Unrecognize choice: " + arg);
                            return;
                    }
                    machine.accept(dc);
                    out.println("Coin inserted");
                    break;
                case 'S':
                    switch (arg.charAt(0)) {
                        case '1':
                            machine.select(ProductExample.CHIPS);
                            break;
                        case '2':
                            machine.select(ProductExample.CANDY);
                            break;
                        case '3':
                            machine.select(ProductExample.COLA);
                            break;
                        default:
                            out.printf("Unrecognized command: %s%s\n" + option, arg);
                    }
                    break;
                case 'C':
                    machine.cancel();
                    out.println("Canceled purchase");
                    break;
                case 'R':
                    String rcOutput = machine.getReturnedCoins().stream()
                            .map(c -> Currency.getByCoin(c))
                            .map(c -> c.name())
                            .collect(Collectors.joining(","));
                    out.println("Returned coins: " + rcOutput);
                    break;
                case 'P':
                    String dpOutput = machine.getPurchasedProduct().stream()
                            .map(p -> p.type)
                            .map(pt -> pt.name())
                            .collect(Collectors.joining(","));
                    out.println("Dispensed product: " + dpOutput.toString());
                    break;
                default:
                    out.printf("Unrecognized command: %s%s\n", option, arg);
            }
            out.println();
            action = showOptions(out, in);
            option = action.getKey();
            arg = action.getValue();
        }
    }

    public static Map.Entry<Character, String> showOptions(PrintStream out, Scanner in) {
        out.println("D: Check display");
        out.println("I[PNDQHD(S%.4f,%.4f)]: Insert (penny, nickel, dollar, quarter, half-dollar, dollar, slug(diameter, mass)");
        out.println("S[123]: Select (1 = chips, 2 = candy, 3 = soda");
        out.println("C: Cancel purchase");
        out.println("R: Check for change");
        out.println("P: Check for product");
        out.println("X: Exit");
        out.println();
        out.print("Choice: ");

        String choice = in.nextLine();
        char option = choice.toUpperCase().charAt(0);
        String arg = choice.toLowerCase().substring(1);
        return new AbstractMap.SimpleEntry<>(option, arg);
    }

    public static void main(String [] args) {
        new Driver().run();
        System.exit(0);
    }
}
