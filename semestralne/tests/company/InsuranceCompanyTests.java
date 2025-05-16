package company;

import contracts.AbstractContract;
import contracts.SingleVehicleContract;
import objects.Person;
import objects.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import payment.ContractPaymentData;
import payment.PremiumPaymentFrequency;
import shared.TestContract;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class InsuranceCompanyTests {
    String contractNumber;
    Person beneficiary;
    Person policyHolder;
    int proposedPremium;
    PremiumPaymentFrequency proposedPaymentFrequency;
    String licencePlate;
    int originalPrice;
    Vehicle vehicleToInsure;
    LocalDateTime currentTime;
    InsuranceCompany insuranceCompany;

    @BeforeEach
    public void setUp() {
        contractNumber = "a2352fs";
        beneficiary = new Person("7201011235");
        policyHolder = new Person("132453");
        proposedPremium = 25;
        proposedPaymentFrequency = PremiumPaymentFrequency.QUARTERLY;
        licencePlate = "BA111PZ";
        originalPrice = 1000;
        vehicleToInsure = new Vehicle(licencePlate, originalPrice);
        currentTime = LocalDateTime.now();
        insuranceCompany = new InsuranceCompany(currentTime);
//        var person1 = new Person();
//        var person2 = new Person();
//        var person3 = new Person();
    }

    @Test
    public void givenCurrentTimeIsNull_whenCreatingInsuranceCompany_thenThrowsIllegalArgumentException() {
        LocalDateTime currentTime = null;

        assertThrows(IllegalArgumentException.class, () -> new InsuranceCompany(currentTime));
    }

    @Test
    public void givenCurrentTime_whenCreatingInsuranceCompany_thenCurrentTimeIsSet() {
        var currentTime = LocalDateTime.now();
        var insuranceCompany = new InsuranceCompany(currentTime);

        assertEquals(currentTime, insuranceCompany.getCurrentTime());
    }

    @Test
    public void givenCurrentTime_whenCreatingInsuranceCompany_thenPropertiesAreSet() {
        var currentTime = LocalDateTime.now();
        var insuranceCompany = new InsuranceCompany(currentTime);

        assertNotNull(insuranceCompany.getHandler());
        assertNotNull(insuranceCompany.getContracts());
        assertNotNull(insuranceCompany.getCurrentTime());
    }

    @Test
    public void givenNewCurrentTimeIsNull_whenSettingCurrentTime_thenThrowsIllegalArgumentException() {
        assertEquals(currentTime, insuranceCompany.getCurrentTime());

        LocalDateTime newCurrentTime = null;

        assertThrows(IllegalArgumentException.class, () -> insuranceCompany.setCurrentTime(newCurrentTime));
    }

    @Test
    public void givenNewCurrentTime_whenSettingCurrentTime_thenCurrentTimeIsSet() {
        assertEquals(currentTime, insuranceCompany.getCurrentTime());

        var newCurrentTime = currentTime.plusDays(1);

        insuranceCompany.setCurrentTime(newCurrentTime);

        assertEquals(newCurrentTime, insuranceCompany.getCurrentTime());
    }

    @Test
    public void givenContractNumberIsNull_whenInsuringVehicle_thenThrowsIllegalArgumentException() {
        contractNumber = null;

        assertThrows(IllegalArgumentException.class, () -> insuranceCompany.insureVehicle(
                contractNumber, beneficiary, policyHolder, proposedPremium, proposedPaymentFrequency, vehicleToInsure
        ));
    }

    @Test
    public void givenProposedPaymentFrequencyIsNull_whenInsuringVehicle_thenThrowsIllegalArgumentException(){
        proposedPaymentFrequency = null;

        assertThrows(IllegalArgumentException.class, () -> insuranceCompany.insureVehicle(
                contractNumber, beneficiary, policyHolder, proposedPremium, proposedPaymentFrequency, vehicleToInsure
        ));
    }

    @Test
    public void givenVehicleIsNull_whenInsuringVehicle_thenThrowsIllegalArgumentException(){
        vehicleToInsure = null;

        assertThrows(IllegalArgumentException.class, () -> insuranceCompany.insureVehicle(
                contractNumber, beneficiary, policyHolder, proposedPremium, proposedPaymentFrequency, vehicleToInsure
        ));
    }

    @Test
    public void givenYearlyPremiumIsLessThanTwoPercent_whenInsuringVehicle_thenThrowsIllegalArgumentException() {
        proposedPremium = 20;
        proposedPaymentFrequency = PremiumPaymentFrequency.QUARTERLY;
        originalPrice = 4500;
        vehicleToInsure = new Vehicle(licencePlate, originalPrice);
        var yearlyPremium = proposedPremium * 12 / proposedPaymentFrequency.getValueInMonths();

        assertTrue(yearlyPremium < (originalPrice * 2) / 100);

        assertThrows(IllegalArgumentException.class, () -> insuranceCompany.insureVehicle(
                contractNumber, beneficiary, policyHolder, proposedPremium, proposedPaymentFrequency, vehicleToInsure
        ));
    }

    @Test
    public void givenInsuranceCompanyHasContractWithContractNumber_whenInsuringVehicleWithTheSameContractNumber_thenThrowsIllegalArgumentException() {
        insuranceCompany.insureVehicle(
                contractNumber, beneficiary, policyHolder, proposedPremium, proposedPaymentFrequency, vehicleToInsure
        );

        assertThrows(IllegalArgumentException.class, () -> insuranceCompany.insureVehicle(
                contractNumber, beneficiary, policyHolder, proposedPremium, proposedPaymentFrequency, vehicleToInsure
        ));
    }

    @Test
    public void givenValidData_whenInsuringVehicle_thenReturnsValidSingleVehicleContract() {
        SingleVehicleContract singleVehicleContract = insuranceCompany.insureVehicle(
                contractNumber, beneficiary, policyHolder, proposedPremium, proposedPaymentFrequency, vehicleToInsure
        );

        assertEquals(insuranceCompany, singleVehicleContract.getInsurer());
        assertEquals(contractNumber, singleVehicleContract.getContractNumber());
        assertEquals(beneficiary, singleVehicleContract.getBeneficiary());
        assertEquals(policyHolder, singleVehicleContract.getPolicyHolder());
        assertNotNull(singleVehicleContract.getContractPaymentData());
        assertEquals(vehicleToInsure, singleVehicleContract.getInsuredVehicle());
        assertNotEquals(0, singleVehicleContract.getCoverageAmount());
    }

    @Test
    public void givenValidData_whenInsuringVehicle_thenCoverageAmountIsHalfThePriceOfInsuredVehicle() {
        var insurance = new InsuranceCompany(currentTime);

        SingleVehicleContract singleVehicleContract = insurance.insureVehicle(
                contractNumber, beneficiary, policyHolder, proposedPremium, proposedPaymentFrequency, vehicleToInsure
        );

        assertEquals(originalPrice / 2, singleVehicleContract.getCoverageAmount());
    }

    @Test
    public void givenValidData_whenInsuringVehicle_thenContractPaymentDataIsValid() {
        SingleVehicleContract singleVehicleContract = insuranceCompany.insureVehicle(
                contractNumber, beneficiary, policyHolder, proposedPremium, proposedPaymentFrequency, vehicleToInsure
        );

        ContractPaymentData contractPaymentData = singleVehicleContract.getContractPaymentData();

        assertEquals(proposedPremium, contractPaymentData.getPremium());
        assertEquals(proposedPremium, contractPaymentData.getOutstandingBalance());
        assertEquals(proposedPaymentFrequency, contractPaymentData.getPremiumPaymentFrequency());
        assertEquals(currentTime.plusMonths(proposedPaymentFrequency.getValueInMonths()), contractPaymentData.getNextPaymentTime());
    }

    @Test
    public void givenContractWithPaymentTimeIsBeforeCurrentTime_whenChargingPremiumOnContract_thenIncreasesOutstandingBalanceByPremiumUntilCurrentTimeIsBeforeNextPaymentTime() {
        int numberOfTimesToPay = 3;

        AbstractContract contract  = new TestContract(
                contractNumber,
                insuranceCompany,
                policyHolder,
                new ContractPaymentData(
                        proposedPremium,
                        proposedPaymentFrequency,
                        currentTime.minusMonths((numberOfTimesToPay - 1) * proposedPaymentFrequency.getValueInMonths()),
                        100
                ),
                100
        );

        ContractPaymentData contractPaymentData = contract.getContractPaymentData();
        int outstandingBalance = contractPaymentData.getOutstandingBalance();

        insuranceCompany.chargePremiumOnContract(contract);

        assertEquals(outstandingBalance + numberOfTimesToPay * proposedPremium , contractPaymentData.getOutstandingBalance());
        assertEquals(currentTime.plusMonths(proposedPaymentFrequency.getValueInMonths()), contractPaymentData.getNextPaymentTime());
    }

    @Test
    public void givenContractWithPaymentTimeIsEqualToCurrentTime_whenChargingPremiumOnContract_thenIncreasesOutstandingBalanceByPremiumUntilCurrentTimeIsBeforeNextPaymentTime() {
        AbstractContract contract  = new TestContract(
                contractNumber,
                insuranceCompany,
                policyHolder,
                new ContractPaymentData(
                        proposedPremium,
                        proposedPaymentFrequency,
                        currentTime,
                        100
                ),
                100
        );

        ContractPaymentData contractPaymentData = contract.getContractPaymentData();
        int outstandingBalance = contractPaymentData.getOutstandingBalance();

        insuranceCompany.chargePremiumOnContract(contract);

        assertEquals(outstandingBalance + proposedPremium, contractPaymentData.getOutstandingBalance());
        assertEquals(currentTime.plusMonths(proposedPaymentFrequency.getValueInMonths()), contractPaymentData.getNextPaymentTime());
    }

}