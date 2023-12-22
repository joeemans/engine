package model.shapes;

import model.ShakingObject;
import model.Type;

public class Rocket extends Bomb implements ShakingObject {
    private static final int SPEED_FACTOR = 3;
    private boolean shaken;
    private final static int HORIZONTAL_OFFSET = 4;

    public Rocket(int x, int y) {
        super(x, y, Type.ROCKET);
    }

    @Override
    public void freeFall() {
        setY( getY() + SPEED_FACTOR * FALLING_DECREMENT);
    }

    @Override
    public void shake() {
        if(shaken){
            setX(getX() - HORIZONTAL_OFFSET);
            shaken = false;
        } else {
            setX(getX() + HORIZONTAL_OFFSET);
            shaken = true;
        }
    }
}
