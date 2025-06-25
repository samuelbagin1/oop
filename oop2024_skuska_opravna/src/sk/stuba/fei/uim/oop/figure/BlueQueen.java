package sk.stuba.fei.uim.oop.figure;

public class BlueQueen extends Queen {
    @Override
    public void move() {
        setPosition(getPosition() + 5);
        System.out.println("Blue Queen moved to position: " + getPosition());
    }
}
