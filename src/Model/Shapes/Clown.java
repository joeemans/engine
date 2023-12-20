package Model.Shapes;

import Model.ImageObject;
import Model.PlateCatcher;
import Model.ShapeLoader;
import Model.Type;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import Controller.Subject;
import Controller.Observer;

public class Clown extends ImageObject implements PlateCatcher, Subject {
    private Stack <GameObject> leftTray = new Stack<>();
    private Stack <GameObject> rightTray = new Stack<>();
    private List<Observer> observers = new ArrayList<>();

    public Clown(int x, int y) {
        super(x, y, Type.CLOWN);
    }

    public Clown(){
        super(0,0,Type.CLOWN);
    }

    @Override
    public boolean catchPlate(Plate plate) {
        return catchPlateWithLeft(plate) || catchPlateWithRight(plate);
    }

    private boolean catchPlateWithLeft(Plate plate) {

        if(Math.abs(this.getX() + plate.getWidth()/2 - plate.getX() - plate.getWidth()/2) <= plate.getWidth()/2
                && (375 - (getLeftTraySize() * 10)) - plate.getY() <= 10
                && plate.getY() < 375 - (getLeftTraySize() * 10)
        ){
            System.out.println("CAUGHT WITH LEFT!");

            plate.setX(this.getX());
            plate.setY(this.getY() - 30 - this.getLeftTraySize()*10);
            plate.setInLeftTray();
            leftTray.add(plate);
            notifyObservers();
            return true;
        }
        return false;

    }

    private boolean catchPlateWithRight(Plate plate) {

        if(Math.abs(plate.getX() + plate.getWidth() - this.getX() - this.getWidth()) <= plate.getWidth()/2
                && (375 - (getRightTraySize() * 10)) - plate.getY() <= 10
                && plate.getY() < 375 - (getRightTraySize() * 10) ) {

            System.out.println("CAUGHT WITH RIGHT!");
            plate.setX((this.getX() + this.getWidth()/2));
            plate.setY(this.getY() - 10 - this.getRightTraySize()*10);
            plate.setInRightTray();
            rightTray.add(plate);
            notifyObservers();
            return true;
        }
        return false;

    }

    @Override
    public void setY(int y) {
        //
    }

    public int getLeftTraySize(){
        return leftTray.size();
    }

    public int getRightTraySize(){
        return rightTray.size();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateScore();
        }
    }

}
