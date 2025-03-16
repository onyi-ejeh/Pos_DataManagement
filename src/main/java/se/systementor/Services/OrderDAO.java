package se.systementor.Services;

import se.systementor.DatabaseConnect.Database;

import java.sql.*;

/**
 * OrderDAO is a Data Access Object (DAO) class responsible for interacting with the database to perform
 * operations related to orders and order items, including creating orders and adding items to orders.
 *
 * It uses JDBC to connect to the database and execute SQL queries. The class also utilizes dependency
 * injection for the `Database` object to facilitate database connections.
 */
public class OrderDAO {
    private final Database db;

    /**
     * Constructor for the OrderDAO class. It initializes the Database object for connecting to the database.
     */
    public OrderDAO() {
        this.db = new Database();
    }

    /**
     * Creates a new order in the database and returns the generated order ID.
     *
     * This method inserts a new record into the `orders` table, including the total price and total VAT of the order.
     * The generated order ID is returned for further use, such as adding items to the order.
     *
     * @param totalPrice The total price of the order.
     * @param totalVat The total VAT of the order.
     * @return The generated order ID, or -1 if the operation fails.
     */
    public int createOrder(double totalPrice, double totalVat) {
        String query = "INSERT INTO orders (total_price, total_vat) VALUES (?, ?)";
        int orderId = -1;

        try (Connection conn = db.getConnection();  // Use instance method for DB connection
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, totalPrice);
            stmt.setDouble(2, totalVat);
            stmt.executeUpdate();

            // Get the generated order ID
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    /**
     * Adds an item to an existing order in the database.
     *
     * This method inserts a new record into the `order_items` table, associating a product with a specific order.
     * It records the order ID, product ID, quantity, and subtotal for the item.
     *
     * @param orderId The ID of the order to which the item will be added.
     * @param productId The ID of the product being added.
     * @param quantity The quantity of the product being ordered.
     * @param subtotal The subtotal for the item (price * quantity).
     */
    public void addOrderItem(int orderId, int productId, int quantity, double subtotal) {
        String query = "INSERT INTO order_items (order_id, product_id, quantity, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = db.getConnection();  // Use instance method for DB connection
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, subtotal);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
