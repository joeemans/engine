package model.shapes;

import controller.DynamicImageLoader;
import model.*;

import java.awt.image.BufferedImage;

public class Bomb extends ImageObject implements Detonator {
    private static final int FALLING_DECREMENT = 1;
    private long detonatingTime;
    int clownWidth;
    int screenWidth;

    public Bomb(int x, int y, Type type) {
        super(x, y, type);
    }


    @Override
    public void freeFall() {
            setY( getY() + FALLING_DECREMENT);
    }

    @Override
    public void setClownWidth(int clownWidth) {
        this.clownWidth = clownWidth;
    }

    @Override
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public void detonate() {
        detonatingTime = System.currentTimeMillis();
        BufferedImage[] images = super.getSpriteImages();
        controller.DynamicImageLoader imageLoader = new DynamicImageLoader();
        images[0] = imageLoader.loadImage("resources/" + Type.EXPLOSION.getName() + ".png");
    }

    @Override
    public long getDetonatingTime() {
            return detonatingTime;
    }
}
