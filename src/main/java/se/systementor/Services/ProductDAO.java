package se.systementor.Services;

import se.systementor.model.Item;
import se.systementor.DatabaseConnect.Database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private final Database database;

    // Constructor to inject Database dependency
    public ProductDAO(Database database) {
        this.database = database;
    }

    /**
     * Retrieves a list of all products from the database.
     *
     * @return A list of {@link Item} objects representing all products.
     */
    public List<Item> getAllProducts() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT id, name, price, vat_rate, category, stock_quantity, barcode FROM items";

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getBigDecimal("vat_rate"),
                        rs.getString("category"),
                        rs.getInt("stock_quantity"),
                        rs.getString("barcode")
                );
                items.add(item);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving products", e);
        }
        return items;
    }

    /**
     * Inserts a new product into the database.
     *
     * @param item The {@link Item} object to insert.
     * @return true if successful, false otherwise.
     */
    public boolean createProduct(Item item) {
        if (item == null) {
            LOGGER.warning("Attempted to insert a null item");
            return false;
        }
        return database.insertItem(item);
    }
}
