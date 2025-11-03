package Payment;

public interface PaymentProcessor {
    /**
     * Process payment of a specified amount.
     * Throws exception if payment fails.
     * Returns true if payment succeeds.
     */
    boolean processPayment(double amount);
}
