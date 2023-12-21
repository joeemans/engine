package model.shapes;

import model.Faller;
import model.ImageObject;
import model.Type;

public class Plate extends ImageObject implements Faller {
    private static final int FALLING_DECREMENT = 1;
    private boolean inRightTray;
    private boolean inLeftTray;
    private int clownWidth;
    private int screenWidth;

    public Plate(int x, int y, Type type) {
        super(x, y, type);
    }

    @Override
    public void freeFall() {
            setY( getY() + FALLING_DECREMENT);
    }

    @Override
    public void setY(int y) {
      if (!isInRightTray() && !isInLeftTray()){
          super.setY(y);
      }
    }

    @Override
    public void setX(int x){
        if(!(isInLeftTray() && x >= this.screenWidth - this.clownWidth + this.getWidth()/32) &&
        !(isInRightTray() && x <= this.clownWidth/2 + this.getWidth()/32 - 5)){
           super.setX(x);
        }
    }

    public boolean isInRightTray() {
        return inRightTray;
    }
    public boolean isInLeftTray() {
        return inLeftTray;
    }

    public void setInRightTray() {
        this.inRightTray = true;
    }

    public void setInLeftTray() {
        this.inLeftTray = true;
    }

    public void setClownWidth(int clownWidth){
        this.clownWidth = clownWidth;
    }

    public void setScreenWidth(int screenWidth){
        this.screenWidth = screenWidth;
    }

}
