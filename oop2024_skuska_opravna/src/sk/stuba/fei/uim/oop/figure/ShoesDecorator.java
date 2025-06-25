package sk.stuba.fei.uim.oop.figure;

public class ShoesDecorator extends Decorator {

    public ShoesDecorator(Figure figure) {
        super(figure);
    }

    @Override
    public void move() {
        figure.move();
        figure.move();
    }
}
