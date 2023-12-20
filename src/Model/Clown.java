package Model;

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

    @Override
    public boolean catchPlateWithLeft(GameObject plate) {

        if(Math.abs(this.getX() + plate.getWidth()/2 - plate.getX() - plate.getWidth()/2) <= plate.getWidth()/2
                && (Math.abs((plate.getY()+plate.getHeight()/2) - (this.getY()+this.getHeight()/2) + 70 + getLeftTraySize() * 7) <= plate.getHeight())){
            leftTray.add(plate);
            notifyObservers();
            return true;
        }
        return false;

    }

    @Override
    public boolean catchPlateWithRight(GameObject plate) {

        if(Math.abs(plate.getX() + plate.getWidth() - this.getX() - this.getWidth()) <= plate.getWidth()/2
                && ((Math.abs((plate.getY()+plate.getHeight()/2) - (this.getY()+this.getHeight()/2) + 70 + getRightTraySize() * 7) <= plate.getHeight()))){
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
