package contracts;

public class MasterVehicleContract {
    private Set<SingleVehicleContract> childContracts;

    public MasterVehicleContract(String contractNumber, InsuranceCompany insurer, Person beneficiary, Person policyHolder) {
        //toDo
    }

    public Set<SingleVehicleContract> getChildContracts() {
        return childContracts;
    }

    public void requestAdditionOfChildContract(SingleVehicleContract contract) {
        //toDo
    }
}