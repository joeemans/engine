package Controller;


import Model.*;
import Model.Circus;
import Model.Shapes.Bomb;
import Model.Shapes.Clown;
import Model.Shapes.Plate;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.Iterator;
import java.util.List;



public class Controller implements Observer {
    private DifficultyState difficulty;
    private Circus circus;
    private long score = 0;
    private long highScore = 0;
    private int lives = 3;
    private long startTime;
    private long countingTime;
    private static final int STARTING_PLATES = 5;
    private static final int PLATES_INCREMENTED = 2;

    private double plateRate;
    private double bombRate;
    private long timeSinceLastPlate;
    private long timeSinceLastBomb;

    private boolean harderOverTime = true;

    public Controller(Circus circus) {
        this.circus = circus;
        circus.addObserver(this);

        this.timeSinceLastPlate = System.currentTimeMillis();
        this.timeSinceLastBomb = System.currentTimeMillis();

        setDifficulty(new EasyDifficulty());

    }

    public Controller(Circus circus, DifficultyState difficulty) {
        this(circus);
        setDifficulty(difficulty);
        this.harderOverTime = false;
        circus.getMovableObjects().add(new Plate(circus.getClown().getX(), 100, Type.BLUE_PLATE));
    }

    public void setDifficulty(DifficultyState difficulty) {
        this.difficulty = difficulty;
        this.plateRate = difficulty.getPlateRate();
        this.bombRate = difficulty.getBombRate();
    }

    public boolean refresh() {
        List<GameObject> movableObjects = getCircus().getMovableObjects();
        List<GameObject> controllableObjects = getCircus().getControlableObjects();
        List<GameObject> constantObjects = getCircus().getConstantObjects();
        Clown clown = getCircus().getClown();

        updateDifficulty();
        dropObjects();

        Iterator<GameObject> objectIterator = movableObjects.iterator();
        while (objectIterator.hasNext()){
            GameObject currentObject = objectIterator.next();

            if (currentObject instanceof Plate) {
                ((Plate)currentObject).setClownWidth(clown.getWidth());
                ((Plate)currentObject).setScreenWidth(circus.getWidth());
            }

            if (currentObject instanceof Faller)
                ((Faller) currentObject).freeFall();


//            if (currentObject instanceof Bomb) {
//                Bomb bomb = (Bomb) currentObject;
//
//                if () { //Clown Catches Bomb
//                   Explosion explosion = new Explosion(bomb.getX(), bomb.getY(), Type.EXPLOSION);
//                   constantObjects.add(explosion);
//                   objectIterator.remove();
//
//                }
//
//            }

            if (currentObject instanceof Plate) {
                Plate plate = (Plate) currentObject;

                if (clown.catchPlate(plate)) {
                    controllableObjects.add(currentObject);
                    objectIterator.remove();
                }
            }

            if (currentObject.getY() >= getCircus().getHeight()){
                objectIterator.remove();
                System.out.println("Object removed");
            }
        }
        return true;
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
                int randomX = (int) (Math.random() * getCircus().getWidth());
                int randomY = (int) (Math.random() * getCircus().getHeight()) / 5;
                int randomType = (int) (Math.random() * 3) + 1;
                movableObjects.add(new Plate(randomX, randomY, Type.getByValue(randomType)));
            }
            if (i < bombsToDrop) {
                int randomX = (int) (Math.random() * getCircus().getWidth());
                int randomY = (int) (Math.random() * getCircus().getHeight()) / 5;
                movableObjects.add(new Bomb(randomX, randomY, Type.BOMB));
            }
        }
    }

    private void updateDifficulty() {
        if (!harderOverTime) return;

        //TODO: UPDATE DIFFICULTY AFTER CERTAIN SCORE
    }

    public void updateScore() {

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


