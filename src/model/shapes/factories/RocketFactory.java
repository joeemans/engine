package model.shapes.factories;

import model.ImageObject;
import model.ShapeLoader;
import model.Type;
import model.shapes.Bomb;
import model.shapes.Rocket;

public class RocketFactory implements ShapeLoader {
    public static final boolean CONTROLLED = false;

    @Override
    public ImageObject loadShape(int x, int y) {
        return new Rocket(x,y);
    }

    @Override
    public boolean getControllable() {
        return CONTROLLED;
    }
}
