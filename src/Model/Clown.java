package Model;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.security.Key;
import java.util.ArrayList;
import java.util.Stack;

public class Clown extends ImageObject implements PlateCatcher {
    private Stack <GameObject> leftTray = new Stack<>();
    private Stack <GameObject> rightTray = new Stack<>();

    public Clown(int x, int y) {
        super(x, y, Type.CLOWN);
    }

    @Override
    public boolean catchPlateWithLeft(GameObject plate) {
        if(Math.abs((plate.getX()+plate.getWidth()/2) - (this.getX()+this.getWidth()/2) - 50) <= plate.getWidth() &&
                (Math.abs((plate.getY()+plate.getHeight()/2) - (this.getY()+this.getHeight()/2) + 70) <= plate.getHeight())){
            leftTray.add(plate);
            return true;
        }
        return false;
    }

    @Override
    public boolean catchPlateWithRight(GameObject plate) {
        if(Math.abs((plate.getX()+plate.getWidth()/2) - (this.getX()+this.getWidth()/2) + 50) <= plate.getWidth() &&
                (Math.abs((plate.getY()+plate.getHeight()/2) - (this.getY()+this.getHeight()/2) + 70) <= plate.getHeight())){
            rightTray.add(plate);
            return true;
        }
        return false;
    }

    public int getLeftTraySize(){
        return leftTray.size();
    }

    public int getRightTraySize(){
        return rightTray.size();
    }

}
