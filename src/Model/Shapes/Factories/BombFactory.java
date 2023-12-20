package Model.Shapes.Factories;

import Model.ImageObject;
import Model.ShapeLoader;
import Model.Shapes.Bomb;
import Model.Type;

public class BombFactory implements ShapeLoader {
    public static final boolean CONTROLLED = false;

    @Override
    public ImageObject loadShape(int x, int y) {
        return new Bomb(x,y, Type.BOMB);
    }

    @Override
    public boolean getControllable() {
        return CONTROLLED;
    }
}
