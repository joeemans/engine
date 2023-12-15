package View;

import Model.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Circus implements World,Observer {
    private static volatile Circus instance;
    private final static int screenWidth = 900;
    private final static int screenHeight = 700;
    private static final int STARTING_PLATES = 5;
    private static final int PLATES_INCREMENTED = 2;
    private final long startingTime;
    private long countingTime;

    //List is an Interface which is implemented by ArrayList
    private final List<GameObject> constantObjects = new LinkedList<>();
    private final List<GameObject> movableObjects = new LinkedList<>();
    private final List<GameObject> controllableObjects = new LinkedList<>();

    Clown clown;

    public static Circus getGameInstance() {
        if (instance == null) {
            synchronized (Circus.class) {
                if (instance == null) {
                    instance = new Circus();
                }
            }
        }
        return instance;
    }

    private Circus() {
            startingTime = System.currentTimeMillis();
            countingTime = startingTime;

            constantObjects.add(new ImageObject(0, 0, Type.BACKGROUND));
            clown = new Clown(screenWidth / 2 - 110, screenHeight / 2 + 70);
            controllableObjects.add(clown);

            for (int i = 0; i < STARTING_PLATES; i++) {
                int randomX = (int) (Math.random() * screenWidth - 10);
                int randomY = (int) (Math.random() * screenHeight) / 5;
                int randomType = (int) (Math.random() * 3) + 1;
                movableObjects.add(new Plate(randomX, randomY, Type.getByValue(randomType)));
            }

        }

    public static void disposeInstance() {
        instance = null;
    }

    @Override
        public int getSpeed () {
            return 10;
        }

        @Override
        public boolean refresh () {
            if (System.currentTimeMillis() > countingTime + 1000) {
                countingTime = System.currentTimeMillis();
                for (int i = 0; i < PLATES_INCREMENTED; i++) {
                    int randomX = (int) (Math.random() * screenWidth);
                    int randomY = (int) (Math.random() * screenHeight) / 5;
                    int randomType = (int) (Math.random() * 3) + 1;
                    movableObjects.add(new Plate(randomX, randomY, Type.getByValue(randomType)));
                }
            }
            Iterator<GameObject> objectIterator = movableObjects.iterator();
            while (objectIterator.hasNext()) {
                GameObject currentObject = objectIterator.next();
                if (currentObject instanceof Faller)
                    ((Faller) currentObject).freeFall();

                if (clown.catchPlateWithLeft(currentObject)) {
                    System.out.println("CAUGHT WITH LEFT!");
                    controllableObjects.add(currentObject);
                    objectIterator.remove();
                    currentObject.setX(clown.getX());
                    currentObject.setY(clown.getY() - clown.getLeftTraySize() * 10);
                } else if (clown.catchPlateWithRight(currentObject)) {
                    System.out.println("CAUGHT WITH RIGHT!");
                    controllableObjects.add(currentObject);
                    objectIterator.remove();
                    currentObject.setX(clown.getX() + clown.getWidth() / 2);
                    currentObject.setY(clown.getY() - clown.getRightTraySize() * 10);
                }

                if (currentObject.getY() >= screenHeight) {
                    objectIterator.remove();
                    System.out.println("Object removed");
                }
            }
            return true;
        }

        private boolean intersect (GameObject o1, GameObject o2){
            return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
        }

        @Override
        public List<GameObject> getConstantObjects () {
            return constantObjects;
        }

        @Override
        public List<GameObject> getMovableObjects () {
            return movableObjects;
        }

        @Override
        public List<GameObject> getControlableObjects () {
            return controllableObjects;
        }

        @Override
        public int getWidth () {
            return this.screenWidth;
        }

        @Override
        public int getHeight () {
            return this.screenHeight;
        }


        @Override
        public String getStatus () {
            return null;
        }


        @Override
        public int getControlSpeed () {
            return 5;
        }

    @Override
    public void updateScore() {

    }
}
