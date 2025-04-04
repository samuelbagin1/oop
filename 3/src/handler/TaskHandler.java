package handler;

import tasks.AbstractTask;

public class TaskHandler {
    private static final int MAXIMUM_TASKS = 5;
    private AbstractTask tasks[];
    private int taskIndex;

    public TaskHandler() {
        tasks = new AbstractTask[MAXIMUM_TASKS];
        taskIndex = 0;
    }

    public AbstractTask[] getTasks() {
        return tasks;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public void addTask(AbstractTask task) {
        if (taskIndex < MAXIMUM_TASKS) tasks[taskIndex++] = task;
    }

    public void removeTask(AbstractTask task) {
        for (int i=0; i<taskIndex; i++) {
            if (tasks[i].equals(task)) {
                for (int j=i; j<taskIndex-1; j++) {
                    tasks[j]=tasks[j+1];
                }
                tasks[--taskIndex]=null;
                break;
            }
        }
    }



    /*
    public void tickLoop(long durationNumberOfTicks) {
        for (long i=0; i<durationNumberOfTicks; i++) {
            System.out.println("Current tick: " + i);

            for (int j=0; j<taskIndex; j++) {
                if (tasks[j].getRunAtTick()==i) {
                    tasks[j].run();
                    if (tasks[j].isFinished()) {
                        removeTask(tasks[j]);
                        --j;
                    }
                }
            }


            System.out.println("===");
        }
    }

     */


      public void tickLoop(long durationNumberOfTicks) {
        for (long i=0; i<durationNumberOfTicks; i++) {
            System.out.println("Current tick: " + i);

            int j=0;
            while (j<taskIndex) {
                if (tasks[j].getRunAtTick()==i) {
                    tasks[j].run();
                    if (tasks[j].isFinished()) {
                        removeTask(tasks[j]);
                        continue;
                    }
                }
                j++;
            }


            System.out.println("===");
        }
    }


}
