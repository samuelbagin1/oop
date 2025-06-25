package sk.stuba.fei.uim.oop.figure;

abstract class Decorator implements Figure {
    protected Figure figure;

    public Decorator(Figure figure) {
        this.figure = figure;
    }

    @Override
    public int getPosition() {
        return figure.getPosition();
    }

    @Override
    public void setPosition(int position) {
        figure.setPosition(position);
    }
}


