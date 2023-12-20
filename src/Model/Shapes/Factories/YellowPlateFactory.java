package Model.Shapes.Factories;

import Model.ImageObject;
import Model.ShapeLoader;
import Model.Shapes.Plate;
import Model.Type;

public class YellowPlateFactory implements ShapeLoader {
    public static final boolean CONTROLLED = false;

    @Override
    public ImageObject loadShape(int x, int y) {
        return new Plate(x, y, Type.YELLOW_PLATE);
    }

    @Override
    public boolean getControllable() {
        return CONTROLLED;
    }
}
