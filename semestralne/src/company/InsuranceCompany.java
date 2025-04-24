package company;
package payment;
package contracts;

public class InsuranceCompany {
    private Set<AbstractContract> contracts;
    private PaymentHandler handler;
    private LocalDateTime currentTime;

    public InsuranceCompany(LocalDateTime currentTime) {

    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public Set<AbstractContracts> getContracts() {
        return contracts;
    }

    public PaymentHandler getHandler() {
        return handler;
    }

    public SingleVehicleContract insureVehicle(String contractNumber, Person beneficiary, Person policyHolder, int proposedPremium, PremmiumPaymentFrequency proposedPaymentFrequency, Vehicle vehicleToInsure) {
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