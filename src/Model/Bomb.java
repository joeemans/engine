package Model;

public class Bomb extends ImageObject implements Faller {
    private static final int FALLING_DECREMENT = 1;

    public Bomb(int x, int y, Type type) {
        super(x, y, type);
    }

    @Override
    public void freeFall() {
            setY( getY() + FALLING_DECREMENT);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

}
