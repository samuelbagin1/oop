package bridge;

public class UnicodeSemaphore implements Semaphore {
    private static final String red = asString(0x1F534);
    private static final String yellow = asString(0x1F7E1);
    private static final String green = asString(0x1F7E2);

    private static String asString(int code) {
        return new String(Character.toChars(code));
    }

    @Override
    public void setRed() {
        System.out.print(red);
    }

    @Override
    public void setYellow() {
        System.out.print(yellow);
    }

    @Override
    public void setGreen() {
        System.out.print(green);
    }
}
