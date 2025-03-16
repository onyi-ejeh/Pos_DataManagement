package se.systementor.Services;

/**
 * PaymntService is a service class responsible for processing payments through various payment methods,
 * including credit card, cash, and PayPal.
 *
 * It provides a unified method `processPayment()` to handle payments, which delegates the actual payment
 * processing to specific methods based on the chosen payment method.
 */
public class PaymntService {

    /**
     * Processes a payment based on the specified payment method.
     *
     * This method checks the payment method provided and delegates the processing to the appropriate
     * method for handling credit card, cash, or PayPal payments. If an unsupported payment method is
     * provided, an exception is thrown.
     *
     * @param amount The amount to be paid.
     * @param paymentMethod The payment method to use ("creditcard", "cash", "paypal").
     * @return true if the payment was successfully processed, false otherwise.
     * @throws IllegalArgumentException if the provided payment method is unsupported.
     */
    public boolean processPayment(double amount, String paymentMethod) {
        switch (paymentMethod.toLowerCase()) {
            case "creditcard":
                return processCreditCardPayment(amount);
            case "cash":
                return processCashPayment(amount);
            case "paypal":
                return processPaypalPayment(amount);
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }

    /**
     * Processes a payment made via credit card.
     *
     * This method simulates the process of handling a credit card payment.
     *
     * @param amount The amount to be paid via credit card.
     * @return true if the payment was successfully processed, false otherwise.
     */
    private boolean processCreditCardPayment(double amount) {
        // Simulate processing a credit card payment
        System.out.println("Processing credit card payment of: " + amount);
        return true;
    }

    /**
     * Processes a payment made via cash.
     *
     * This method simulates the process of handling a cash payment.
     *
     * @param amount The amount to be paid in cash.
     * @return true if the payment was successfully processed, false otherwise.
     */
    private boolean processCashPayment(double amount) {
        // Simulate processing a cash payment
        System.out.println("Processing cash payment of: " + amount);
        return true;
    }

    /**
     * Processes a payment made via PayPal.
     *
     * This method simulates the process of handling a PayPal payment.
     *
     * @param amount The amount to be paid via PayPal.
     * @return true if the payment was successfully processed, false otherwise.
     */
    private boolean processPaypalPayment(double amount) {
        // Simulate processing a PayPal payment
        System.out.println("Processing PayPal payment of: " + amount);
        return true;
    }
}
