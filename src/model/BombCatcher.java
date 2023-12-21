package model;

public interface BombCatcher extends UserControlled{

    boolean catchBomb(Detonator detonator);
    boolean catchBombWithLeft(Detonator detonator);
    boolean catchBombWithRight(Detonator detonator);

}
