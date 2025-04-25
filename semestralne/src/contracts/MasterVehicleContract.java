package contracts;

import company.InsuranceCompany;
import objects.LegalForm;
import objects.Person;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MasterVehicleContract extends AbstractVehicleContract{
    private final Set<SingleVehicleContract> childContracts;

    // Zmluvy vozidiel sa do MasterVehicleContract pridávajú podaním požiadavky na poisťovateľa, tzn. zavolá
    //sa metóda InsuranceCompany::moveSingleVehicleContractToMasterVehicleContract.
    //MasterVehicleContract sa považuje za neaktívny, ak sú neaktívne všetky jeho dcérske zmluvy. Ak žiadne
    //dcérske zmluvy nemá, tak sa jeho aktivita vyhodnocuje podľa atribútu isActive. Metóda setInactive musí
    //nastaviť ako neaktívne všetky jeho dcérske zmluvy, aj svoj atribút isActive.

    public MasterVehicleContract(String contractNumber, InsuranceCompany insurer, Person beneficiary, Person policyHolder) {
        super(contractNumber, insurer, beneficiary, policyHolder, null, 0);

        // nvm ktore
        if (super.getPolicyHolder().getLegalForm()!=LegalForm.LEGAL) throw new IllegalArgumentException("Legal form does not match");
        if (policyHolder.getLegalForm()!= LegalForm.LEGAL) throw new IllegalArgumentException();

        childContracts = new HashSet<SingleVehicleContract>();
    }

    public Set<SingleVehicleContract> getChildContracts() {
        return childContracts;
    }

    public void requestAdditionOfChildContract(SingleVehicleContract contract) {
        LocalDateTime currentTime = insurer.getCurrentTime();
        InsuranceCompany insurer1 = new InsuranceCompany(currentTime);
        insurer1.moveSingleVehicleContractToMasterVehicleContract(this, contract);
    }
}