package contracts;

public abstract class AbstractVehicleContract {
    protected Person beneficiary;

    public AbstractVehicleContract(String contractNumber, InsuranceCompany insurer, Person beneficiary, Person policyHolder, ContractPaymentData contractPaymentData, int coverageAmount) {
        //toDo
    }

    public void setBeneficiary(Person beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Person getBeneficiary() {
        return beneficiary;
    }
}