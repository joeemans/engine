package Model;

import Controller.Controller;
import View.Circus;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import Controller.Subject;
import Controller.Observer;

public class Clown extends ImageObject implements PlateCatcher, Subject {
    private Stack <GameObject> leftTray = new Stack<>();
    private Stack <GameObject> rightTray = new Stack<>();
    private List<Observer> observers = new ArrayList<>();

    public Clown(int x, int y, Controller instance) {
        super(x, y, Type.CLOWN);
        addObserver(instance);
    }

    @Override
    public boolean catchPlateWithLeft(GameObject plate) {
        if(Math.abs((plate.getX()+plate.getWidth()/2) - (this.getX()+this.getWidth()/2) + 50) <= plate.getWidth() &&
                (Math.abs((plate.getY()+plate.getHeight()/2) - (this.getY()+this.getHeight()/2) + 70) <= plate.getHeight())){
            leftTray.add(plate);
            notifyObservers();
            return true;
        }
        return false;
    }

    @Override
    public boolean catchPlateWithRight(GameObject plate) {
        if(Math.abs((plate.getX()+plate.getWidth()/2) - (this.getX()+this.getWidth()/2) - 50) <= plate.getWidth() &&
                (Math.abs((plate.getY()+plate.getHeight()/2) - (this.getY()+this.getHeight()/2) + 70) <= plate.getHeight())){
            rightTray.add(plate);
            notifyObservers();
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
