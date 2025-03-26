package se.systementor.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The Item class represents an item in the POS system, with attributes such as name, price,
 * VAT rate, category, stock quantity, and barcode.
 * It ensures proper validation and provides additional methods for business logic.
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
     * Constructs an Item object with the specified attributes.
     *
     * @param id           The unique identifier of the item.
     * @param name         The name of the item (cannot be null or empty).
     * @param price        The price of the item (cannot be null or negative).
     * @param vatRate      The VAT rate of the item (cannot be null or negative).
     * @param category     The category of the item (cannot be null or empty).
     * @param stockQuantity The stock quantity of the item (cannot be negative).
     * @param barcode      The barcode of the item (cannot be null or empty).
     * @throws IllegalArgumentException if any validation fails.
     */
    public Item(int id, String name, BigDecimal price, BigDecimal vatRate, String category, int stockQuantity, String barcode) {
        this.id = id;
        this.name = validateName(name);
        this.price = validatePrice(price);
        this.vatRate = validateVatRate(vatRate);
        this.category = validateCategory(category);
        this.stockQuantity = validateStockQuantity(stockQuantity);
        this.barcode = validateBarcode(barcode);
    }

    // Validation methods
    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        return name.trim();
    }

    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
        return price;
    }

    private BigDecimal validateVatRate(BigDecimal vatRate) {
        if (vatRate == null || vatRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("VAT rate cannot be null or negative");
        }
        return vatRate;
    }

    private String validateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        return category.trim();
    }

    private int validateStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        return stockQuantity;
    }

    private String validateBarcode(String barcode) {
        if (barcode == null || barcode.trim().isEmpty()) {
            throw new IllegalArgumentException("Barcode cannot be null or empty");
        }
        return barcode.trim();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Converts the price to a long value representing cents (e.g., 12.50 SEK becomes 1250).
     *
     * @return The price in cents as a long value.
     */
    public long getPriceAsLong() {
        return price.multiply(BigDecimal.valueOf(100)).longValue();
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }

    /**
     * Converts the VAT rate to a double value.
     *
     * @return The VAT rate as a double.
     */
    public double getVatRateAsDouble() {
        return vatRate.doubleValue();
    }

    public String getCategory() {
        return category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getBarcode() {
        return barcode;
    }

    // Setters with validation
    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setPrice(BigDecimal price) {
        this.price = validatePrice(price);
    }

    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = validateVatRate(vatRate);
    }

    public void setCategory(String category) {
        this.category = validateCategory(category);
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = validateStockQuantity(stockQuantity);
    }

    public void setBarcode(String barcode) {
        this.barcode = validateBarcode(barcode);
    }

    // Business logic methods
    /**
     * Calculates the total price including VAT.
     *
     * @return The price including VAT.
     */
    public BigDecimal getPriceWithVat() {
        return price.add(price.multiply(vatRate));
    }

    /**
     * Reduces the stock quantity by the specified amount.
     *
     * @param quantity The quantity to reduce.
     * @throws IllegalArgumentException if the quantity is invalid (e.g., negative or greater than stock).
     */
    public void reduceStock(int quantity) {
        if (quantity <= 0 || quantity > stockQuantity) {
            throw new IllegalArgumentException("Invalid stock reduction amount");
        }
        this.stockQuantity -= quantity;
    }

    // toString, equals, and hashCode
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", vatRate=" + vatRate +
                ", category='" + category + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", barcode='" + barcode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                stockQuantity == item.stockQuantity &&
                Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(vatRate, item.vatRate) &&
                Objects.equals(category, item.category) &&
                Objects.equals(barcode, item.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, vatRate, category, stockQuantity, barcode);
    }
}