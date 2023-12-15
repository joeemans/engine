package Model;

public class Plate extends ImageObject implements Faller{
    private static final int FALLING_DECREMENT = 1;

    public Plate(int x, int y, Type type) {
        super(x, y, type);
    }

    @Override
    public void freeFall() {
            setY( getY() + FALLING_DECREMENT);
    }
}
