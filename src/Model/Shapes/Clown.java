package Model.Shapes;

import Model.*;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import Controller.Subject;
import Controller.Observer;

public class Clown extends ImageObject implements PlateCatcher, Subject {
    //public Stack <GameObject> leftTray = new Stack<>();
   // public Stack <GameObject> rightTray = new Stack<>();

    public ArrayList<GameObject> leftTray = new ArrayList<>();
    public ArrayList<GameObject> rightTray = new ArrayList<>();

    private List<Observer> observers = new ArrayList<>();
    public boolean platesEmptied;


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

    public boolean checkConsecutivePlatesOnRight(Color color) {
        /*if(!this.rightTray.isEmpty()) {
            if (((Plate) this.rightTray.get(getRightTraySize()-1)).getType().getColor().equals(color)) {
                consecutivePlatesOnRight++;
                if (consecutivePlatesOnRight == 3) {
                    notifyObservers();
                    removeConsecutivePlatesOnRight();
                    platesEmptied = true;
                    return true;
                }
            }
            else consecutivePlatesOnRight = 1;
        }*/

        if(this.rightTray.size() < 2){
            return false;
        }

        for (int i = 0; i < 2; i++) {
            if (!((Plate) this.rightTray.get(getRightTraySize() - i - 1)).getType().getColor().equals(color)) {
                return false;
            }
        }
            notifyObservers();
            removeConsecutivePlatesOnRight();
            platesEmptied = true;
            return true;
    }

    public boolean checkConsecutivePlatesOnLeft(Color color){
        /*if(!this.leftTray.isEmpty()) {
            if (((Plate) this.leftTray.get(getLeftTraySize()-1)).getType().getColor().equals(color)) {
                consecutivePlatesOnLeft++;
                if (consecutivePlatesOnLeft == 3) {
                    notifyObservers();
                    removeConsecutivePlatesOnLeft();
                    platesEmptied = true;
                    return true;
                }
            }
            else consecutivePlatesOnLeft = 1;
        }
        return false;*/

        if(this.leftTray.size() < 2){
            return false;
        }

        for (int i = 0; i < 2; i++) {
            if (!((Plate) this.leftTray.get(getLeftTraySize() - i - 1)).getType().getColor().equals(color)) {
                return false;
            }
        }
            notifyObservers();
            removeConsecutivePlatesOnLeft();
            platesEmptied = true;
            return true;

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


    public boolean catchPlateWithLeft(Plate plate) {

        if(Math.abs(this.getX() + plate.getWidth()/2 - plate.getX() - plate.getWidth()/2) <= plate.getWidth()/2
                && (375 - (getLeftTraySize() * 10)) - plate.getY() <= 10
                && plate.getY() < 375 - (getLeftTraySize() * 10)
        ){

            if(!checkConsecutivePlatesOnLeft(plate.getType().getColor())) {
//            checkConsecutivePlatesOnLeft(plate.getType().getColor());
            plate.setX(getX());
            plate.setY(getY() - 20 - getLeftTraySize() * 10);
            plate.setInLeftTray();
            plate.setX(this.getX());
            plate.setY(this.getY() - 30 - this.getLeftTraySize() * 10);
            plate.setInLeftTray();
            leftTray.add(plate);
//            if(consecutivePlatesOnLeft == 3){
//                removeConsecutivePlatesOnLeft();
//            }
            }
            return true;
        }
        return false;

    }

    public boolean catchPlateWithRight(Plate plate) {

        if(Math.abs(plate.getX() + plate.getWidth() - this.getX() - this.getWidth()) <= plate.getWidth()/2
                && (375 - (getRightTraySize() * 10)) - plate.getY() <= 10
                && plate.getY() < 375 - (getRightTraySize() * 10) ) {

            System.out.println("CAUGHT WITH RIGHT!");

            if(! checkConsecutivePlatesOnRight(((plate).getType().getColor()))) {
//            checkConsecutivePlatesOnRight(((plate).getType().getColor()));
            plate.setX(getX() + getWidth() / 2);
            plate.setY(getY() - 10 - getRightTraySize() * 10);
            plate.setInRightTray();

            plate.setX((this.getX() + this.getWidth() / 2));
            plate.setY(this.getY() - 10 - this.getRightTraySize() * 10);
            plate.setInRightTray();
            rightTray.add(plate);
//            if ( consecutivePlatesOnRight == 3 ){
//                removeConsecutivePlatesOnRight();
//            }
            }
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
}
