package View;

import Model.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Circus implements World {
    private final int screenWidth;
    private final int screenHeight;
    private static final int STARTING_PLATES = 5;
    private static final int PLATES_INCREMENTED = 2;
    private final long startingTime;
    private long countingTime;

    //List is an Interface which is implemented by ArrayList
    private final List<GameObject> constantObjects = new LinkedList<>();
    private final List<GameObject> movableObjects = new LinkedList<>();
    private final List<GameObject> controllableObjects = new LinkedList<>();

    public Circus(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        startingTime = System.currentTimeMillis();
        countingTime = startingTime;

        constantObjects.add(new ImageObject(0,0, Type.BACKGROUND));
        controllableObjects.add(new Clown(screenWidth / 2 - 110, screenHeight / 2 + 70));

        for ( int i = 0  ; i < STARTING_PLATES ; i++){
            int randomX = (int)(Math.random() * screenWidth - 10);
            int randomY = (int)(Math.random() * screenHeight) / 5;
            int randomType = (int)(Math.random()*3)+1;
            movableObjects.add(new Plate(randomX,randomY, Type.getByValue(randomType)));
        }

    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public boolean refresh() {
        if (System.currentTimeMillis() > countingTime + 1000){
            countingTime = System.currentTimeMillis();
            for ( int i = 0  ; i < PLATES_INCREMENTED ; i++){
                int randomX = (int)(Math.random() * screenWidth);
                int randomY = (int)(Math.random() * screenHeight) / 5;
                int randomType = (int)(Math.random()*3)+1;
                movableObjects.add(new Plate(randomX,randomY, Type.getByValue(randomType)));
            }
        }
        Iterator <GameObject> objectIterator = movableObjects.iterator();
        while (objectIterator.hasNext()){
            GameObject currentObject = objectIterator.next();
            if (currentObject instanceof Faller)
                ((Faller) currentObject).freeFall();
            if (currentObject instanceof PlateCatcher && ((PlateCatcher) currentObject).catchPlate()){
                constantObjects.add(currentObject);
                objectIterator.remove();
            }
            if (currentObject.getY() >= screenHeight){
                objectIterator.remove();
                System.out.println("Object removed");
            }
        }
        return true;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constantObjects;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movableObjects;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controllableObjects;
    }

    @Override
    public int getWidth() {
        return this.screenWidth;
    }

    @Override
    public int getHeight() {
        return this.screenHeight;
    }


    @Override
    public String getStatus() {
        return null;
    }


    @Override
    public int getControlSpeed() {
        return 0;
    }
}
