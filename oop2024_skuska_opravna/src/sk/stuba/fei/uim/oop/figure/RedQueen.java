package sk.stuba.fei.uim.oop.figure;

public class RedQueen extends Queen {
    @Override
    public void move() {
        setPosition(getPosition() + 6);
        System.out.println("Red Queen moved to position: " + getPosition());
    }
}
