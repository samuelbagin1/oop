package contracts;

import company.InsuranceCompany;
import objects.LegalForm;
import objects.Person;


import java.util.LinkedHashSet;
import java.util.Set;

public class MasterVehicleContract extends AbstractVehicleContract{
    private final Set<SingleVehicleContract> childContracts;

    // KONSTRUKTOR
    public MasterVehicleContract(String contractNumber, InsuranceCompany insurer, Person beneficiary, Person policyHolder) {
        super(contractNumber, insurer, beneficiary, policyHolder, null, 0);
        if (policyHolder.getLegalForm()!= LegalForm.LEGAL) throw new IllegalArgumentException("Legal form does not match");

        childContracts = new LinkedHashSet<>();

    }


    // GETTER
    public Set<SingleVehicleContract> getChildContracts() {
        return childContracts;
    }

    // MANIPULATOR
    public void requestAdditionOfChildContract(SingleVehicleContract contract) {
        insurer.moveSingleVehicleContractToMasterVehicleContract(this, contract);
    }




    // OVERRIDY - docielenie aby sa zavolala spravna metoda pri Active a Paymente z AbstractContract
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

    @Override
    public void updateBalance() {
        insurer.chargePremiumOnContract(this);
    }

}