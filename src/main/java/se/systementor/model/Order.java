package se.systementor.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The Order class represents a customer's order in the POS system.
 * It contains details such as the order ID, receipt number, order time,
 * total price, total VAT, and a custom thank-you message.
 * The class provides several constructors to accommodate different order creation scenarios.
 */
public class Order {
    private int id;
    private int receiptNumber;
    private LocalDateTime orderTime; // Date of purchase
    private BigDecimal totalPrice;
    private BigDecimal totalVat;
    private String thankYouMessage;  // Custom message

    /**
     * Default constructor for creating an order with the current date and a predefined thank-you message.
     */
    public Order() {
        this.orderTime = LocalDateTime.now();
        this.thankYouMessage = "TACK FÖR DITT KÖP!";
    }

    /**
     * Constructor for creating an order with predefined values and a default thank-you message.
     *
     * @param id The unique identifier for the order.
     * @param receiptNumber The receipt number associated with the order.
     * @param orderTime The time the order was made.
     * @param totalPrice The total price of the order (excluding VAT).
     * @param totalVat The total VAT amount for the order.
     */
    public Order(int id, int receiptNumber, LocalDateTime orderTime, BigDecimal totalPrice, BigDecimal totalVat) {
        this(id, receiptNumber, orderTime, totalPrice, totalVat, "TACK FÖR DITT KÖP!");
    }

    /**
     * Constructor for creating an order with a custom thank-you message.
     *
     * @param id The unique identifier for the order.
     * @param receiptNumber The receipt number associated with the order.
     * @param orderTime The time the order was made.
     * @param totalPrice The total price of the order (excluding VAT).
     * @param totalVat The total VAT amount for the order.
     * @param thankYouMessage A custom thank-you message to display to the customer.
     */
    public Order(int id, int receiptNumber, LocalDateTime orderTime, BigDecimal totalPrice, BigDecimal totalVat, String thankYouMessage) {
        this.id = id;
        this.receiptNumber = receiptNumber;
        this.orderTime = Objects.requireNonNull(orderTime, "Order time cannot be null");
        this.totalPrice = validatePrice(totalPrice);
        this.totalVat = validateVat(totalVat);
        this.thankYouMessage = validateThankYouMessage(thankYouMessage);
    }

    // Validation methods
    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total price cannot be null or negative");
        }
        return price;
    }

    private BigDecimal validateVat(BigDecimal vat) {
        if (vat == null || vat.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total VAT cannot be null or negative");
        }
        return vat;
    }

    private String validateThankYouMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Thank-you message cannot be null or empty");
        }
        return message;
    }

    // Getters
    public int getId() { return id; }
    public int getReceiptNumber() { return receiptNumber; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public BigDecimal getTotalVat() { return totalVat; }
    public String getThankYouMessage() { return thankYouMessage; }

    // Setter
    public void setThankYouMessage(String thankYouMessage) {
        this.thankYouMessage = validateThankYouMessage(thankYouMessage);
    }

    // toString, equals, and hashCode
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", receiptNumber=" + receiptNumber +
                ", orderTime=" + orderTime +
                ", totalPrice=" + totalPrice +
                ", totalVat=" + totalVat +
                ", thankYouMessage='" + thankYouMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                receiptNumber == order.receiptNumber &&
                Objects.equals(orderTime, order.orderTime) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(totalVat, order.totalVat) &&
                Objects.equals(thankYouMessage, order.thankYouMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, receiptNumber, orderTime, totalPrice, totalVat, thankYouMessage);
    }
}