package se.systementor.model;

import java.time.LocalDateTime;

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
    private double totalPrice;
    private double totalVat;
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
    public Order(int id, int receiptNumber, LocalDateTime orderTime, double totalPrice, double totalVat) {
        this.id = id;
        this.receiptNumber = receiptNumber;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
        this.totalVat = totalVat;
        this.thankYouMessage = "TACK FÖR DITT KÖP!";
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
    public Order(int id, int receiptNumber, LocalDateTime orderTime, double totalPrice, double totalVat, String thankYouMessage) {
        this.id = id;
        this.receiptNumber = receiptNumber;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
        this.totalVat = totalVat;
        this.thankYouMessage = thankYouMessage;
    }

    // Getters

    /**
     * Returns the unique identifier of the order.
     *
     * @return The ID of the order.
     */
    public int getId() { return id; }

    /**
     * Returns the receipt number associated with the order.
     *
     * @return The receipt number.
     */
    public int getReceiptNumber() { return receiptNumber; }

    /**
     * Returns the time when the order was placed.
     *
     * @return The order time.
     */
    public LocalDateTime getOrderTime() { return orderTime; }

    /**
     * Returns the total price of the order, excluding VAT.
     *
     * @return The total price of the order.
     */
    public double getTotalPrice() { return totalPrice; }

    /**
     * Returns the total VAT amount for the order.
     *
     * @return The total VAT amount.
     */
    public double getTotalVat() { return totalVat; }

    /**
     * Returns the thank-you message for the order.
     *
     * @return The thank-you message.
     */
    public String getThankYouMessage() { return thankYouMessage; }

    // Setter

    /**
     * Sets a custom thank-you message for the order.
     *
     * @param thankYouMessage The custom thank-you message to set.
     */
    public void setThankYouMessage(String thankYouMessage) {
        this.thankYouMessage = thankYouMessage;
    }
}
