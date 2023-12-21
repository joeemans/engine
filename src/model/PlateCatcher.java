package model;

import model.shapes.Plate;

public interface PlateCatcher extends UserControlled{

    boolean catchPlate(Faller plate);
    boolean catchPlateWithLeft(Faller plate);
    boolean catchPlateWithRight(Faller plate);

}
