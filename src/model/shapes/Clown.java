package model.shapes;

import model.*;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.ArrayList;
import java.util.List;

import controller.Subject;
import controller.Observer;

public class Clown extends ImageObject implements UserControlled, Subject {

    public ArrayList<GameObject> leftTray = new ArrayList<>();
    public ArrayList<GameObject> rightTray = new ArrayList<>();

    private List<Observer> observers = new ArrayList<>();
    public boolean platesEmptied;


    public Clown(int x, int y) {
        super(x, y, Type.CLOWN);
    }


    @Override
    public boolean catchPlate(Plate plate) {
        return catchPlateWithLeft(plate) || catchPlateWithRight(plate);
    }

    @Override
    public boolean catchPlateWithLeft(Plate plate) {

        if(Math.abs(this.getX() + plate.getWidth()/2 - plate.getX() - plate.getWidth()/2) <= plate.getWidth()/2
                && (375 - (getLeftTraySize() * 10)) - plate.getY() <= 10
                && plate.getY() < 375 - (getLeftTraySize() * 10)
        ){

            if(!checkConsecutivePlatesOnLeft(plate.getType().getColor())) {
                plate.setX(this.getX());
                plate.setY(this.getY() - 40 - this.getLeftTraySize() * 10);
                plate.setInLeftTray();
                leftTray.add(plate);
                notifyObserversOnCatchingPlates();
            }
            return true;
        }
        return false;

    }

    @Override
    public boolean catchPlateWithRight(Plate plate) {

        if(Math.abs(plate.getX() + plate.getWidth() - this.getX() - this.getWidth()) <= plate.getWidth()/2
                && (375 - (getRightTraySize() * 10)) - plate.getY() <= 10
                && plate.getY() < 375 - (getRightTraySize() * 10) ) {

            System.out.println("CAUGHT WITH RIGHT!");

            if(! checkConsecutivePlatesOnRight(((plate).getType().getColor()))) {
                plate.setX((this.getX() + this.getWidth() / 2));
                plate.setY(this.getY() - 10 - this.getRightTraySize() * 10);
                plate.setInRightTray();
                rightTray.add(plate);
                notifyObserversOnCatchingPlates();
            }
            return true;
        }
        return false;

    }

    public boolean checkConsecutivePlatesOnRight(Color color) {
        if(this.rightTray.size() < 2){
            return false;
        }

        for (int i = 0; i < 2; i++) {
            if (!((Plate) this.rightTray.get(getRightTraySize() - i - 1)).getType().getColor().equals(color)) {
                return false;
            }
        }
            notifyObserversOnCatchingConsecutivePlates();
            removeConsecutivePlatesOnRight();
            platesEmptied = true;
            return true;
    }

    public boolean checkConsecutivePlatesOnLeft(Color color){
        if(this.leftTray.size() < 2){
            return false;
        }

        for (int i = 0; i < 2; i++) {
            if (!((Plate) this.leftTray.get(getLeftTraySize() - i - 1)).getType().getColor().equals(color)) {
                return false;
            }
        }
            notifyObserversOnCatchingConsecutivePlates();
            removeConsecutivePlatesOnLeft();
            platesEmptied = true;
            return true;
    }

    public void updateLeftTray() {
        for (Observer observer : observers) {
            observer.leftTrayUpdate();
        }
    }

    public void updateRightTray() {
        for (Observer observer : observers) {
            observer.rightTrayUpdate();
        }
    }

    public void removeConsecutivePlatesOnRight(){
        for(int i=1; i<=2; i++){
            updateRightTray();
        }
    }

    public void removeConsecutivePlatesOnLeft(){
        for(int i=1; i<=2; i++){
            updateLeftTray();
        }
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

    @Override
    public boolean catchBomb(Detonator bomb) {
        return catchBombWithLeft(bomb) || catchBombWithRight(bomb);
    }

    @Override
    public boolean catchBombWithLeft(Detonator bomb) {
        if(Math.abs(this.getX() + ((GameObject)bomb).getWidth()/2 - ((GameObject)bomb).getX()
                - ((GameObject)bomb).getWidth()/2) <= ((GameObject)bomb).getWidth()/2
                && (375 - (getLeftTraySize() * 10)) - ((GameObject)bomb).getY() <= 10
                && ((GameObject)bomb).getY() < 375 - (getLeftTraySize() * 10)
        ){
            bomb.detonate();
            removeAllPlatesOnLeft();
            notifyObserversOnCatchingBomb();
            return true;
        }

        return false;

    }

    @Override
    public boolean catchBombWithRight(Detonator bomb) {
        if(Math.abs(((GameObject)bomb).getX() + ((GameObject)bomb).getWidth() -
                this.getX() - this.getWidth()) <= ((GameObject)bomb).getWidth()/2
                && (375 - (getRightTraySize() * 10)) - ((GameObject)bomb).getY() <= 10
                && ((GameObject)bomb).getY() < 375 - (getRightTraySize() * 10) ){

            bomb.detonate();
            notifyObserversOnCatchingBomb();
            removeAllPlatesOnRight();
            return true;
        }

        return false;

    }

    private void removeAllPlatesOnLeft(){
        while(!leftTray.isEmpty()){
            updateLeftTray();
        }
    }

    private void removeAllPlatesOnRight(){
        while(!rightTray.isEmpty()) {
            updateRightTray();
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        //
    }

    @Override
    public void notifyObserversOnCatchingPlates() {
        for (Observer observer : observers) {
            observer.incrementScore(1);
        }
    }

    @Override
    public void notifyObserversOnCatchingConsecutivePlates() {
        for (Observer observer : observers) {
            observer.incrementScore(10);
        }
    }

    @Override
    public void notifyObserversOnCatchingBomb() {
        for (Observer observer : observers) {
            observer.incrementScore(-10);
        }
    }
}
