package tasks;

public class RecurringTask extends AbstractTask {
    private long delayBetweenRuns;
    private Integer numberOfRuns;

    public RecurringTask(String message, long runAtTick, long delayBetweenRuns, Integer numberOfRuns) {
        //this.message = message;
        //this.runAtTick = runAtTick;
        super(message, runAtTick);
        assert delayBetweenRuns>=0 : "delayBetweenRuns must be greater than 0";
        assert numberOfRuns==null || numberOfRuns>=0 : "numberOfRuns must be non-null and non-zero";

        this.delayBetweenRuns = delayBetweenRuns;
        this.numberOfRuns = numberOfRuns;
    }

    public long getDelayBetweenRuns() {
        return delayBetweenRuns;
    }

    public Integer getNumberOfRuns() {
        return numberOfRuns;
    }

    @Override
    public void run() {
        System.out.println("Task " + getId() + ": " + message);
        if (numberOfRuns != null) {
            numberOfRuns--;
        }
        runAtTick+=delayBetweenRuns;
    }

    @Override
    public boolean isFinished() {
        return numberOfRuns!=null && numberOfRuns<=0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj==null || getClass()!=obj.getClass()) return false;
        RecurringTask other = (RecurringTask) obj;
        return getId().equals(other.getId());
    }
}
