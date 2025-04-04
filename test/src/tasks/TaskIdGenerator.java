package tasks;

public class TaskIdGenerator {
    private static int nextId = 0;

    private TaskIdGenerator() {}

    public static String generateTaskId(AbstractTask task) {
        String taskClassName = task.getClass().getSimpleName();
        return taskClassName + String.valueOf(nextId++);
    }

}
