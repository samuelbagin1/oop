package chainofresponsibility;

public class DepartmentHandler implements Handler {
    private Database database;

    public DepartmentHandler(Database database) {
        this.database = database;
    }

    @Override
    public Response run(Request request, Context context) {
        try {
            String departmentIdString = PathParser.parse(request.path())[1]; // TODO osetrit ak 'path' obsahuje viac ako 2 casti
            Integer departmentIdInteger = Integer.valueOf(departmentIdString);
            String department = database.getDepartment(departmentIdInteger);
            return new Response(200, department);
        }
        catch (IllegalArgumentException | IndexOutOfBoundsException exception) {
            return new Response(404, "chybne ID pracoviska");
        }
    }
}
