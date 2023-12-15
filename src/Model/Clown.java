package Model;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.security.Key;
import java.util.ArrayList;
import java.util.Stack;

public class Clown extends ImageObject implements PlateCatcher {
    Stack <GameObject> leftTray = new Stack<>();
    Stack <GameObject> rightTray = new Stack<>();

    public Clown(int x, int y) {
        super(x, y, Type.CLOWN);
    }

    @Override
    public boolean catchPlate() {
        return true;
    }
    @Override
    public void setX(int x) {
        super.setX(x+10);
    }

}
