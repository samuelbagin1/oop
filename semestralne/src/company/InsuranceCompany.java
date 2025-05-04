package company;

import contracts.*;
import objects.Person;
import objects.Vehicle;
import payment.ContractPaymentData;
import payment.PaymentHandler;
import payment.PremiumPaymentFrequency;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class InsuranceCompany {
    private final Set<AbstractContract> contracts;
    private final PaymentHandler handler;
    private LocalDateTime currentTime;



    public InsuranceCompany(LocalDateTime currentTime) {
        if (currentTime==null) throw new IllegalArgumentException("currentTime is null");

        this.currentTime = currentTime;
        this.contracts = new LinkedHashSet<>();
        handler=new PaymentHandler(this);
    }




    // GETTERY
    public LocalDateTime getCurrentTime() {
        return currentTime;
    }
    public Set<AbstractContract> getContracts() {
        return contracts;
    }
    public PaymentHandler getHandler() {
        return handler;
    }


    // STTERY
    public void setCurrentTime(LocalDateTime currentTime) {
        if (currentTime==null) throw new IllegalArgumentException("currentTime is null");
        this.currentTime = currentTime;
    }




    // ----------------------- METODY ----------------------- //
    // INSURE
    public SingleVehicleContract insureVehicle(String contractNumber, Person beneficiary, Person policyHolder, int proposedPremium, PremiumPaymentFrequency proposedPaymentFrequency, Vehicle vehicleToInsure) {
        for (AbstractContract contract : contracts) {   // check ci existuje s rovnakym cislom
            if (contract.getContractNumber().equals(contractNumber)) {
                throw new IllegalArgumentException("contract number already exists");
            }
        }
        if (vehicleToInsure==null) throw new IllegalArgumentException("vehicleToInsure cannot be null");
        if (proposedPaymentFrequency==null) throw new IllegalArgumentException("proposedPaymentFrequency cannot be null");
        if (policyHolder==null) throw new IllegalArgumentException("policyHolder cannot be null");
        if ((12/proposedPaymentFrequency.getValueInMonths())*proposedPremium<0.02*vehicleToInsure.getOriginalValue()) throw new IllegalArgumentException("proposedPremium has to be greater than 2% of original value of vehicle");


        ContractPaymentData contractPaymentData = new ContractPaymentData(proposedPremium, proposedPaymentFrequency, this.currentTime, 0);
        SingleVehicleContract contract = new SingleVehicleContract(contractNumber, this, beneficiary, policyHolder, contractPaymentData, vehicleToInsure.getOriginalValue()/2, vehicleToInsure);

        chargePremiumOnContract(contract);
        contracts.add(contract);
        policyHolder.addContract(contract);

        return contract;
    }


    public TravelContract insurePersons(String contractNumber, Person policyHolder, int proposedPremium, PremiumPaymentFrequency proposedPaymentFrequency, Set<Person> personsToInsure) {
        if (personsToInsure==null || personsToInsure.isEmpty()) throw new IllegalArgumentException("personsToInsure is null or empty");
        if (policyHolder==null) throw new IllegalArgumentException("policyHolder is null");
        if (proposedPaymentFrequency==null) throw new IllegalArgumentException("proposedPaymentFrequency cannot be null");
        for (AbstractContract contract : contracts) {
            if (contract.getContractNumber().equals(contractNumber)) {
                throw new IllegalArgumentException("contract number already exists");
            }
        }
        if ((12/proposedPaymentFrequency.getValueInMonths())*proposedPremium<5*personsToInsure.size()) throw new IllegalArgumentException("proposedPremium has to be 5 times greater than count of Persons");

        ContractPaymentData contractPaymentData = new ContractPaymentData(proposedPremium, proposedPaymentFrequency, this.currentTime, 0);
        TravelContract travelContract=new TravelContract(contractNumber, this, policyHolder, contractPaymentData, 10*personsToInsure.size(), personsToInsure);

        chargePremiumOnContract(travelContract);
        for (Person person : personsToInsure) {
            person.addContract(travelContract);}
        contracts.add(travelContract);
        policyHolder.addContract(travelContract);

        return travelContract;
    }





    // CONTRACT MANIPULATION
    public MasterVehicleContract createMasterVehicleContract(String contractNumber, Person beneficiary, Person policyHolder) {
        for (AbstractContract contract : contracts) {
            if (contract.getContractNumber().equals(contractNumber)) {
                throw new IllegalArgumentException("contract number already exists");
            }
        }

        MasterVehicleContract masterVehicleContract = new MasterVehicleContract(contractNumber, this, beneficiary, policyHolder);

        contracts.add(masterVehicleContract);
        policyHolder.addContract(masterVehicleContract);

        return masterVehicleContract;
    }


    public void moveSingleVehicleContractToMasterVehicleContract(MasterVehicleContract masterVehicleContract, SingleVehicleContract singleVehicleContract) {
        if (masterVehicleContract==null) throw new IllegalArgumentException("masterVehicleContract is null");
        if (singleVehicleContract==null) throw new IllegalArgumentException("singleVehicleContract is null");

        if (!singleVehicleContract.isActive()) throw new InvalidContractException("singleVehicleContract is not active");
        if (!masterVehicleContract.isActive()) throw new InvalidContractException("masterVehicleContract is not active");

        if (singleVehicleContract.getPolicyHolder()!=masterVehicleContract.getPolicyHolder()) throw new InvalidContractException("Contracts must have the same policy holder");
        if (singleVehicleContract.getInsurer()!=this || masterVehicleContract.getInsurer()!=this) throw new InvalidContractException("Contracts must belong to this insurance company");

        contracts.remove(singleVehicleContract);
        singleVehicleContract.getPolicyHolder().getContracts().remove(singleVehicleContract);
        masterVehicleContract.getChildContracts().add(singleVehicleContract);
    }




    // CHARGING PREMIUMS
    public void chargePremiumsOnContracts() {
        for (AbstractContract contract : contracts) {
            if (contract.isActive()) {
                contract.updateBalance();
            }
        }
    }

    public void chargePremiumOnContract(MasterVehicleContract contract) {
        for (SingleVehicleContract childContract : contract.getChildContracts()) {
            if (childContract.isActive()) {
                chargePremiumOnContract(childContract);
            }
        }
    }

    public void chargePremiumOnContract(AbstractContract contract) {
        // skipnu sa contracty ak su paymentData null, ako masterVehicleContract
        if (contract.getContractPaymentData()==null) return;

        while (currentTime.isAfter(contract.getContractPaymentData().getNextPaymentTime()) || currentTime.isEqual(contract.getContractPaymentData().getNextPaymentTime())) {
            contract.getContractPaymentData().setOutstandingBalance(contract.getContractPaymentData().getOutstandingBalance()+contract.getContractPaymentData().getPremium());
            contract.getContractPaymentData().updateNextPaymentTime();
        }
    }




    // CLAIM PROCESS
    public void processClaim(TravelContract travelContract, Set<Person> affectedPersons) {
        if (travelContract==null) throw new IllegalArgumentException("travelContract is null");
        if (affectedPersons==null || affectedPersons.isEmpty()) throw new IllegalArgumentException("affectedPersons is null or empty");

        if (travelContract.getContractPaymentData()==null) throw new IllegalArgumentException("ContractPaymentData is null");
        if (!travelContract.getInsuredPersons().containsAll(affectedPersons)) {
            throw new IllegalArgumentException("Some affected persons are not insured");
        }

        if (!travelContract.isActive()) throw new InvalidContractException("travelContract is not active");


        int coverageAmount=travelContract.getCoverageAmount()/affectedPersons.size();
        for (Person person : affectedPersons) {
            person.payout(coverageAmount);
        }

        travelContract.setInactive();
    }

    public void processClaim(SingleVehicleContract singleVehicleContract, int expectedDamages) {
        if (singleVehicleContract==null) throw new IllegalArgumentException("singleVehicleContract is null");
        if (expectedDamages<=0) throw new IllegalArgumentException("expectedDamages is less than 0");
        if (!singleVehicleContract.isActive()) throw new InvalidContractException("singleVehicleContract is not active");


        if (singleVehicleContract.getBeneficiary()!=null) {
            singleVehicleContract.getBeneficiary().payout(singleVehicleContract.getCoverageAmount());
        } else {
            singleVehicleContract.getPolicyHolder().payout(singleVehicleContract.getCoverageAmount());
        }


        if (expectedDamages>=0.7*singleVehicleContract.getInsuredVehicle().getOriginalValue()) {
            singleVehicleContract.setInactive();
        }
    }


}