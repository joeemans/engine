package controller;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    void notifyObserversOnCatchingPlates();
    void notifyObserversOnCatchingConsecutivePlates();
    void notifyObserversOnCatchingBomb();
}
