package Model;

import Model.Shapes.Bomb;
import Model.Shapes.Plate;

public interface BombCatcher {

    boolean catchBomb(Bomb bomb);
    boolean catchBombWithLeft(Bomb bomb);
    boolean catchBombWithRight(Bomb bomb);

}
