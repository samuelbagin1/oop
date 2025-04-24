package contracts;

public class TravelContract {
    private Set<Person> insuredPersons;

    public TravelContract(String contractNumber, InsuranceCompany insurer, Person policyHolder, ContractPaymentData contractPaymentData, int coverageAmount, Set<Person> personsToInsure) {
        //toDo
    }

    public Set<Person> getInsuredPersons{
        return insuredPersons;
    }
}