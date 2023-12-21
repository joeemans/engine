package model;

import model.shapes.Plate;

public interface UserControlled {
    boolean catchPlate(Plate plate);
    boolean catchPlateWithLeft(Plate plate);
    boolean catchPlateWithRight(Plate plate);
    boolean catchBomb(Detonator detonator);
    boolean catchBombWithLeft(Detonator detonator);
    boolean catchBombWithRight(Detonator detonator);

}
