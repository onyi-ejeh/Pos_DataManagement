package se.systementor.model;

import java.math.BigDecimal;

/**
 * The Item class represents an item in the POS system, with various attributes such as name, price,
 * VAT rate, category, stock quantity, and barcode.
 * It provides constructors for creating new items or retrieving existing items (with an ID),
 * as well as methods for getting and setting the attributes of an item.
 * The class also overrides the `toString` method to provide a meaningful string representation of the item.
 */
public class Item {

    private int id;
    private String name;
    private BigDecimal price;
    private BigDecimal vatRate;
    private String category;
    private int stockQuantity;
    private String barcode;

    /**
     * Constructs an Item object with an existing ID.
     *
     * @param id The unique identifier of the item.
     * @param name The name of the item.
     * @param price The price of the item.
     * @param vatRate The VAT rate of the item.
     * @param category The category of the item.
     * @param stockQuantity The stock quantity of the item.
     * @param barcode The barcode of the item.
     */
    public Item(int id, String name, BigDecimal price, BigDecimal vatRate, String category, int stockQuantity, String barcode) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vatRate = vatRate;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.barcode = barcode;
    }

    /**
     * Constructs a new Item object without an ID (used for new items).
     *
     * @param name The name of the item.
     * @param price The price of the item.
     * @param vatRate The VAT rate of the item.
     * @param category The category of the item.
     * @param stockQuantity The stock quantity of the item.
     * @param barcode The barcode of the item.
     */
    public Item(String name, BigDecimal price, BigDecimal vatRate, String category, int stockQuantity, String barcode) {
        this.name = name;
        this.price = price;
        this.vatRate = vatRate;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.barcode = barcode;
    }

    /**
     * Returns a string representation of the item, including its name, price, VAT rate, and category.
     *
     * @return A string representation of the item.
     */
    @Override
    public String toString() {
        return "Item[name=" + name + ", price=" + price + ", vatRate=" + vatRate + ", category=" + category + "]";
    }

    // Getters and setters

    /**
     * Returns the unique identifier of the item.
     *
     * @return The item's ID.
     */
    public int getId() { return id; }

    /**
     * Returns the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() { return name; }

    /**
     * Returns the price of the item.
     *
     * @return The price of the item.
     */
    public BigDecimal getPrice() { return price; }

    /**
     * Returns the VAT rate of the item.
     *
     * @return The VAT rate of the item.
     */
    public BigDecimal getVatRate() { return vatRate; }

    /**
     * Returns the category of the item.
     *
     * @return The category of the item.
     */
    public String getCategory() { return category; }

    /**
     * Returns the stock quantity of the item.
     *
     * @return The stock quantity of the item.
     */
    public int getStockQuantity() { return stockQuantity; }

    /**
     * Returns the barcode of the item.
     *
     * @return The barcode of the item.
     */
    public String getBarcode() { return barcode; }

    /**
     * Sets the unique identifier of the item.
     *
     * @param id The unique identifier of the item.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Sets the name of the item.
     *
     * @param name The name of the item.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Sets the price of the item.
     *
     * @param price The price of the item.
     */
    public void setPrice(BigDecimal price) { this.price = price; }

    /**
     * Sets the VAT rate of the item.
     *
     * @param vatRate The VAT rate of the item.
     */
    public void setVatRate(BigDecimal vatRate) { this.vatRate = vatRate; }

    /**
     * Sets the category of the item.
     *
     * @param category The category of the item.
     */
    public void setCategory(String category) { this.category = category; }

    /**
     * Sets the stock quantity of the item.
     *
     * @param stockQuantity The stock quantity of the item.
     */
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    /**
     * Sets the barcode of the item.
     *
     * @param barcode The barcode of the item.
     */
    public void setBarcode(String barcode) { this.barcode = barcode; }
}
