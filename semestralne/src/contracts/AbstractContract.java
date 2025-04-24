package contracts;

public abstract class AbstractContract {
    private String contractNumber;
    protected InsuranceCompany insurer;
    protected Person policyHolder;
    protected ContractPaymentData contractPaymentData;
    protected int coverageAmount;
    protected boolean isActive;


    public AbstractContract(String contractNumber, InsuranceCompany insurer, Person policyHolder, ContractPaymentData contractPaymentData, int coverageAmount) {
        //toDo
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public Person getPolicyHolder() {
        return policyHolder;
    }

    public InsuranceCompany getInsurer() {
        return insurer;
    }

    public int getCoverageAmount() {
        return coverageAmount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setInactive() {
        isActive = false;
    }

    public void setCoverageAmount(int coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public ContractPaymentData getContractPaymentData() {
        return contractPaymentData;
    }

    public void pay(int amount) {
        //toDo
    }

    public void updateBalance() {
        //toDo
    }
}