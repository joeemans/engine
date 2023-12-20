package Controller;

public interface Observer {
    boolean refresh();
    void updateScore();
    void leftTrayUpdate();
    void rightTrayUpdate();
}
