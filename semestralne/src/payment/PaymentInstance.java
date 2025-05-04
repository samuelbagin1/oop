package payment;

import java.time.LocalDateTime;

public class PaymentInstance implements Comparable<PaymentInstance> {
    private final LocalDateTime paymentTime;
    private final int paymentAmount;

    public PaymentInstance(LocalDateTime paymentTime, int paymentAmount) {
        if (paymentTime == null) throw new IllegalArgumentException("Payment time cannot be null");
        if (paymentAmount <= 0) throw new IllegalArgumentException("Payment amount cannot be less than 0");

        this.paymentTime = paymentTime;
        this.paymentAmount = paymentAmount;
    }

    // GETTERS
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }
    public int getPaymentAmount() {
        return paymentAmount;
    }


    // COMPARE
    @Override
    public int compareTo(PaymentInstance other) {
        return this.paymentTime.compareTo(other.paymentTime);
    }
}