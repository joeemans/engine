package model;

import controller.DynamicImageLoader;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import controller.Subject;
import controller.Observer;
import view.MainWindow;

public class Circus implements World, Subject {
    private static volatile Circus instance;
    private final static int SCREEN_WIDTH = 900;
    private final static int SCREEN_HEIGHT = 700;
    private static final int STARTING_PLATES = 5;
    private static final int PLATES_INCREMENTED = 2;
    private final long startingTime;
    private long countingTime;
    private int lives = 5;
    private boolean timeout = false;
    private static final long GAME_TIME_SECONDS = 1000;
    private int sensitivity = 7;
    private long timePaused;
    private static int clownWidth;

    //List is an Interface which is implemented by ArrayList
    private final List<GameObject> constantObjects = new LinkedList<>();
    private final List<GameObject> movableObjects = new LinkedList<>();
    private final List<GameObject> controllableObjects = new LinkedList<>();

    private UserControlled clown;
    long score = 0;

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
        countingTime = getStartingTime();

        constantObjects.add(new ImageObject(0, 0, Type.BACKGROUND));
        for (int i=0 ; i < lives ; i++) {
            constantObjects.add(new ImageObject(i * 40, 0, Type.HEART));
        }

        DynamicFileLinker.shapesLoader();
        ShapeLoader userControlledObjectFactory = DynamicFileLinker.getRandomUserControlledShapeFactory();
        clown = (UserControlled) userControlledObjectFactory.loadShape(getScreenWidth() / 2 - 110, SCREEN_HEIGHT / 2 + 70);
        controllableObjects.add(clown);
        clownWidth = clown.getWidth();
    }

    public static void disposeInstance() {
        instance = null;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public UserControlled getClown() {
        return clown;
    }

    @Override
    public int getSpeed () {
        return 10;
    }

    @Override
    public boolean refresh() {
        if(!timeout && lives > 0) {
            countingTime = System.currentTimeMillis();
            if (MainWindow.isWasPaused()) {
                long timeElapsedSincePause = getCountingTime() - MainWindow.getTimePaused();
                timePaused = getTimePaused() + timeElapsedSincePause;
                MainWindow.setWasPaused(false);
            }
            if (getCountingTime() > getStartingTime() + timePaused + 120 * GAME_TIME_SECONDS) {
                timeout = true;
            }
            if (!timeout && lives > 0) {
                notifyObservers();
                return true;
            }
        }
        return false;
    }

    public boolean intersect(GameObject o1, GameObject o2){
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth())
                && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    public void decrementLives(){
        BufferedImage[] images = constantObjects.get(lives).getSpriteImages();
        controller.DynamicImageLoader imageLoader = new DynamicImageLoader();
        images[0] = imageLoader.loadImage("resources/" + Type.EMPTY_HEART.getName() + ".png");
        lives--;
    }

    public int getLives(){
        return lives;
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
        return this.getScreenWidth();
    }

    @Override
    public int getHeight () {
        return this.SCREEN_HEIGHT;
    }


    @Override
    public String getStatus () {
        long seconds = (120 * GAME_TIME_SECONDS + getStartingTime() + getTimePaused() - getCountingTime()) / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        String timeRepresented = String.format("%d:%02d", minutes, seconds);
        return "Score: " + score + "  Time remaining: " + timeRepresented;
    }

    public void setScore(long score){
        this.score = score;
    }

    @Override
    public int getControlSpeed () {
        return sensitivity;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.refresh();
        }
    }


    public int getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }

    public long getStartingTime() {
        return startingTime;
    }

    public long getCountingTime() {
        return countingTime;
    }

    public long getTimePaused() {
        return timePaused;
    }

    public static int getClownWidth() {
        return clownWidth;
    }
}
