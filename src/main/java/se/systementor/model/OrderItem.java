package se.systementor.model;

import java.math.BigDecimal;

public class OrderItem {
    private int orderId;
    private int productId;
    private String productName;
    private int quantity;
    private BigDecimal subtotal;
    private BigDecimal vatRate; // Add this field

    public OrderItem(int orderId, int productId, String productName, int quantity, BigDecimal subtotal, BigDecimal vatRate) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.vatRate = vatRate; // Initialize the VAT rate
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getVatRate() { // Add this method
        return vatRate;
    }
}