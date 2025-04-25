package objects;
import contracts.AbstractContract;
import contracts.TravelContract;
import contracts.AbstractVehicleContract;
import objects.LegalForm;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Person {
    private final String id;
    private final LegalForm legalForm;
    private int paidOutAmount;
    private final Set<AbstractContract> contracts;




    public Person(String id) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("ID cannot be null or empty");

        // kontrola rodneho cisla
        if (id.length()==10 || id.length()==9) {
            if (isValidBirthNumber(id)) {
                this.legalForm=LegalForm.NATURAL;
                this.id=id;
            }

        // kontrola ICO
        } else if (id.length()==6 || id.length()==8) {
            if (isValidRegistrationNumber(id)) {
                this.legalForm=LegalForm.LEGAL;
                this.id=id;
            }

        // ak nieje ID validne
        } else {
            throw new IllegalArgumentException("ID is not valid");
        }


        this.paidOutAmount=0;
        this.contracts=new HashSet<AbstractContract>();
    }


    // kontrola rodneho cisla
    public static boolean isValidBirthNumber(String birthNumber) {
        // ak nie je cislica dovidenia
        for (int i=0; i<birthNumber.length(); i++) {
            if (birthNumber.charAt(i)<'0' || birthNumber.charAt(i)>'9') return false;
        }

        // pomocne premenne
        int month = Integer.parseInt(birthNumber.substring(2, 4));
        int year = Integer.parseInt(birthNumber.substring(0, 2));
        int day = Integer.parseInt(birthNumber.substring(4, 6));

        // podmienky
        if (!(month>=1 && month<=12) && !(month>=51 && month<=62) ) return false;
        if (birthNumber.length()==9 && year>53) return false;
        if (birthNumber.length()==9 && year<=53) month-=50;

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
            for (int i=0; i<birthNumber.length(); i++) sum+=(-1)^i*Integer.parseInt(birthNumber.substring(i, i+1));

            if (sum%11!=0) return false;
        }


        return true;
    }

    // kontrola ICO
    public static boolean isValidRegistrationNumber(String registrationNumber) {
        // ak nie je cislica dovidenia
        for (int i=0; i<registrationNumber.length(); i++) {
            if (registrationNumber.charAt(i)<'0' || registrationNumber.charAt(i)>'9') return false;
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