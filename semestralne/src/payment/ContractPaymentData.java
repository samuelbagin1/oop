package payment;

import java.time.LocalDateTime;

public class ContractPaymentData {
    private int premium;    // poistne
    private PremiumPaymentFrequency premiumPaymentFrequency;
    private LocalDateTime nextPaymentTime;

    // ak je kladny, tak je nedoplatok
    // ak je 0, je zaplateny
    // ak je zaporny, je preplatok
    private int outstandingBalance;     // nedoplatok




    public ContractPaymentData(int premium, PremiumPaymentFrequency premiumPaymentFrequency, LocalDateTime nextPaymentTime, int outstandingBalance) {
        if (premium<=0) throw new IllegalArgumentException("Premium cannot be less than 0");
        if (premiumPaymentFrequency==null) throw new IllegalArgumentException("Premium payment frequency cannot be null");
        if (nextPaymentTime==null) throw new IllegalArgumentException("Next payment time cannot be null");


        this.premium = premium;
        this.premiumPaymentFrequency = premiumPaymentFrequency;
        this.nextPaymentTime = nextPaymentTime;
        this.outstandingBalance = outstandingBalance;
    }



    // GETTERY
    public int getPremium() {
        return premium;
    }
    public int getOutstandingBalance() {
        return outstandingBalance;
    }
    public PremiumPaymentFrequency getPremiumPaymentFrequency() {
        return premiumPaymentFrequency;
    }
    public LocalDateTime getNextPaymentTime() {
        return nextPaymentTime;
    }



    // SETTERY
    public void setPremium(int premium) {
        if (premium<=0) throw new IllegalArgumentException("Premium cannot be less than 0");
        this.premium = premium;
    }
    public void setOutstandingBalance(int outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }
    public void setPremiumPaymentFrequency(PremiumPaymentFrequency premiumPaymentFrequency) {
        if (premiumPaymentFrequency==null) throw new IllegalArgumentException("Premium payment frequency cannot be null");
        this.premiumPaymentFrequency = premiumPaymentFrequency;
    }




    public void updateNextPaymentTime() {
        nextPaymentTime=nextPaymentTime.plusMonths(premiumPaymentFrequency.getValueInMonths());
    }
}