package contract;


public class InvalidContractException extends RuntimeException {
    public InvalidContractException(String message) {
        super(" " + message);
    }
}
