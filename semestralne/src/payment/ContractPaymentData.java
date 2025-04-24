package payment;

public class ContractPaymentData {
    private int premium;
    private PremiumPaymentFrequency premiumPaymentFrequency;
    private LocalDateTime nextPaymentTime;
    private int outstandingBalance;

    public ContractPaymentData(int premium, PremiumPaymentFrequency premiumPaymentFrequency, LocalDateTime nextPaymentTime, int outstandingBalance) {
        //toDo
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        //toDo
        this.premium = premium;
    }

    public void setOutstandingBalance(int outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public int getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setPremiumPaymentFrequency(PremiumPaymentFrequency premiumPaymentFrequency) {
        this.premiumPaymentFrequency = premiumPaymentFrequency;
    }

    public PremiumPaymentFrequency getPremiumPaymentFrequency() {
        return premiumPaymentFrequency;
    }

    public LocalDateTime getNextPaymentTime() {
        return nextPaymentTime;
    }

    public void updateNextPaymentTime() {
        //toDo
    }
}