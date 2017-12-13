# vend
Vending Machine Kata

This is a simulator of a vending machine. To build and execute the app, follow these steps:

* Download the code from http://github.com/proftodd/vend
* In the project folder, run gradle clean build
* java -cp build/classes/java/main org.jtodd.vend.Driver

Here is some more detail on the options:

* All the options are case insensitive.
* All the options are single-letter. Some have an argument, which is the rest of the entered command after the first character. Do not enter spaces between the option and the argument. For example, "IQ" means insert a quarter.
- D: Check the display on the vending machine
- I: Insert a coin. All except for slug are single letter arguments. To enter a slug, enter I, S, and the diameter in mm and the mass in mg.
- S: Select a product. Must be accompanied by a numeric code for the selection - 1 = chips, 2 = candy, 3 = soda.
- C: Push the coin return and cancel a purchase.
- R: Check for change. A string is shown summarizing the coins found in the coin return.
- P: Check for product. A string is shown summarizing the products found in the dispensary.
- X: Exit

Enjoy!