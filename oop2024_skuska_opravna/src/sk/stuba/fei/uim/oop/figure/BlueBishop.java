package sk.stuba.fei.uim.oop.figure;

public class BlueBishop extends Bishop {
    @Override
    public void move() {
        setPosition(getPosition() + 4);
        System.out.println("Blue Bishop moved to position: " + getPosition());
    }
}
