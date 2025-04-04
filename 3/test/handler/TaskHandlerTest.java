package handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.AbstractTask;
import tasks.RecurringTask;
import tasks.SimpleTask;

import static org.junit.jupiter.api.Assertions.*;

class TaskHandlerTest {

    private TaskHandler handler;
    private AbstractTask task0;
    private RecurringTask task1;
    private SimpleTask task2;
    private AbstractTask task3;
    private AbstractTask task4;
    private AbstractTask task5;

    @BeforeEach
    void setUp() {
        handler = new TaskHandler();
        task0 = new SimpleTask("Odovzdat zadanie z OOP.", 5);
        task1 = new RecurringTask("Odovzdat zadanie z UMINT do OOP!", 2, 6, null);
        task2 = new SimpleTask("Otestovat zadanie.", 1);
        task3 = new RecurringTask("Poprosit cviciaceho o vratenie zadania", 5, 1, 2);
        task4 = new RecurringTask("Skontrolovat discord", 3, 1, 5);
        task5 = new SimpleTask("Ist skor spat!", 55);

        handler.addTask(task0);
        handler.addTask(task1);
    }

    @Test
    void addTask() {
        assertEquals(2, handler.getTaskIndex());
        handler.addTask(task2);
        handler.addTask(task3);
        handler.addTask(task4);
        handler.addTask(task5);
        assertEquals(5, handler.getTaskIndex());
    }

    @Test
    void removeTask() {
        handler.addTask(task2);
        handler.addTask(task3);
        handler.addTask(task4);
        handler.addTask(task5);

        handler.removeTask(task4);
        assertEquals(4, handler.getTaskIndex());
        handler.removeTask(task5);
        handler.removeTask(task3);
        handler.removeTask(task2);
        handler.removeTask(task1);
        handler.removeTask(task1);
        assertEquals(1, handler.getTaskIndex());

    }


}