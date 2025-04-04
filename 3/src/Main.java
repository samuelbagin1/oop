import handler.TaskHandler;
import tasks.RecurringTask;
import tasks.SimpleTask;

public class Main {
    public static void main(String[] args) {
        SimpleTask t1 = new SimpleTask("uvariť obed", 2);
        SimpleTask t2 = new SimpleTask("nakúpiť potraviny", 1);
        RecurringTask t3 = new RecurringTask("umyť si zuby", 1, 2, 2);
        TaskHandler taskHandler = new TaskHandler();
        taskHandler.addTask(t1);
        taskHandler.addTask(t2);
        taskHandler.addTask(t3);
        taskHandler.tickLoop(6);
    }
}