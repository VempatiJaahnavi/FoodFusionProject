package foodfusion.payment;

public class DemoUpiPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        System.out.printf("Demo UPI payment successful for Rs. %.2f%n", amount);
        return true;
    }
}
