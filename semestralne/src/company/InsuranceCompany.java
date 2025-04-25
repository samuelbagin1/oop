package company;

import contracts.AbstractContract;
import contracts.MasterVehicleContract;
import contracts.SingleVehicleContract;
import contracts.TravelContract;
import objects.Person;
import objects.Vehicle;
import payment.PaymentHandler;
import payment.PremiumPaymentFrequency;

import java.time.LocalDateTime;
import java.util.Set;

public class InsuranceCompany {
    private final Set<AbstractContract> contracts;
    private final PaymentHandler handler;
    private LocalDateTime currentTime;

    public InsuranceCompany(LocalDateTime currentTime) {
        if (currentTime==null) throw new IllegalArgumentException("currentTime is null");

        this.currentTime = currentTime;
        this.contracts = new java.util.HashSet<>();
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




    // METODY
    public SingleVehicleContract insureVehicle(String contractNumber, Person beneficiary, Person policyHolder, int proposedPremium, PremiumPaymentFrequency proposedPaymentFrequency, Vehicle vehicleToInsure) {
        //toDo
        return null;
    }

    public TravelContract insurePersons(String contractNumber, Person policyHolder, int proposedPremium, PremiumPaymentFrequency proposedPaymentFrequency, Set<Person> personsToInsure) {
        //toDo
        return null;
    }

    public MasterVehicleContract createMasterVehicleContract(String contractNumber, Person beneficiary, Person policyHolder) {
        //toDo
        return null;
    }

    public void moveSingleVehicleContractToMasterVehicleContract(MasterVehicleContract masterVehicleContract, SingleVehicleContract singleVehicleContract) {
        //toDo
    }

    public void chargePremiumsOnContracts() {
        //toDo
    }

    public void chargePremiumOnContract(MasterVehicleContract contract) {
        //toDo
    }

    public void chargePremiumOnContract(AbstractContract contract) {
        //toDo
    }

    public void processClaim(TravelContract travelContract, Set<Person> affectedPersons) {
        //toDO
    }

    public void processClaim(SingleVehicleContract singleVehicleContract, int expectedDamages) {
        //toDo
    }
}