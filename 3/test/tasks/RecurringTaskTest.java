package tasks;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.RecordComponent;

import static org.junit.jupiter.api.Assertions.*;

class RecurringTaskTest {

    @Test
    void run() {
        {
            RecurringTask task = new RecurringTask("Nakrmit rybicky.", 0, 5, null);
            String id = task.getId();
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            task.run();
            String output = out.toString();
            assertEquals("Task " + id + ": Nakrmit rybicky."+ System.lineSeparator(), output);

            assertEquals(5, task.getRunAtTick());
            assertNull(task.getNumberOfRuns());
        }
        {
            RecurringTask task = new RecurringTask("Podojit Milku.", 0, 5, 3);
            String id = task.getId();
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            task.run();
            String output = out.toString();
            assertEquals("Task " + id + ": Podojit Milku."+ System.lineSeparator(), output);

            assertEquals(5, task.getRunAtTick());
            assertEquals(2, task.getNumberOfRuns());
        }
    }
}