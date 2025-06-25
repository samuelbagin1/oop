package chainofresponsibility;

import java.util.Map;

public class UniversityServer {
    private Database database;
    private Handler authentication; // prvy handler

    public UniversityServer() {
        database = new Database();

        Handler course = new CourseHandler(database);
        Handler department = new DepartmentHandler(database);

        Handler router = new RouterHandler(Map.of(
           "course", course,
           "department", department
        ));

        authentication = new AuthenticationHandler(router, database);
    }

    public Response getData(Request request) {
        return authentication.run(request, new Context());
    }
}
