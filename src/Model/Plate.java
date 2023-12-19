package Model;

import eg.edu.alexu.csd.oop.game.*;

public class Plate extends ImageObject implements Faller{
    private static final int FALLING_DECREMENT = 1;
    private boolean inTray;

    public Plate(int x, int y, Type type) {
        super(x, y, type);
    }

    @Override
    public void freeFall() {
            setY( getY() + FALLING_DECREMENT);
    }

    @Override
    public void setY(int y) {
      if (!isInTray()){
          super.setY(y);
      }
    }

    public boolean isInTray() {
        return inTray;
    }

    public void setInTray() {
        this.inTray = true;
    }
}
