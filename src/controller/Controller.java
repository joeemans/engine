package controller;


import model.*;
import model.Circus;
import model.shapes.Clown;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.Iterator;
import java.util.List;

public class Controller implements Observer {
    private DifficultyState difficulty;
    private Circus circus;
    private long score = 0;
    private long highScore = 0;
//    private final long startTime;
//    private long countingTime;
    private static final int STARTING_PLATES = 5;
    private static final int PLATES_INCREMENTED = 2;

    private double plateRate;
    private double bombRate;
    private long timeSinceLastPlate;
    private long timeSinceLastBomb;
    private static final int DEFAULT_TIME_BETWEEN_EXPLOSIONS = 3000;

    private boolean harderOverTime;

    private FallingObjectPool fallingObjectPool;

    public Controller(Circus circus) {
        this.circus = circus;
        circus.addObserver(this);
        circus.getClown().addObserver(this);
    //    startTime = System.currentTimeMillis();

        this.timeSinceLastPlate = System.currentTimeMillis();
        this.timeSinceLastBomb = System.currentTimeMillis();

        setDifficulty(new EasyDifficulty());

        this.fallingObjectPool = new FallingObjectPool(circus);

    }

    public Controller(Circus circus, DifficultyState difficulty) {
        this(circus);
        setDifficulty(difficulty);
    }

    public Controller(Circus circus, DifficultyState difficulty, int sensitivity) {
        this(circus);
        setDifficulty(difficulty);
        circus.setSensitivity(sensitivity);
    }

    public void setDifficulty(DifficultyState difficulty) {
        this.difficulty = difficulty;
        this.plateRate = difficulty.getPlateRate();
        this.bombRate = difficulty.getBombRate();
    }

    public void refresh() {
        List<GameObject> movableObjects = getCircus().getMovableObjects();
        List<GameObject> controllableObjects = getCircus().getControlableObjects();
        List<GameObject> constantObjects = getCircus().getConstantObjects();
        UserControlled clown = getCircus().getClown();

        updateDifficulty();
        dropObjects();

            Iterator<GameObject> objectIterator = movableObjects.iterator();
            while (objectIterator.hasNext()) {
                GameObject currentObject = objectIterator.next();

                if (currentObject instanceof Faller){
                    ((Faller) currentObject).setClownWidth(clown.getWidth());
                    ((Faller) currentObject).setScreenWidth(circus.getWidth());
                    ((Faller) currentObject).freeFall();
                }

                if (currentObject instanceof Detonator) {
                    if (clown.catchBomb((Detonator) currentObject)) {
                        ((Detonator) currentObject).detonate();
                        constantObjects.add(currentObject);
                        objectIterator.remove();
                        circus.decrementLives();
                    } else if (circus.intersect(currentObject, clown)) {
                        ((Detonator) currentObject).detonate();
                        constantObjects.add(currentObject);
                        objectIterator.remove();
                        circus.decrementLives();
                    }
                }

                else {
                    Faller plate = (Faller)currentObject;

                    if (clown.catchPlate(plate)) {
                        if (!((Clown)circus.getClown()).platesEmptied) {
                            controllableObjects.add(currentObject);
                        }
                        ((Clown)circus.getClown()).platesEmptied = false;
                        objectIterator.remove();
                    }
                }

                if (currentObject.getY() >= getCircus().getHeight()) {
                    objectIterator.remove();
                    if (currentObject instanceof Detonator) {
                        fallingObjectPool.returnDetonator((Detonator)currentObject);
                    }
                    else{
                        fallingObjectPool.returnFallingObject((Faller)currentObject);
                    }
                }
            }
            objectIterator = constantObjects.iterator();
            while (objectIterator.hasNext()) {
                GameObject currentObject = objectIterator.next();

                if (currentObject instanceof Detonator && System.currentTimeMillis() >
                        ((Detonator) currentObject).getDetonatingTime() + DEFAULT_TIME_BETWEEN_EXPLOSIONS) {
                    objectIterator.remove();
                }
            }
    }

    private void dropObjects() {
        List<GameObject> movableObjects = getCircus().getMovableObjects();

        long currentTime = System.currentTimeMillis();
        long timeElapsedSinceLastPlate = currentTime - getTimeSinceLastPlate();
        long timeElapsedSinceLastBomb = currentTime - getTimeSinceLastBomb();

        int platesToDrop = (int) (plateRate * timeElapsedSinceLastPlate / 1000);
        int bombsToDrop = (int) (bombRate * timeElapsedSinceLastBomb / 1000);

        long newTimeSinceDrop = System.currentTimeMillis();

        if (platesToDrop > 0) {
            timeSinceLastPlate = newTimeSinceDrop;
        }
        if (bombsToDrop > 0) {
            timeSinceLastBomb = newTimeSinceDrop;
        }

        for (int i = 0; i < Math.max(platesToDrop, bombsToDrop); i++) {
            if (i < platesToDrop) {
                /*int randomX = (int) (Math.random() * getCircus().getWidth());
                int randomY = (int) (Math.random() * getCircus().getHeight()) / 5;
                ShapeLoader randomFallingObjectFactory = DynamicFileLinker.getRandomFallingObjectFactory();
                movableObjects.add(randomFallingObjectFactory.loadShape(randomX, randomY));*/

                //Faller plate = platePool.borrowPlate();
                //movableObjects.add(plate);

                Faller fallingObject = fallingObjectPool.borrowFallingObject();

            }
            if (i < bombsToDrop) {
                /*int randomX = (int) (Math.random() * getCircus().getWidth());
                int randomY = (int) (Math.random() * getCircus().getHeight()) / 5;
                ShapeLoader randomDetonatingObjectFactory = DynamicFileLinker.getRandomDetonatingObjectFactory();
                movableObjects.add(randomDetonatingObjectFactory.loadShape(randomX, randomY));*/

                Detonator detonator = fallingObjectPool.borrowDetonator();

            }
        }
    }

    private void updateDifficulty() {
        if (!harderOverTime) return;

        //TODO: UPDATE DIFFICULTY AFTER CERTAIN SCORE
    }

    @Override
    public void incrementScore(int inc) {
        score+=inc;
        circus.setScore(score);
    }

    @Override
    public void leftTrayUpdate() {
        Faller plate = (Faller) ((Clown)circus.getClown()).leftTray.remove(((Clown)circus.getClown()).getLeftTraySize()-1);
        circus.getControlableObjects().remove(plate);
    }

    @Override
    public void rightTrayUpdate() {
        Faller plate = (Faller) ((Clown)circus.getClown()).rightTray.remove(((Clown)circus.getClown()).getRightTraySize()-1);
        circus.getControlableObjects().remove(plate);
    }


    public Circus getCircus() {
        return circus;
    }

    private long getTimeSinceLastPlate() {
        return this.timeSinceLastPlate;
    }

    private long getTimeSinceLastBomb() {
        return this.timeSinceLastBomb;
    }

    private double getBombRate() {
        return this.bombRate;
    }

    private double getPlateRate() {
        return this.plateRate;
    }
}


