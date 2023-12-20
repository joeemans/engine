package Model;

import Model.Shapes.Plate;
import eg.edu.alexu.csd.oop.game.GameObject;

public interface PlateCatcher {
    boolean catchPlate(Plate plate);
    boolean catchPlateWithLeft(Plate plate);
    boolean catchPlateWithRight(Plate plate);

}
