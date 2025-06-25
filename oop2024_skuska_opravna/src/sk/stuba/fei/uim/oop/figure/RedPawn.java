package sk.stuba.fei.uim.oop.figure;

public class RedPawn extends Pawn {
    @Override
    public void move() {
        setPosition(getPosition() + 1);
        System.out.println("Red Pawn moved to position: " + getPosition());
    }
}
