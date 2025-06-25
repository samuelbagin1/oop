package chainofresponsibility;

public interface Handler {
    Response run(Request request, Context context);
}
