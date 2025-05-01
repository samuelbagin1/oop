package objects;
import contracts.AbstractContract;
import contracts.TravelContract;
import contracts.AbstractVehicleContract;
import objects.LegalForm;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Person {
    private final String id;
    private final LegalForm legalForm;
    private int paidOutAmount;      // vyplatena suma
    private final Set<AbstractContract> contracts;




    public Person(String id) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("ID cannot be null or empty");

        // kontrola rodneho cisla
        if (isValidBirthNumber(id)) {
            this.legalForm=LegalForm.NATURAL;

        // kontrola ICO
        } else if (isValidRegistrationNumber(id)) {
            this.legalForm=LegalForm.LEGAL;

        // ak nieje ID validne
        } else {
            throw new IllegalArgumentException("ID is not valid");
        }


        this.id=id;
        this.paidOutAmount=0;
        this.contracts=new LinkedHashSet<AbstractContract>();
    }




    // kontrola rodneho cisla
    public static boolean isValidBirthNumber(String birthNumber) {
        if (birthNumber==null || birthNumber.isEmpty()) return false;
        if (birthNumber.length()!=9 && birthNumber.length()!=10) return false;

        // ak nie je cislica dovidenia
        for (int i=0; i<birthNumber.length(); i++) {
            if (!Character.isDigit(birthNumber.charAt(i))) return false;
        }

        // pomocne premenne
        int month = Integer.parseInt(birthNumber.substring(2, 4));
        int year = Integer.parseInt(birthNumber.substring(0, 2));
        int day = Integer.parseInt(birthNumber.substring(4, 6));


        // podmienky
        if (month>50) month-=50;
        if (month<1 || month>12) return false;
        if (birthNumber.length()==9 && year>53) return false;

//        if (birthNumber.length()==9) {
//            if (year>53) return false;
//            year+=1900;
//        } else {
//            year+=year<54 ? 2000 : 1900;
//        }

        // checknem ci je rok validny
        try {
            year+=birthNumber.length()==10 ? 2000 : 1900;
            if (year>LocalDate.now().getYear()) year-=100;
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return false;
        }


        // kontrolna suma pre roky po 1954 vratane
        if (birthNumber.length()==10 && year>=1954) {
            int sum=0;
            for (int i=0; i<birthNumber.length(); i++) sum+=(i%2==0 ? 1 : -1)*Character.getNumericValue(birthNumber.charAt(i));

            if (sum%11!=0) return false;
        }


        return true;
    }

    // kontrola ICO
    public static boolean isValidRegistrationNumber(String registrationNumber) {
        if (registrationNumber==null || registrationNumber.isEmpty()) return false;
        if (registrationNumber.length()!=6 && registrationNumber.length()!=8) return false;

        // ak nie je cislica dovidenia
        for (int i=0; i<registrationNumber.length(); i++) {
            if (!Character.isDigit(registrationNumber.charAt(i))) return false;
        }

        return true;
    }


    // GETTERY
    public String getId() {
        return id;
    }
    public int getPaidOutAmount() {
        return paidOutAmount;
    }
    public LegalForm getLegalForm() {
        return legalForm;
    }
    public Set<AbstractContract> getContracts() {
        return contracts;
    }


    // SETTERY
    public void addContract(AbstractContract contract) {
        if (contract==null) throw new IllegalArgumentException("Contract cannot be null");

        contracts.add(contract);
    }

    public void payout(int paidOutAmount) {
        if (paidOutAmount<=0) throw new IllegalArgumentException("Paid out amount cannot be less than 0");

        this.paidOutAmount+=paidOutAmount;
    }
}