package payment;

import company.InsuranceCompany;
import contracts.AbstractContract;
import contracts.InvalidContractException;
import contracts.MasterVehicleContract;
import contracts.SingleVehicleContract;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PaymentHandler {
    private final Map<AbstractContract, Set<PaymentInstance>> paymentHistory;
    private final InsuranceCompany insurer;     // poistovatel




    public PaymentHandler(InsuranceCompany insurer) {
        if (insurer==null) throw new IllegalArgumentException("Insurance Company cannot be null");

        this.insurer = insurer;
        this.paymentHistory = new HashMap<>();
    }


    public Map<AbstractContract, Set<PaymentInstance>> getPaymentHistory() {
        return paymentHistory;
    }





    // METODY
    public void pay(MasterVehicleContract contract, int amount) {
        if (contract==null) throw new IllegalArgumentException("Contract cannot be null");
        if (amount<=0) throw new IllegalArgumentException("Amount cannot be less than 0");
        if (contract.getChildContracts().isEmpty()) {
            throw new InvalidContractException("Contract has no child contracts");
        }
        if (!contract.isActive()) {
            throw new InvalidContractException("contract is not active");
        }
        if (contract.getInsurer()!=insurer) {
            throw new InvalidContractException("Contract does not belong to this insurance company");
        }


        int remainingAmount=amount;

        // vycistenie nedoplatkov
        for (SingleVehicleContract childContract : contract.getChildContracts()) {
            if (childContract.isActive() && childContract.getContractPaymentData().getOutstandingBalance()>0) {

                    if (remainingAmount>=childContract.getContractPaymentData().getOutstandingBalance()) {
                        remainingAmount-=childContract.getContractPaymentData().getOutstandingBalance();
                        childContract.getContractPaymentData().setOutstandingBalance(0);

                    } else {
                        childContract.getContractPaymentData().setOutstandingBalance(childContract.getContractPaymentData().getOutstandingBalance()-remainingAmount);
                        remainingAmount=0;
                    }

            }
        }

        // ak je dostatok penazi, vytvoria sa preplatky
        while (remainingAmount>0) {
            boolean paymentDone=false;

            for (SingleVehicleContract childContract : contract.getChildContracts()) {
                if (childContract.isActive() && remainingAmount>0) {

                    if (remainingAmount>=childContract.getContractPaymentData().getPremium()) {
                        childContract.getContractPaymentData().setOutstandingBalance(childContract.getContractPaymentData().getOutstandingBalance()-childContract.getContractPaymentData().getPremium());
                        remainingAmount-=childContract.getContractPaymentData().getPremium();
                        paymentDone=true;
                    } else {
                        childContract.getContractPaymentData().setOutstandingBalance(childContract.getContractPaymentData().getOutstandingBalance()-remainingAmount);
                        remainingAmount=0;
                        paymentDone=true;
                    }

                }
            }

            if (!paymentDone) break;    // ak sa nedalo zaplatit, vyskocime z loopu
        }

        // ak este neexistuje zaznam o platbe, vytvorime ho
        if (!paymentHistory.containsKey(contract)) {
            paymentHistory.put(contract, new TreeSet<>());
        }

        PaymentInstance paymentInstance = new PaymentInstance(insurer.getCurrentTime(), amount);
        paymentHistory.get(contract).add(paymentInstance);
    }



    public void pay(AbstractContract contract, int amount) {
        if (contract==null) throw new IllegalArgumentException("Contract cannot be null");
        if (amount<=0) throw new IllegalArgumentException("Amount cannot be less than 0");
        if (contract.getInsurer()!=insurer) throw new InvalidContractException("Contract does not belong to this insurance company");
        if (!contract.isActive()) throw new InvalidContractException("Contract is not active");

        // ak este neexistuje zaznam o platbe, vytvorime ho
        if (!paymentHistory.containsKey(contract)) {
            paymentHistory.put(contract, new TreeSet<>());
        }


        contract.getContractPaymentData().setOutstandingBalance(contract.getContractPaymentData().getOutstandingBalance()-amount);

        PaymentInstance paymentInstance= new PaymentInstance(insurer.getCurrentTime(), amount);
        paymentHistory.get(contract).add(paymentInstance);
    }
}