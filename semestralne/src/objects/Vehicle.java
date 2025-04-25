package objects;
import contracts.SingleVehicleContract;

public class Vehicle {
    private final String licensePlate;
    private final int originalValue;

    public Vehicle(String licensePlate, int originalValue) {
        if (licensePlate == null || licensePlate.isEmpty() || licensePlate.length()!=7) throw new IllegalArgumentException("License plate cannot be null or empty");
        if (originalValue<=0) throw new IllegalArgumentException("Original value cannot be less than or equal to 0");

        // check ci je SPZ platna
        for (int i=0; i<licensePlate.length(); i++) {
            if (!(licensePlate.charAt(i)>='0' && licensePlate.charAt(i)<='9') && !(licensePlate.charAt(i)>='A' && licensePlate.charAt(i)<='Z')) {
                throw new IllegalArgumentException("License plate is not valid");
            }
        }

        this.licensePlate = licensePlate;
        this.originalValue = originalValue;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getOriginalValue() {
        return originalValue;
    }
}