package Model.Shapes.Factories;

import Model.ImageObject;
import Model.ShapeLoader;
import Model.Shapes.Clown;

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
