package sk.stuba.fei.uim.oop.figure;

abstract class Pawn implements Figure {
    private int position = 0;

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }
}

abstract class Bishop implements Figure {
    private int position = 0;

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }
}

abstract class Queen implements Figure {
    private int position = 0;


    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }
}


