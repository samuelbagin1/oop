package chainofresponsibility;

public class ChainOfResponsibilityApplication {
    public static void main(String[] args) {
        UniversityServer server = new UniversityServer();

        Request request1 = new Request("Peter", "12345", "/course/1");
        Response response1 = server.getData(request1);
        System.out.println(response1);

        Request request2 = new Request("Peter", "12345", "/department/2");
        Response response2 = server.getData(request2);
        System.out.println(response2);
    }
}
