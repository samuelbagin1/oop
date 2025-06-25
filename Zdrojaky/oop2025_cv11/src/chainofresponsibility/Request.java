package chainofresponsibility;

public record Request (
        String userName,
        String password,
        String path
){
}