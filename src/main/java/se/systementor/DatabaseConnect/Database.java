package se.systementor.DatabaseConnect;

import se.systementor.model.Item;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The Database class provides methods for interacting with the POS system's database.
 * It includes functionality for retrieving, inserting, updating, and deleting items in the 'items' table.
 * It also offers the ability to fetch active products with stock quantity greater than zero.
 * This class handles database connections and executes SQL queries for item management within the system.
 */
public class Database {
    private static final Logger logger = Logger.getLogger(Database.class.getName());

    // Hardcoded database credentials (for development purposes only)
    private static final String URL = "jdbc:mysql://localhost:3306/pos_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Vicky234";

    /**
     * Establishes a connection to the database.
     *
     * @return A Connection object.
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
     * @throws IllegalArgumentException if the item is null.
     */
    public boolean insertItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        String query = "INSERT INTO items (name, price, vat_rate, category, stock_quantity, barcode) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "name = VALUES(name), " +
                "price = VALUES(price), " +
                "vat_rate = VALUES(vat_rate), " +
                "category = VALUES(category), " +
                "stock_quantity = VALUES(stock_quantity)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, item.getName());
            stmt.setBigDecimal(2, item.getPrice());
            stmt.setBigDecimal(3, item.getVatRate());
            stmt.setString(4, item.getCategory());
            stmt.setInt(5, item.getStockQuantity());
            stmt.setString(6, item.getBarcode());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.severe("Error inserting item: " + e.getMessage());
            return false;
        }
    }

    // Add other CRUD methods with similar improvements...
}