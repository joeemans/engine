package Model.Shapes;

import Model.*;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import Controller.Subject;
import Controller.Observer;

public class Clown extends ImageObject implements PlateCatcher, Subject {
    public Stack <GameObject> leftTray = new Stack<>();
    public Stack <GameObject> rightTray = new Stack<>();
    private List<Observer> observers = new ArrayList<>();
    private int consecutivePlatesOnLeft = 1;
    private int consecutivePlatesOnRight = 1;
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

    public boolean
    checkConsecutivePlatesOnRight(Color color){
        if(!this.rightTray.isEmpty()) {
            if (((Plate) this.rightTray.peek()).getType().getColor().equals(color)) {
                consecutivePlatesOnRight++;
                if (consecutivePlatesOnRight == 3) {
                    System.out.println("remove right");
                    notifyObservers();
                    removeConsecutivePlatesOnRight();
                    platesEmptied = true;
                    return true;
                }
            }
            else consecutivePlatesOnRight = 1;
        }
        return false;
    }
    public boolean checkConsecutivePlatesOnLeft(Color color){
        if(!this.leftTray.isEmpty()) {
            if (((Plate) this.leftTray.peek()).getType().getColor().equals(color)) {
                consecutivePlatesOnLeft++;
                System.out.println(consecutivePlatesOnLeft);
                if (consecutivePlatesOnLeft == 3) {
                    System.out.println("remove right");
                    notifyObservers();
                    removeConsecutivePlatesOnLeft();
                    platesEmptied = true;
                    return true;
                }
            }
            else consecutivePlatesOnLeft = 1;
            System.out.println(consecutivePlatesOnLeft);
        }
        return false;
    }
    public void removeConsecutivePlatesOnRight(){
        for(int i=1; i<=2; i++){
            updateRightTray();
            System.out.println("Relayed");
//            Plate plate = (Plate)this.rightTray.pop();
            //plate.setIsVisible(false);
        }
        consecutivePlatesOnRight = 1;
    }

    public void removeConsecutivePlatesOnLeft(){
        for(int i=1; i<=2; i++){
            updateLeftTray();
            System.out.println("Relayed");
//            Plate plate = (Plate)this.leftTray.pop();
            //plate.setIsVisible(false);
        }
        consecutivePlatesOnLeft = 1;
    }


    public boolean catchPlateWithLeft(Plate plate) {

        if(Math.abs(this.getX() + plate.getWidth()/2 - plate.getX() - plate.getWidth()/2) <= plate.getWidth()/2
                && (375 - (getLeftTraySize() * 10)) - plate.getY() <= 10
                && plate.getY() < 375 - (getLeftTraySize() * 10)
        ){
            System.out.println("CAUGHT WITH LEFT!");
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
        System.out.println(11);
        for (Observer observer : observers) {
            System.out.println("IM HEREE");
            observer.leftTrayUpdate();
        }
    }

    public void updateRightTray() {
        for (Observer observer : observers) {
            System.out.println("IM HEREE");
            observer.rightTrayUpdate();
        }
    }
}
