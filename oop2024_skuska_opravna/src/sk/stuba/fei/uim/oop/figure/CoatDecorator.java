package sk.stuba.fei.uim.oop.figure;

public class CoatDecorator extends Decorator {

    public CoatDecorator(Figure figure) {
        super(figure);
    }

    @Override
    public void move() {
        figure.move();
        figure.setPosition(getPosition() + 3);
    }
}
