package bridge;

public class ControlWithTimer extends Control {

    public ControlWithTimer(Semaphore semaphore) {
        super(semaphore);
    }

    public void stop(int time) {
        // if (ak nie je cervena)
        countDown(time);
        super.stop();
    }

    public void go(int time) {
        // if (ak nie je zelena)
        countDown(time);
        super.go();
    }

    private void countDown(int time) {
        for (int i = time; i > 0; -- i) {
            System.out.print(i + " ");
        }
    }
}
