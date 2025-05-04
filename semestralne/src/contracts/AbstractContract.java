package contracts;
import company.InsuranceCompany;
import payment.ContractPaymentData;
import objects.Person;
import java.util.Objects;

public abstract class AbstractContract {
    private final String contractNumber;
    protected final InsuranceCompany insurer;   // poistovatel
    protected final Person policyHolder;        // poistnik
    protected final ContractPaymentData contractPaymentData;
    protected int coverageAmount;              // poistne plnenie
    protected boolean isActive;


    public AbstractContract(String contractNumber, InsuranceCompany insurer, Person policyHolder, ContractPaymentData contractPaymentData, int coverageAmount) {
        if (contractNumber==null || contractNumber.isEmpty()) throw new IllegalArgumentException("Contract number cannot be null or empty");
        if (insurer==null) throw new IllegalArgumentException("Insurance Company cannot be null");
        if (policyHolder==null) throw new IllegalArgumentException("Policy Holder cannot be null");
        if (coverageAmount<0) throw new IllegalArgumentException("Coverage amount cannot be less than 0");

        this.isActive = true;
        this.contractPaymentData=contractPaymentData;
        this.coverageAmount=coverageAmount;
        this.policyHolder=policyHolder;
        this.insurer=insurer;
        this.contractNumber = contractNumber;
    }


    // GETTERS
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
    public ContractPaymentData getContractPaymentData() {
        return contractPaymentData;
    }

    // SETTERS
    public void setInactive() {
        isActive = false;
    }
    public void setCoverageAmount(int coverageAmount) {
        if (coverageAmount<0) throw new IllegalArgumentException("Coverage amount cannot be less than 0");
        this.coverageAmount = coverageAmount;
    }




    // METODY
    public void pay(int amount) {
        insurer.getHandler().pay(this, amount);
    }

    public void updateBalance() {
        insurer.chargePremiumOnContract(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractContract)) return false;
        AbstractContract that = (AbstractContract) o;
        return contractNumber.equals(that.contractNumber) && insurer.equals(that.insurer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractNumber, insurer);
    }
}