package Controller;

public interface Observer {
    boolean refresh();
    void incrementScore(int inc);
    void leftTrayUpdate();
    void rightTrayUpdate();
}
