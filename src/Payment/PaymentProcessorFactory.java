package Payment;

public class PaymentProcessorFactory {
    public static PaymentProcessor createProcessor(String type) {
        switch (type.toLowerCase()) {
            case "cash":
                return new CashPaymentProcessor();
            case "card":
                return new CardPaymentProcessor();
            default:
                throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
    }
}
