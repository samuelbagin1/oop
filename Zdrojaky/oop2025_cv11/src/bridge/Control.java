package bridge;

public class Control {
    private Semaphore semaphore;

    public Control(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void stop() {
        // if (ak nie je cervana
        System.out.print("stop: ");
        semaphore.setYellow();
        System.out.print(" ");
        semaphore.setRed();
        System.out.println();
    }

    public void go() {
        // if (ak nie je zelena
        System.out.print("go: ");
        semaphore.setYellow();
        System.out.print(" ");
        semaphore.setGreen();
        System.out.println();
    }
}
