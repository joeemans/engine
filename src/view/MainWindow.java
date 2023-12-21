package view;

import controller.Controller;
import controller.HardDifficulty;
import eg.edu.alexu.csd.oop.game.GameEngine;
import model.Circus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JPanel panel;
    private JButton newGameButton = new JButton("New Game");
    private JButton exitButton = new JButton("Exit");
    private JButton optionsButton = new JButton("Options");
    public static final Dimension WINDOW_DIMENSION = new Dimension(900, 700);
    private static final Dimension BUTTON_DIMENSION = new Dimension(200,100);


    public MainWindow() {
        setSize(WINDOW_DIMENSION);
        setTitle("Main Menu");
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("GameWindowImage.jpg").getImage());
        ImageIcon imageIcon = new ImageIcon("Main-menu.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setSize(WINDOW_DIMENSION);
        this.add(imageLabel);
      //  newGameButton.setSize(BUTTON_DIMENSION);
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
