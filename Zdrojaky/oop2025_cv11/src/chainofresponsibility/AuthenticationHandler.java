package chainofresponsibility;

public class AuthenticationHandler implements Handler {
    private Handler next;
    private Database database;

    public AuthenticationHandler(Handler next, Database database) {
        this.next = next;
        this.database = database;
    }

    @Override
    public Response run(Request request, Context context) {
        String userName = request.userName();
        String password = request.password();
        boolean valid = database.areCredentialsValid(userName, password);
        if (valid) {
            context.setUserName(userName);
            return next.run(request, context);
        }
        else {
            return new Response(401, "chybne prihlasovacie udaje");
        }
    }
}
