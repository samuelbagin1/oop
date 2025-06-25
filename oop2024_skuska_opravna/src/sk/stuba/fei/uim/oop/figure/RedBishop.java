package sk.stuba.fei.uim.oop.figure;

public class RedBishop extends Bishop {
    @Override
    public void move() {
        setPosition(getPosition() + 3);
        System.out.println("Red Bishop moved to position: " + getPosition());
    }
}
