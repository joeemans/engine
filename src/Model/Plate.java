package Model;

public class Plate extends ImageObject implements Faller{
    public Plate(int x, int y, Type type) {
        super(x, y, type);
    }

    @Override
    public void freefall() {

    }
}
