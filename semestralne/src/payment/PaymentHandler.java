package payment;

import company.InsuranceCompany;
import contracts.AbstractContract;
import contracts.InvalidContractException;
import contracts.MasterVehicleContract;
import contracts.SingleVehicleContract;

import java.util.Map;
import java.util.Set;

public class PaymentHandler {
    private final Map<AbstractContract, Set<PaymentInstance>> paymentHistory;
    private final InsuranceCompany insurer;

    public PaymentHandler(InsuranceCompany insurer) {
        if (insurer==null) throw new IllegalArgumentException("Insurance Company cannot be null");

        this.insurer = insurer;
        this.paymentHistory = new java.util.HashMap<>();
    }

    public Map<AbstractContract, Set<PaymentInstance>> getPaymentHistory() {
        return paymentHistory;
    }

    public void pay(MasterVehicleContract contract, int amount) {
        if (contract==null) throw new IllegalArgumentException("Contract cannot be null");
        if (amount<=0) throw new IllegalArgumentException("Amount cannot be less than 0");
        if (!paymentHistory.containsKey(contract)) {
            throw new InvalidContractException("Contract does not exist");
        }
        if (contract.getChildContracts().isEmpty() || !contract.isActive()) {
            throw new InvalidContractException("No child contracts to pay");
        }


        for (SingleVehicleContract childContract : contract.getChildContracts()) {
            if (childContract.isActive()) {
                if (childContract.getContractPaymentData().getOutstandingBalance()>0) {

                    if (childContract.getContractPaymentData().getPremium()<amount) {
                        amount-=childContract.getContractPaymentData().getPremium();
                        childContract.getContractPaymentData().setOutstandingBalance(0);

                    } else {
                        childContract.getContractPaymentData().setOutstandingBalance(childContract.getContractPaymentData().getOutstandingBalance()-amount);
                        amount=0;
                    }

                }
            }
        }

        for (SingleVehicleContract childContract : contract.getChildContracts()) {
            if (amount>0) {

                if (childContract.isActive()) {
                    if (childContract.getContractPaymentData().getPremium()<amount) {
                        childContract.getContractPaymentData().setOutstandingBalance(childContract.getContractPaymentData().getOutstandingBalance()-childContract.getContractPaymentData().getPremium());
                        amount-=childContract.getContractPaymentData().getPremium();
                    } else {
                        childContract.getContractPaymentData().setOutstandingBalance(childContract.getContractPaymentData().getOutstandingBalance()-amount);
                        amount=0;
                    }
                }

            } else break;
        }

        PaymentInstance paymentInstance = new PaymentInstance(java.time.LocalDateTime.now(), amount);
        paymentHistory.get(contract).add(paymentInstance);
    }

    public void pay(AbstractContract contract, int amount) {
        if (contract==null || !contract.isActive()) throw new IllegalArgumentException("Contract cannot be null");
        if (amount<=0) throw new IllegalArgumentException("Amount cannot be less than 0");
        if (contract.getInsurer()!=insurer) throw new InvalidContractException("Contract does not belong to this insurance company");


        if (!paymentHistory.containsKey(contract)) {
            throw new InvalidContractException("Contract does not exist");

        } else {
            contract.updateBalance();
            PaymentInstance paymentInstance= new PaymentInstance(java.time.LocalDateTime.now(), amount);
            paymentHistory.get(contract).add(paymentInstance);
        }
    }
}