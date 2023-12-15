package Model;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.ArrayList;

public class Clown extends ImageObject implements PlateCatcher{
    ArrayList <GameObject> leftTray = new ArrayList<>();
    ArrayList <GameObject> rightTray = new ArrayList<>();

    public Clown(int x, int y) {
        super(x, y, Type.CLOWN);
    }

    @Override
    public void catchPlate() {

    }
}
