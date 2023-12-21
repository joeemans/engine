package model;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface Detonator extends GameObject, Faller {
    void detonate();
    long getDetonatingTime();
}
