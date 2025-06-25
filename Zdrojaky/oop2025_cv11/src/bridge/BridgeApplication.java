package bridge;

public class BridgeApplication {
    public static void main(String[] args) {
        Semaphore unicode = new UnicodeSemaphore();
        unicode.setRed();
        unicode.setYellow();
        unicode.setGreen();
        System.out.println();

        Semaphore linux = new LinuxSemaphore();
        linux.setRed();
        linux.setYellow();
        linux.setGreen();
        System.out.println();

        Control c1 = new Control(unicode);
        c1.stop();
        c1.go();

        Control c2 = new Control(linux);
        c2.stop();
        c2.go();

        ControlWithTimer c3 = new ControlWithTimer(unicode);
        c3.stop(10);
        c3.go(5);

        ControlWithTimer c4 = new ControlWithTimer(linux);
        c4.stop(8);
        c4.go(7);
    }
}
