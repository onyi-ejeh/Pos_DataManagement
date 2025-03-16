package se.systementor;

import UserInterface.ui.CashRegisterGUI;
import se.systementor.DatabaseConnect.Database;
import se.systementor.model.Item;
import javax.swing.SwingUtilities;
import java.math.BigDecimal;

/**
 * Main class to run the cash register application.
 * This class starts the user interface and handles sample item insertion into the database,
 * and prints the items in the cart to the console.
 */
public class Main {

    /**
     * Main method to start the application. This method runs the GUI and inserts sample items
     * into the database, followed by printing the items in the cart to the console.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        // Run the GUI in a separate thread
        SwingUtilities.invokeLater(() -> {
            CashRegisterGUI display = new CashRegisterGUI();
            display.run();
        });

        // Run database operations
        insertSampleItems();

        // Print cart items
        printCartItems();
    }

    /**
     * Inserts sample items into the database.
     *
     * This method creates a few sample items with product data and attempts to insert them into
     * the database using the Database class.
     */
    private static void insertSampleItems() {
        // Create Item objects with the provided data
        Item item1 = new Item("Milk", new BigDecimal("12.50"), new BigDecimal("12.00"), "Dairy", 50, "123456789012");
        Item item2 = new Item("Bread", new BigDecimal("25.00"), new BigDecimal("12.00"), "Bakery", 30, "234567890123");
        Item item3 = new Item("Laptop", new BigDecimal("9999.99"), new BigDecimal("25.00"), "Electronics", 5, "345678901234");

        // Insert items into the database using Database class
        Database db = new Database();
        boolean inserted1 = db.insertItem(item1);  // Insert first item
        boolean inserted2 = db.insertItem(item2);  // Insert second item
        boolean inserted3 = db.insertItem(item3);  // Insert third item

        // Print insertion results to confirm
        System.out.println("Item 1 inserted: " + inserted1);
        System.out.println("Item 2 inserted: " + inserted2);
        System.out.println("Item 3 inserted: " + inserted3);
    }

    /**
     * Prints the details of items in the cart to the console.
     *
     * This method creates a mock cart with items and prints their details such as name, price,
     * VAT rate, and category.
     */
    private static void printCartItems() {
        // Updated the Item constructor to use BigDecimal instead of double
        Item[] cartItems = {
                new Item("Milk", new BigDecimal("12.50"), new BigDecimal("12.00"), "Dairy", 0, "123456789012"),
                new Item("Bread", new BigDecimal("25.00"), new BigDecimal("12.00"), "Bakery", 0, "234567890123"),
                new Item("Laptop", new BigDecimal("9999.99"), new BigDecimal("25.00"), "Electronics", 0, "345678901234")
        };

        System.out.println("Items in your cart:");
        System.out.println("---------------------");
        for (Item item : cartItems) {
            System.out.println(String.format("Name: %-15s Price: %-8.2f VAT Rate: %-6.2f Category: %-10s",
                    item.getName(), item.getPrice(), item.getVatRate(), item.getCategory()));
        }
    }
}
