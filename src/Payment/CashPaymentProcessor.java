package Payment;

public class CashPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // Simulate cash payment process
        System.out.println("Please pay Rs. " + String.format("%.2f", amount) + " in cash.");
        System.out.println("Payment received.");
        return true; // always succeeds for simulation
    }
}
