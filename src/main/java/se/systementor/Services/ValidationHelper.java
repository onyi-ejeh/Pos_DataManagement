package se.systementor.Services;

/**
 * The ValidationHelper class provides utility methods for validating various aspects of an order, such as price,
 * quantity, and product existence. These methods are used to ensure that the order details meet certain criteria before
 * further processing.
 */
public class ValidationHelper {

    /**
     * Validates that the given price is not negative.
     *
     * @param price The price to validate.
     * @return true if the price is valid (greater than or equal to 0), false otherwise.
     */
    public static boolean validatePrice(double price) {
        if (price < 0) {
            System.out.println("Error: Price cannot be negative.");
            return false;
        }
        return true;
    }

    /**
     * Validates that the given quantity is greater than zero.
     *
     * @param quantity The quantity to validate.
     * @return true if the quantity is valid (greater than 0), false otherwise.
     */
    public static boolean validateQuantity(int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be greater than zero.");
            return false;
        }
        return true;
    }

    /**
     * Validates whether the given product ID exists in the database.
     * This method is a placeholder, simulating the check for a non-existing product.
     *
     * @param productId The ID of the product to validate.
     * @return true if the product exists (simulated check), false otherwise.
     */
    public static boolean validateProductExistence(int productId) {
        // Here you might want to check if the product exists in the database
        // Simulating a check for a non-existing product
        if (productId <= 0) {
            System.out.println("Error: Product does not exist.");
            return false;
        }
        return true;
    }
}
