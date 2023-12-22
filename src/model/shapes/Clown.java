package model.shapes;

import model.*;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.ArrayList;
import java.util.List;

import controller.Subject;
import controller.Observer;

public class Clown extends ImageObject implements BombCatcher {

    public ArrayList<GameObject> leftTray = new ArrayList<>();
    public ArrayList<GameObject> rightTray = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();
    private boolean platesEmptied;


    public Clown(int x, int y) {
        super(x, y, Type.CLOWN);
    }

    @Override
    public boolean catchPlate(Faller plate) {
        return catchPlateWithLeft(plate) || catchPlateWithRight(plate);
    }

    public boolean catchPlateWithLeft(Faller plate) {

        if(Math.abs(this.getX() + plate.getWidth()/2 - plate.getX() - plate.getWidth()/2) <= plate.getWidth()/2
                && (375 - (getLeftTraySize() * 10)) - plate.getY() <= 10
                && plate.getY() < 375 - (getLeftTraySize() * 10)
        ){

            if(!checkConsecutivePlatesOnLeft(((Plate)plate).getType().getColor())) {
                plate.setX(this.getX());
                plate.setY(this.getY() - 40 - this.getLeftTraySize() * 10);
                ((Plate)plate).setInLeftTray();
                leftTray.add(plate);
                notifyObserversOnCatchingPlates();
            }
            return true;
        }
        return false;

    }

    public boolean catchPlateWithRight(Faller plate) {

        if(Math.abs(plate.getX() + plate.getWidth() - this.getX() - this.getWidth()) <= plate.getWidth()/2
                && (375 - (getRightTraySize() * 10)) - plate.getY() <= 10
                && plate.getY() < 375 - (getRightTraySize() * 10) ) {

            if(! checkConsecutivePlatesOnRight(((Plate)plate).getType().getColor())) {
                plate.setX((this.getX() + this.getWidth() / 2));
                plate.setY(this.getY() - 10 - this.getRightTraySize() * 10);
                ((Plate)plate).setInRightTray();
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
            setPlatesEmptied(true);
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
            setPlatesEmptied(true);
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

    public boolean catchBombWithLeft(Detonator bomb) {
        if(Math.abs(this.getX() + bomb.getWidth()/2 - bomb.getX()
                - bomb.getWidth()/2) <= bomb.getWidth()/2
                && (375 - (getLeftTraySize() * 10)) - bomb.getY() <= 10
                && bomb.getY() < 375 - (getLeftTraySize() * 10)
        ){
            bomb.detonate();
            removeAllPlatesOnLeft();
            notifyObserversOnCatchingBomb();
            return true;
        }

        return false;

    }

    public boolean catchBombWithRight(Detonator bomb) {
        if(Math.abs(bomb.getX() + bomb.getWidth() -
                this.getX() - this.getWidth()) <= bomb.getWidth()/2
                && (375 - (getRightTraySize() * 10)) - bomb.getY() <= 10
                && bomb.getY() < 375 - (getRightTraySize() * 10) ){

            bomb.detonate();
            notifyObserversOnCatchingBomb();
            removeAllPlatesOnRight();
            return true;
        }

        return false;

    }

    public void removeAllPlatesOnLeft(){
        while(!leftTray.isEmpty()){
            updateLeftTray();
        }
    }

    private void removeAllPlatesOnRight(){
        while(!rightTray.isEmpty()) {
            updateRightTray();
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    public void notifyObserversOnCatchingPlates() {
        for (Observer observer : observers) {
            observer.incrementScore(1);
        }
    }

    public void notifyObserversOnCatchingConsecutivePlates() {
        for (Observer observer : observers) {
            observer.incrementScore(10);
        }
    }

    public void notifyObserversOnCatchingBomb() {
        for (Observer observer : observers) {
            observer.incrementScore(-10);
        }
    }

    public boolean isPlatesEmptied() {
        return platesEmptied;
    }

    public void setPlatesEmptied(boolean platesEmptied) {
        this.platesEmptied = platesEmptied;
    }
}
