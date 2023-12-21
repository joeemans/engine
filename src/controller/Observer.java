package controller;

public interface Observer {
    void refresh();
    void incrementScore(int inc);
    void leftTrayUpdate();
    void rightTrayUpdate();
}
