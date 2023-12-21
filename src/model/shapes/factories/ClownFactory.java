package model.shapes.factories;

import model.ImageObject;
import model.ShapeLoader;
import model.shapes.Clown;

public class ClownFactory implements ShapeLoader {
    public static final boolean CONTROLLED = true;

    @Override
    public ImageObject loadShape(int x, int y) {
        return new Clown(x,y);
    }

    @Override
    public boolean getControllable() {
        return CONTROLLED;
    }
}
