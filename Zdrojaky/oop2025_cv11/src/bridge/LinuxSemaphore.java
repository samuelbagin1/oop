package bridge;

public class LinuxSemaphore implements Semaphore {
    private static final char circle = '\u25CF';
    private static final String red = "\u001B[31m";
    private static final String yellow = "\u001B[93m";
    private static final String green = "\u001B[32m";
    private static final String reset = "\u001B[0m";

    @Override
    public void setRed() {
        System.out.print(red + circle + reset);
    }

    @Override
    public void setYellow() {
        System.out.print(yellow + circle + reset);
    }

    @Override
    public void setGreen() {
        System.out.print(green + circle + reset);
    }
}
