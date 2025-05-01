package contracts;

import company.InsuranceCompany;
import objects.LegalForm;
import objects.Person;
import payment.ContractPaymentData;

import java.util.Set;

public class TravelContract extends AbstractContract {
    private final Set<Person> insuredPersons;

    public TravelContract(String contractNumber, InsuranceCompany insurer, Person policyHolder, ContractPaymentData contractPaymentData, int coverageAmount, Set<Person> personsToInsure) {
        super(contractNumber, insurer, policyHolder, contractPaymentData, coverageAmount);
        if (contractPaymentData==null) throw new IllegalArgumentException("contractPaymentData is null");
        if (personsToInsure==null || personsToInsure.isEmpty()) throw new IllegalArgumentException("personsToInsure is null or empty");

        for (Person person : personsToInsure) {
            if (person.getLegalForm()!= LegalForm.NATURAL) throw new IllegalArgumentException("Person is not a NATURAL person");
        }

        insuredPersons = personsToInsure;
    }

    public Set<Person> getInsuredPersons(){
        return this.insuredPersons;
    }
}