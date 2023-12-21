package model.shapes.factories;

import model.ImageObject;
import model.ShapeLoader;
import model.shapes.Plate;
import model.Type;

public class WhitePlateFactory implements ShapeLoader {
    public static final boolean CONTROLLED = false;

    @Override
    public ImageObject loadShape(int x, int y) {
        return new Plate(x, y , Type.WHITE_PLATE);
    }

    @Override
    public boolean getControllable() {
        return CONTROLLED;
    }
}
