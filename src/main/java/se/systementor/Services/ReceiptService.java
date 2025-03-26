package se.systementor.Services;

import se.systementor.model.Order;
import se.systementor.model.OrderItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * ReceiptService is a service class responsible for generating receipts for customer orders.
 * It formats the receipt as a string containing the order details, such as the receipt number,
 * order date, product details, and total amount.
 */
public class ReceiptService {

    /**
     * Generates a formatted receipt for a given order and its associated order items.
     *
     * @param order      The {@link Order} object representing the order details, including receipt number and order time.
     * @param orderItems A list of {@link OrderItem} objects representing the items in the order.
     * @return A formatted receipt as a string.
     * @throws IllegalArgumentException if order or orderItems is null.
     */
    public static String generateReceipt(Order order, List<OrderItem> orderItems) {
        // Validate input
        Objects.requireNonNull(order, "Order cannot be null");
        Objects.requireNonNull(orderItems, "Order items cannot be null");

        StringBuilder receipt = new StringBuilder();

        // Header
        receipt.append("                     STEFANS SUPERSHOP\n");
        receipt.append("----------------------------------------------------\n");
        receipt.append(String.format("Kvittonummer: %-10s  Datum: %s\n", order.getReceiptNumber(), order.getOrderTime()));
        receipt.append("----------------------------------------------------\n");

        // Item details
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal totalVat = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            BigDecimal itemTotal = item.getSubtotal().multiply(BigDecimal.valueOf(item.getQuantity()));
            BigDecimal itemVat = itemTotal.multiply(item.getVatRate()).divide(BigDecimal.valueOf(100)); // Calculate VAT for the item
            subtotal = subtotal.add(itemTotal);
            totalVat = totalVat.add(itemVat);

            receipt.append(String.format("%-25s %3d x %10.2f = %10.2f\n",
                    item.getProductName(), item.getQuantity(), item.getSubtotal(), itemTotal));
        }

        // Footer
        BigDecimal total = subtotal.add(totalVat);
        receipt.append("----------------------------------------------------\n");
        receipt.append(String.format("Subtotal:                                  %10.2f\n", subtotal));
        receipt.append(String.format("Moms:                                     %10.2f\n", totalVat));
        receipt.append(String.format("Total:                                    %10.2f\n", total));
        receipt.append("TACK FÖR DITT KÖP\n");

        return receipt.toString();
    }
}