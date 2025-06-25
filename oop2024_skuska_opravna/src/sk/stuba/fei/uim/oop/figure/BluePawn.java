package sk.stuba.fei.uim.oop.figure;

public class BluePawn extends Pawn {
    @Override
    public void move() {
        setPosition(getPosition() + 1);
        System.out.println("Blue Pawn moved to position: " + getPosition());
    }
}
