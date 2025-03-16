package se.systementor.Services;

import se.systementor.model.Order;
import se.systementor.model.OrderItem;

import java.util.List;

/**
 * ReceiptService is a service class responsible for generating receipts for customer orders.
 * It formats the receipt as a string containing the order details, such as the receipt number,
 * order date, product details, and total amount.
 */
public class ReceiptService {

    /**
     * Generates a formatted receipt for a given order and its associated order items.
     *
     * This method takes an {@link Order} object and a list of {@link OrderItem} objects and formats them into
     * a readable string representing a receipt. It includes the receipt number, order date, item details (name,
     * quantity, subtotal, and item total), and the total price of the order.
     *
     * @param order The {@link Order} object representing the order details, including receipt number and order time.
     * @param orderItems A list of {@link OrderItem} objects representing the items in the order, including quantity and subtotal.
     * @return A string representing the formatted receipt.
     */
    public static String generateReceipt(Order order, List<OrderItem> orderItems) {
        StringBuilder receipt = new StringBuilder();

        receipt.append("                     STEFANS SUPERSHOP\n");
        receipt.append("----------------------------------------------------\n");
        receipt.append("Kvittonummer: " + order.getReceiptNumber() + "        Datum: " + order.getOrderTime() + "\n");
        receipt.append("----------------------------------------------------\n");

        double total = 0;

        for (OrderItem item : orderItems) {
            // Assuming you have access to the product data here (either fetch it or pass product name with OrderItem)
            String productName = "Product Name";  // Placeholder, replace with actual product name from Item class
            double itemTotal = item.getQuantity() * item.getSubtotal();
            total += itemTotal;
            receipt.append(String.format("%-25s %-3d *  %-10.2f =  %-10.2f\n", productName, item.getQuantity(), item.getSubtotal(), itemTotal));
        }

        receipt.append("----------------------------------------------------\n");
        receipt.append("Total:                                       " + total + "\n");
        receipt.append("TACK FÖR DITT KÖP\n");

        return receipt.toString();
    }
}
