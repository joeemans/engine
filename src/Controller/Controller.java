package Controller;


import Model.*;
import View.Circus;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.Iterator;
import java.util.List;



public class Controller implements Observer {
    private DifficultyState difficulty;
    private Circus circus;
    private long score = 0;
    private long highScore = 0;
    private int lives = 3;
    private final long startingTime;
    private long countingTime;
    private static final int STARTING_PLATES = 5;
    private static final int PLATES_INCREMENTED = 2;



    public Controller(Circus circus) {
        this.circus = circus;

        this.difficulty = new EasyDifficulty();

        startingTime = System.currentTimeMillis();
        countingTime = startingTime;
    }

    public Controller(Circus circus, DifficultyState difficulty) {
        this.circus = circus;
        this.difficulty = difficulty;

        startingTime = System.currentTimeMillis();
        countingTime = startingTime;
    }

    public void setDifficulty(DifficultyState difficulty) {
        this.difficulty = difficulty;
    }

    public boolean refresh() {
        List<GameObject> movableObjects = circus.getMovableObjects();
        List<GameObject> controllableObjects = circus.getControlableObjects();
        Clown clown = circus.getClown();

        if (System.currentTimeMillis() > countingTime + 1000){
            countingTime = System.currentTimeMillis();
            for ( int i = 0  ; i < PLATES_INCREMENTED ; i++){
                int randomX = (int)(Math.random() * circus.getWidth());
                int randomY = (int)(Math.random() * circus.getHeight()) / 5;
                int randomType = (int)(Math.random()*3)+1;
                movableObjects.add(new Plate(randomX,randomY, Type.getByValue(randomType)));
            }
        }
        Iterator<GameObject> objectIterator = movableObjects.iterator();
        while (objectIterator.hasNext()){
            GameObject currentObject = objectIterator.next();
            if (currentObject instanceof Faller)
                ((Faller) currentObject).freeFall();

            if (clown.catchPlateWithLeft(currentObject)){
                System.out.println("CAUGHT WITH LEFT!");
                controllableObjects.add(currentObject);
                objectIterator.remove();
                currentObject.setX(clown.getX());
                currentObject.setY(clown.getY() - clown.getLeftTraySize()*10);
            }

            else if (clown.catchPlateWithRight(currentObject)){
                System.out.println("CAUGHT WITH RIGHT!");
                controllableObjects.add(currentObject);
                objectIterator.remove();
                currentObject.setX(clown.getX() + clown.getWidth()/2);
                currentObject.setY(clown.getY() - clown.getRightTraySize()*10);
            }

            if (currentObject.getY() >= circus.getHeight()){
                objectIterator.remove();
                System.out.println("Object removed");
            }
        }
        return true;
    }

    public void updateScore() {

    }


}


