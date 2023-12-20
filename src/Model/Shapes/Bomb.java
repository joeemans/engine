package Model.Shapes;

import Controller.DynamicImageLoader;
import Model.*;

import java.awt.image.BufferedImage;

public class Bomb extends ImageObject implements Faller , Detonator {
    private static final int FALLING_DECREMENT = 1;
    private long detonatingTime;

    public Bomb(int x, int y, Type type) {
        super(x, y, type);
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
    public void detonate() {
        detonatingTime = System.currentTimeMillis();
        BufferedImage[] images = super.getSpriteImages();
        Controller.DynamicImageLoader imageLoader = new DynamicImageLoader();
        images[0] = imageLoader.loadImage("resources/" + Type.EXPLOSION.getName() + ".png");
    }

    @Override
    public long getDetonatingTime() {
            return detonatingTime;
    }
}
