package model;

import controller.Observer;
import controller.Subject;
import eg.edu.alexu.csd.oop.game.GameObject;
import model.shapes.Plate;

import java.util.ArrayList;
import java.util.List;

public interface UserControlled extends GameObject, Subject {

    boolean catchPlate(Faller plate);
    boolean catchBomb(Detonator detonator);


}
