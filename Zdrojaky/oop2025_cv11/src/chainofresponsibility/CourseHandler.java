package chainofresponsibility;

public class CourseHandler implements Handler {
    public Database database;

    public CourseHandler(Database database) {
        this.database = database;
    }

    @Override
    public Response run(Request request, Context context) {
        try {
            String courseIdString = PathParser.parse(request.path())[1]; // TODO osetrit ak 'path' obsahuje viac ako 2 casti
            Integer courseIdeInteger = Integer.valueOf(courseIdString);
            String course = database.getCourse(courseIdeInteger);
            return new Response(200, course);
        }
        catch (IllegalArgumentException | IndexOutOfBoundsException exception) {
            return new Response(404, "chybne ID predmetu");
        }
    }
}
