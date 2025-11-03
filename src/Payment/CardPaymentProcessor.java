package Payment;

public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // Simulate card payment process
        System.out.println("Processing card payment for Rs. " + String.format("%.2f", amount));
        System.out.println("Payment authorized.");
        return true; // always succeeds for simulation
    }
}
