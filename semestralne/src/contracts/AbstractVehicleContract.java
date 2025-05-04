package contracts;

import company.InsuranceCompany;
import objects.Person;
import payment.ContractPaymentData;

public abstract class AbstractVehicleContract extends AbstractContract {
    protected Person beneficiary;   // opravnena osoba

    public AbstractVehicleContract(String contractNumber, InsuranceCompany insurer, Person beneficiary, Person policyHolder, ContractPaymentData contractPaymentData, int coverageAmount) {
        super(contractNumber, insurer, policyHolder, contractPaymentData, coverageAmount);
        // if (beneficiary == null) zmluva nemá oprávnenú osobu a prípadné poistné plnenie sa vypláca poistníkovi.
        if (beneficiary==policyHolder) throw new IllegalArgumentException("beneficiary is same as policyHolder");

        this.beneficiary = beneficiary;
    }




    // ----------- GET & SET ----------- //
    public void setBeneficiary(Person beneficiary) {
        if (beneficiary==policyHolder) throw new IllegalArgumentException("beneficiary is same as policyHolder");
        this.beneficiary = beneficiary;
    }

    public Person getBeneficiary() {
        return beneficiary;
    }
}