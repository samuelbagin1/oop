package payment;

public class PaymentInstance extends interface Comparable<PaymentInstance> {
    private LocalDateTime paymentTime;
    private int paymentAmount;

    public PaymentInstance(LocalDateTime paymentTime, int paymentAmount) {
        this.paymentTime = paymentTime;
        this.paymentAmount = paymentAmount;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void getPaymentAmount() {
        return paymentAmount;
    }
}