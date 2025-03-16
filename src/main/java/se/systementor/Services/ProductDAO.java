package se.systementor.Services;

import se.systementor.model.Item;
import se.systementor.DatabaseConnect.Database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDAO is a Data Access Object (DAO) class responsible for interacting with the database to
 * perform operations related to products. This class allows retrieving a list of all products
 * and creating new products in the database.
 */
public class ProductDAO {

    /**
     * Retrieves a list of all products from the database.
     *
     * This method executes a SQL query to fetch all records from the "items" table. For each record,
     * it creates an {@link Item} object using the data from the result set, including the product's
     * name, price, VAT rate, category, stock quantity, and barcode.
     *
     * @return A list of {@link Item} objects representing all products in the database.
     */
    public List<Item> getAllProducts() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";

        // Create an instance of Database
        Database database = new Database();

        try (Connection conn = database.getConnection();  // Only the connection is used in try-with-resources
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BigDecimal priceBD = rs.getBigDecimal("price");
                BigDecimal vatRateBD = rs.getBigDecimal("vat_rate");

                // Create Item using the constructor with BigDecimal for price and VAT rate
                Item item = new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        priceBD,  // Pass BigDecimal value for price
                        vatRateBD,  // Pass BigDecimal value for VAT rate
                        rs.getString("category"),
                        rs.getInt("stock_quantity"),
                        rs.getString("barcode")
                );
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    /**
     * Creates a new product in the database.
     *
     * This method creates an {@link Item} object representing a product and attempts to insert it
     * into the database using the {@link Database} class. The item is a sample product called "Milk"
     * with a price and VAT rate, category, stock quantity, and barcode.
     *
     * @return void
     */
    public void createProduct() {
        // Correct constructor call with BigDecimal values for price and VAT rate
        Item item = new Item("Milk", new BigDecimal("12.50"), new BigDecimal("12.00"), "Dairy", 50, "123456789012");

        // Use Database class to insert the product
        Database db = new Database();
        boolean inserted = db.insertItem(item);
        System.out.println("Item inserted: " + inserted);
    }
}
