package view;

import controller.Controller;
import controller.DifficultyState;
import controller.EasyDifficulty;
import eg.edu.alexu.csd.oop.game.GameEngine;
import model.Circus;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {
    private JPanel panel;
    private JButton exitButton = new JButton("Exit");
    private JButton optionsButton = new JButton("Options");
    public static final Dimension WINDOW_DIMENSION = new Dimension(900, 700);
    private static final Dimension BUTTON_DIMENSION = new Dimension(200,100);
    private int sensitivity = 7;
    private DifficultyState difficultyState = new EasyDifficulty();
    private static boolean wasPaused;
    private static long timePaused;


    public MainWindow() {
        setSize(WINDOW_DIMENSION);
        setTitle("Main Menu");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("GameWindowImage.jpg").getImage());
        ImageIcon imageIcon = new ImageIcon("Main-menu.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setSize(WINDOW_DIMENSION);
        this.add(imageLabel);
        Font font = new Font("Calibri", Font.BOLD, 18);
        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(font);
        optionsButton.setFont(font);
        exitButton.setFont(font);
        newGameButton.setBackground(Color.GRAY);
        newGameButton.setBounds(325,150,250,50);
        imageLabel.add(newGameButton);
        optionsButton.setBackground(Color.GRAY);
        optionsButton.setBounds(325,300,250,50);
        imageLabel.add(optionsButton);
        exitButton.setBackground(Color.GRAY);
        exitButton.setBounds(325,450,250,50);
        imageLabel.add(exitButton);
        setVisible(true);

        newGameButton.addActionListener(e -> {
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

            Controller controller;
            if (difficultyState == null) {
                controller = new Controller(Circus.getGameInstance(), sensitivity);
            } else {
                controller = new Controller(Circus.getGameInstance(), difficultyState, sensitivity);
            }
            GameEngine.GameController gc =  GameEngine.start("Circus of Plates", controller.getCircus(), menuBar, Color.BLACK);

            pauseMenuItem.addActionListener(e1 -> {
                setWasPaused(true);
                setTimePaused(System.currentTimeMillis());
                gc.pause();
            });

            resumeMenuItem.addActionListener(e1 -> {
                gc.resume();
            });

            newMenuItem.addActionListener(e12 -> {
                // Handle the "Resume" menu item action
                Circus.disposeInstance();
                gc.changeWorld(Circus.getGameInstance());
            });


        });
        optionsButton.addActionListener(e -> {
            new OptionsMenu(MainWindow.this);
            setVisible(false);
        });
        exitButton.addActionListener(e -> dispose());
    }


    public static void main(String[] args) {
        new MainWindow();
    }

    public static boolean isWasPaused() {
        return wasPaused;
    }

    public static void setWasPaused(boolean wasPaused) {
        MainWindow.wasPaused = wasPaused;
    }

    public static long getTimePaused() {
        return timePaused;
    }

    public static void setTimePaused(long timePaused) {
        MainWindow.timePaused = timePaused;
    }

    public int getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }

    public DifficultyState getDifficultyState() {
        return difficultyState;
    }

    public void setDifficultyState(DifficultyState difficultyState) {
        this.difficultyState = difficultyState;
    }
}
