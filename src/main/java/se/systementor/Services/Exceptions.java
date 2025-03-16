package se.systementor.Services;

/**
 * The Exceptions class contains custom exception classes used in the system to
 * handle specific error scenarios related to product validation, payment processing,
 * and database connection issues.
 *
 * Each of these exceptions provides a custom message to indicate the cause of the error.
 */
public class Exceptions {

    /**
     * Custom exception to handle invalid product-related errors.
     * This exception is thrown when a product is found to be invalid,
     * such as when trying to add an invalid product to an order or inventory.
     */
    public class InvalidProductException extends Exception {

        /**
         * Constructor for InvalidProductException that accepts a custom error message.
         *
         * @param message The error message associated with the exception.
         */
        public InvalidProductException(String message) {
            super(message);
        }
    }

    /**
     * Custom exception to handle errors during payment processing.
     * This exception is thrown when an error occurs during the payment
     * process, such as when the payment gateway returns an error.
     */
    public class PaymentProcessingException extends Exception {

        /**
         * Constructor for PaymentProcessingException that accepts a custom error message.
         *
         * @param message The error message associated with the exception.
         */
        public PaymentProcessingException(String message) {
            super(message);
        }
    }

    /**
     * Custom exception to handle database connection errors.
     * This exception is thrown when there is an issue connecting to the database,
     * such as a connection timeout or incorrect credentials.
     */
    public class DatabaseConnectionException extends Exception {

        /**
         * Constructor for DatabaseConnectionException that accepts a custom error message.
         *
         * @param message The error message associated with the exception.
         */
        public DatabaseConnectionException(String message) {
            super(message);
        }
    }
}
