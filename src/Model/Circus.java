package Model;

import Model.PlatePool;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Controller.Subject;
import Controller.Observer;

public class Circus implements World, Subject {
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

//    PlatePool platePool;

    private Clown clown;

    private ArrayList<Observer> observers = new ArrayList<>();

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

//        platePool = new PlatePool(this);
    }

    public static void disposeInstance() {
        instance = null;
    }

    public Clown getClown() {
        return clown;
    }

    @Override
    public int getSpeed () {
        return 10;
    }

    @Override
    public boolean refresh() {
        notifyObservers();
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
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.refresh();
        }
    }
}
