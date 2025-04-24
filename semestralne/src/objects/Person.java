package objects;

public class Person {
    private String id;
    private LegalForm legalForm;
    private int paidOutAmount;
    private Set<AbstractContract> contracts;

    public Person(String id) {
        this.id = id;
    }

    public static boolean isValidBirthNumber(String birthNumber) {
        //toDo
        return null;
    }

    public static boolean isValidRegistrationNumber(String registrationNumber) {
        //toDo
        return null;
    }

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

    public void addContract(AbstractContract contract) {
        //toDo
    }

    public void payout(int paidOutAmount) {
        //toDo
    }
}