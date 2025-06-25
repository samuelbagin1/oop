package chainofresponsibility;

import java.util.Map;

public class RouterHandler implements Handler {
    private Map<String, Handler> routes;

    public RouterHandler(Map<String, Handler> routes) {
        this.routes = routes;
    }

    @Override
    public Response run(Request request, Context context) {
        try {
            String[] parts = PathParser.parse(request.path());
            String firstPart = parts[0];
            Handler next = routes.get(firstPart);
            if (next != null) {
                return next.run(request, context);
            } else {
                return new Response(404, "chyba cesta");
            }
        } catch (IndexOutOfBoundsException exception) {
            return new Response(404, "chybna cesta (2)");
        }
    }
}
