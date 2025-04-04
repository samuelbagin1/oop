package tasks;

public class SimpleTask extends AbstractTask{
    private boolean isFinished=false;

    public SimpleTask(String message, long runAtTick) {
        //this.message = message;
        //this.runAtTick = runAtTick;
        super(message, runAtTick);
    }

    @Override
    public void run() {
        System.out.println("Task " + getId() + ": " + message);
        isFinished=true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj==null || getClass()!=obj.getClass()) return false;
        SimpleTask other = (SimpleTask) obj;
        return getId().equals(other.getId());
    }
}
