package model.shapes.factories;

import model.ImageObject;
import model.ShapeLoader;
import model.shapes.Bomb;
import model.Type;

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
