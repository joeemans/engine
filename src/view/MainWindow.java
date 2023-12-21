package view;

import controller.Controller;
import controller.HardDifficulty;
import eg.edu.alexu.csd.oop.game.GameEngine;
import model.Circus;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.GameWindow.WINDOW_DIMENSION;

public class MainWindow extends JFrame  {
    private JPanel panel1;
    private JButton newGameButton;
    private JButton exitButton;
    private JButton optionsButton;



    public MainWindow() {
        setContentPane(panel1);
        setSize(WINDOW_DIMENSION);
        setTitle("Main Menu");
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("GameWindowImage.jpg").getImage());
        setVisible(true);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
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

        GameEngine.GameController gc =  GameEngine.start("Circus of Plates", controller.getCircus(), menuBar, Color.BLACK);

        pauseMenuItem.addActionListener(e1 -> {
            // Handle the "Pause" menu item action
            gc.pause();
        });

        resumeMenuItem.addActionListener(e1 -> {
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
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }


    public static void main(String[] args) {
        new MainWindow();
    }


}
