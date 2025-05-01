package contracts;

import company.InsuranceCompany;
import objects.LegalForm;
import objects.Person;

import javax.management.DescriptorKey;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MasterVehicleContract extends AbstractVehicleContract{
    private final Set<SingleVehicleContract> childContracts;


    public MasterVehicleContract(String contractNumber, InsuranceCompany insurer, Person beneficiary, Person policyHolder) {
        super(contractNumber, insurer, beneficiary, policyHolder, null, 0);

        if (policyHolder.getLegalForm()!= LegalForm.LEGAL) throw new IllegalArgumentException("Legal form does not match");

        childContracts = new LinkedHashSet<SingleVehicleContract>();
    }

    public Set<SingleVehicleContract> getChildContracts() {
        return childContracts;
    }

    public void requestAdditionOfChildContract(SingleVehicleContract contract) {
        insurer.moveSingleVehicleContractToMasterVehicleContract(this, contract);
    }

    @Override
    public boolean isActive() {
        if (childContracts.isEmpty()) {
            return super.isActive();
        }

        // ak je jeden contract aktivny, tak vrati true
        for (SingleVehicleContract contract : childContracts) {
            if (contract.isActive()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void setInactive() {
        super.setInactive();
        for (SingleVehicleContract contract : childContracts) {
            contract.setInactive();
        }
    }

    @Override
    public void pay(int amount) {
        insurer.getHandler().pay(this, amount);
    }
}