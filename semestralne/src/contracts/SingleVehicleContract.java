package contracts;

public class SingleVehicleContract {
    private Vehicle insuredVehicle;

    public SingleVehicleContract(String contractNumber, InsuranceCompany insurer, Person beneficiary, Person policyHolder, ContractPaymentData contractPaymentData, int coverageAmount, Vehicle vehicleToInsure) {
        //toDo
    }

    public Vehicle getInsuredVehicle() {
        return insuredVehicle;
    }
}