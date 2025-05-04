import company.InsuranceCompany;
import contracts.*;
import objects.Person;
import objects.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import payment.ContractPaymentData;
import payment.PremiumPaymentFrequency;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class updateBalanceTests {

    private Person policyHolder;
    private Vehicle vehicle1;
    private LocalDateTime testTime;

    @BeforeEach
    void setUp() {
        policyHolder = new Person("8301143697");
        vehicle1 = new Vehicle("AA111AA", 15_000);
        testTime = LocalDateTime.of(2023, 1, 1, 12, 0);
    }

    @Test
    public void testUpdateBalanceCallsCorrectChargePremiumMethod() {
        // Create a custom test implementation of InsuranceCompany to track method calls
        class TestInsuranceCompany extends InsuranceCompany {
            boolean abstractContractMethodCalled = false;
            boolean masterContractMethodCalled = false;
            AbstractContract lastContractCharged = null;

            public TestInsuranceCompany(LocalDateTime currentTime) {
                super(currentTime);
            }

            @Override
            public void chargePremiumOnContract(AbstractContract contract) {
                abstractContractMethodCalled = true;
                lastContractCharged = contract;
                super.chargePremiumOnContract(contract);
            }

            @Override
            public void chargePremiumOnContract(MasterVehicleContract contract) {
                masterContractMethodCalled = true;
                lastContractCharged = contract;
                super.chargePremiumOnContract(contract);
            }

            public void resetTracking() {
                abstractContractMethodCalled = false;
                masterContractMethodCalled = false;
                lastContractCharged = null;
            }
        }

        // Create test objects
        TestInsuranceCompany testInsurer = new TestInsuranceCompany(LocalDateTime.now());
        Person legalEntity = new Person("123456");

        // Test case 1: SingleVehicleContract should call the general method
        ContractPaymentData paymentData = new ContractPaymentData(
                100, PremiumPaymentFrequency.MONTHLY, testInsurer.getCurrentTime(), 0);
        SingleVehicleContract singleContract = new SingleVehicleContract(
                "SV001", testInsurer, null, policyHolder,
                paymentData, 5000, vehicle1);

        // Reset tracking and call updateBalance
        testInsurer.resetTracking();
        singleContract.updateBalance();

        // Verify the correct method was called
        assertTrue(testInsurer.abstractContractMethodCalled,
                "chargePremiumOnContract(AbstractContract) should be called for SingleVehicleContract");
        assertFalse(testInsurer.masterContractMethodCalled,
                "chargePremiumOnContract(MasterVehicleContract) should not be called for SingleVehicleContract");
        assertEquals(singleContract, testInsurer.lastContractCharged,
                "The single contract should be passed to the method");

        // Test case 2: MasterVehicleContract should call the specialized method
        MasterVehicleContract masterContract = new MasterVehicleContract(
                "MC001", testInsurer, null, legalEntity);

        // Reset tracking and call updateBalance
        testInsurer.resetTracking();
        masterContract.updateBalance();

        // Verify the correct method was called
        assertFalse(testInsurer.abstractContractMethodCalled,
                "chargePremiumOnContract(AbstractContract) should not be called for MasterVehicleContract");
        assertTrue(testInsurer.masterContractMethodCalled,
                "chargePremiumOnContract(MasterVehicleContract) should be called for MasterVehicleContract");
        assertEquals(masterContract, testInsurer.lastContractCharged,
                "The master contract should be passed to the method");

        // Test case 3: Create a MasterVehicleContract with child contracts
        MasterVehicleContract parentContract = new MasterVehicleContract(
                "MC002", testInsurer, null, legalEntity);
        SingleVehicleContract childContract1 = new SingleVehicleContract(
                "CH001", testInsurer, null, policyHolder, paymentData, 6000, vehicle1);

        // Add child contract
        parentContract.requestAdditionOfChildContract(childContract1);

        // Reset tracking and call updateBalance on the master contract
        testInsurer.resetTracking();
        parentContract.updateBalance();

        // Verify master contract method was called (not abstract contract method)
        assertTrue(testInsurer.abstractContractMethodCalled,
                "For master contract with children, abstract method should be called");
        assertTrue(testInsurer.masterContractMethodCalled,
                "For master contract with children, master contract method should be called");
        assertEquals(childContract1, testInsurer.lastContractCharged,
                "The parent contract should be passed to the method");
    }

    @Test
    void testMoveSingleVehicleContractToMasterVehicleContract_Conditions() {
        InsuranceCompany insurer1 = new InsuranceCompany(testTime);
        InsuranceCompany insurer2 = new InsuranceCompany(testTime);

        // Create policyholders
        Person policyholder1 = new Person("123456");
        Person policyholder2 = new Person("567890");

        // Create vehicles
        Vehicle vehicle1 = new Vehicle("ABC1234", 10000);
        Vehicle vehicle2 = new Vehicle("XYZ5678", 15000);

        // Create contracts with insurer1 and policyholder1
        MasterVehicleContract masterContract = new MasterVehicleContract("M001", insurer1, null, policyholder1);
        insurer1.getContracts().add(masterContract);

        SingleVehicleContract singleContract = insurer1.insureVehicle(
                "S001", null, policyholder1, 200, PremiumPaymentFrequency.MONTHLY, vehicle1);

        // Test successful case - should not throw exception
        insurer1.moveSingleVehicleContractToMasterVehicleContract(masterContract, singleContract);
        assertTrue(masterContract.getChildContracts().contains(singleContract));
        assertFalse(insurer1.getContracts().contains(singleContract));

        // Test with inactive master contract
        MasterVehicleContract inactiveMasterContract = new MasterVehicleContract("M002", insurer1, null, policyholder1);
        insurer1.getContracts().add(inactiveMasterContract);
        inactiveMasterContract.setInactive();

        SingleVehicleContract singleContract2 = insurer1.insureVehicle(
                "S002", null, policyholder1, 200, PremiumPaymentFrequency.MONTHLY, vehicle2);

        // Should throw exception for inactive master contract
        assertThrows(InvalidContractException.class, () ->
                insurer1.moveSingleVehicleContractToMasterVehicleContract(inactiveMasterContract, singleContract2));

        // Test with inactive single contract
        SingleVehicleContract inactiveSingleContract = insurer1.insureVehicle(
                "S003", null, policyholder1, 300, PremiumPaymentFrequency.MONTHLY, vehicle2);
        inactiveSingleContract.setInactive();

        MasterVehicleContract masterContract3 = new MasterVehicleContract("M003", insurer1, null, policyholder1);
        insurer1.getContracts().add(masterContract3);

        // Should throw exception for inactive single contract
        assertThrows(InvalidContractException.class, () ->
                insurer1.moveSingleVehicleContractToMasterVehicleContract(masterContract3, inactiveSingleContract));

        // Test with different insurers
        MasterVehicleContract masterContract4 = new MasterVehicleContract("M004", insurer2, null, policyholder1);
        insurer2.getContracts().add(masterContract4);

        SingleVehicleContract singleContract4 = insurer1.insureVehicle(
                "S004", null, policyholder1, 200, PremiumPaymentFrequency.MONTHLY, vehicle1);

        // Should throw exception for different insurers
        assertThrows(InvalidContractException.class, () ->
                insurer1.moveSingleVehicleContractToMasterVehicleContract(masterContract4, singleContract4));

        // Test with different policyholders
        MasterVehicleContract masterContract5 = new MasterVehicleContract("M005", insurer1, null, policyholder1);
        insurer1.getContracts().add(masterContract5);

        SingleVehicleContract singleContract5 = insurer1.insureVehicle(
                "S005", null, policyholder2, 200, PremiumPaymentFrequency.MONTHLY, vehicle2);

        // Should throw exception for different policyholders
        assertThrows(InvalidContractException.class, () ->
                insurer1.moveSingleVehicleContractToMasterVehicleContract(masterContract5, singleContract5));
    }
}