import Controller.Controller;
import Model.Circus;
import Controller.HardDifficulty;
import Controller.EasyDifficulty;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menuBar.add(menu);
        Controller controller = new Controller(Circus.getGameInstance(),new HardDifficulty()) ;

        GameController gc =  GameEngine.start("Circus of Plates", controller.getCircus(), menuBar, Color.BLACK);

        pauseMenuItem.addActionListener(e -> {
            // Handle the "Pause" menu item action
            gc.pause();
        });

        resumeMenuItem.addActionListener(e -> {
            // Handle the "Resume" menu item action
            gc.resume();
        });

        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Resume" menu item action
                Circus.disposeInstance();
                gc.changeWorld(Circus.getGameInstance());
            }
        });
        }
}