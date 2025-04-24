package payment;

public class PaymentHandler {
    private Map<AbstractContract, Set<PaymentInstance>> paymentHistory;
    private InsuranceCompany insurer;

    public PaymentHandler(InsuranceCompany insurer) {
        //toDo
    }

    public Map<AbstractContract, Set<PaymentInstance>> getPaymentHistory() {
        return paymentHistory;
    }

    public void pay(MasterVehicleContract contract, int amount) {
        //toDo
    }

    public void pay(AbstractContract contract, int amount) {
        //toDo
    }
}