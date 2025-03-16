package se.systementor.DatabaseConnect;

import se.systementor.model.Item;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Database class provides methods for interacting with the POS system's database.
 * It includes functionality for retrieving, inserting, updating, and deleting items in the 'items' table.
 * It also offers the ability to fetch active products with stock quantity greater than zero.
 * This class handles database connections and executes SQL queries for item management within the system.
 */
public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/pos_system";
    private static final String USER = "root";  // Load from environment variable
    private static final String PASSWORD = "Vicky234";

    /**
     * Establishes a connection to the database using the provided credentials.
     *
     * @return Connection object for interacting with the database.
     * @throws SQLException if a database access error occurs.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Inserts a new item into the database, or updates the item if it already exists.
     *
     * @param item The item to be inserted or updated.
     * @return true if the item was successfully inserted or updated; false otherwise.
     */
    public boolean insertItem(Item item) {
        String query = "INSERT INTO items (name, price, vat_rate, category, stock_quantity, barcode) "
                + "VALUES (?, ?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "name = VALUES(name), "
                + "price = VALUES(price), "
                + "vat_rate = VALUES(vat_rate), "
                + "category = VALUES(category), "
                + "stock_quantity = VALUES(stock_quantity)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the values for the query
            stmt.setString(1, item.getName());
            stmt.setBigDecimal(2, item.getPrice());
            stmt.setBigDecimal(3, item.getVatRate());
            stmt.setString(4, item.getCategory());
            stmt.setInt(5, item.getStockQuantity());
            stmt.setString(6, item.getBarcode());

            // Execute the update query
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Return true if the item was inserted or updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of an error
        }
    }

    /**
     * Updates an existing item in the database.
     *
     * @param item The item to be updated.
     * @return true if the item was successfully updated; false otherwise.
     */
    public boolean updateItem(Item item) {
        String query = "UPDATE items SET name = ?, price = ?, vat_rate = ?, category = ?, stock_quantity = ?, barcode = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, item.getName());
            stmt.setBigDecimal(2, item.getPrice());
            stmt.setBigDecimal(3, item.getVatRate());
            stmt.setString(4, item.getCategory());
            stmt.setInt(5, item.getStockQuantity());
            stmt.setString(6, item.getBarcode());
            stmt.setInt(7, item.getId());

            return stmt.executeUpdate() > 0;  // Returns true if successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes an item from the database based on the given item ID.
     *
     * @param id The ID of the item to be deleted.
     * @return true if the item was successfully deleted; false otherwise.
     */
    public boolean deleteItem(int id) {
        String query = "DELETE FROM items WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;  // Returns true if successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a single item from the database based on the given item ID.
     *
     * @param id The ID of the item to be retrieved.
     * @return The item if found; null otherwise.
     */
    public Item getItemById(int id) {
        String query = "SELECT * FROM items WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getBigDecimal("vat_rate"),
                        rs.getString("category"),
                        rs.getInt("stock_quantity"),
                        rs.getString("barcode")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no item is found
    }

    /**
     * Fetches all active products (items in stock with a quantity greater than zero).
     *
     * @return A list of active products.
     */
    public List<Item> activeProducts() {
        List<Item> products = new ArrayList<>();
        String query = "SELECT * FROM items WHERE stock_quantity > 0";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getBigDecimal("vat_rate"),
                        rs.getString("category"),
                        rs.getInt("stock_quantity"),
                        rs.getString("barcode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
