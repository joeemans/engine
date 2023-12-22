package model;

public interface BombCatcher extends PlateCatcher {

    boolean catchBomb(Detonator detonator);
    boolean catchBombWithLeft(Detonator detonator);
    boolean catchBombWithRight(Detonator detonator);
}
