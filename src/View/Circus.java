package View;

import Model.Clown;
import Model.Component;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.util.ArrayList;
import java.util.List;

public class Circus implements World {
    private final int screenWidth;
    private final int screenHeight;

    //List is an Interface which is implemented by ArrayList
    private final List<GameObject> constantObjects = new ArrayList<>();
    private final List<GameObject> movableObjects = new ArrayList<>();
    private final List<GameObject> controllableObjects = new ArrayList<>();

    public Circus(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        movableObjects.add(new Clown(screenWidth / 2, screenHeight / 2));

    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public boolean refresh() {
        return false;
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
