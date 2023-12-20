package Model.Shapes;

import Model.Faller;
import Model.ImageObject;
import Model.ShapeLoader;
import Model.Type;

public class Bomb extends ImageObject implements Faller, ShapeLoader {
    private static final int FALLING_DECREMENT = 1;

    public Bomb(int x, int y, Type type) {
        super(x, y, type);
    }

    public Bomb(){
        super(0,0,Type.BOMB);
    }



    @Override
    public void freeFall() {
            setY( getY() + FALLING_DECREMENT);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public ImageObject loadShape(int x, int y) {
        return new Bomb(x, y, Type.BOMB);
    }
}
