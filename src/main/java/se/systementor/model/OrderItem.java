package se.systementor.model;

/**
 * The OrderItem class represents an item in an order, including details about
 * the associated order, product, quantity, and the subtotal price for that item.
 * Each OrderItem corresponds to a product within a customer's order.
 */
public class OrderItem {

    private int orderId;
    private int productId;
    private int quantity;
    private double subtotal;

    /**
     * Constructor for creating an OrderItem with the specified order ID, product ID,
     * quantity, and subtotal.
     *
     * @param orderId The ID of the order that this item is part of.
     * @param productId The ID of the product being ordered.
     * @param quantity The quantity of the product in the order.
     * @param subtotal The subtotal price for the quantity of the product in the order.
     */
    public OrderItem(int orderId, int productId, int quantity, double subtotal) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    // Getters

    /**
     * Returns the ID of the order to which this item belongs.
     *
     * @return The order ID.
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Returns the ID of the product associated with this order item.
     *
     * @return The product ID.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Returns the quantity of the product in this order item.
     *
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the subtotal price for this order item (quantity * price).
     *
     * @return The subtotal price.
     */
    public double getSubtotal() {
        return subtotal;
    }

    // Setters could be added if necessary, e.g., to modify the order item details after creation.
}
