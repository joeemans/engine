package View;

import javax.swing.*;
import java.awt.*;

final public class GameWindow extends JFrame {
    private static final Dimension WINDOW_DIMENSION = new Dimension(900, 700);

    public GameWindow() {
        setSize(WINDOW_DIMENSION);
        setTitle("Clown Game");
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("GameWindowImage.jpg").getImage());

        //now with our JFrame all set, time to create a JMenu bar to add functionalities to our game
        JMenuBar menuBar = new JMenuBar();
        fillMenuBar(menuBar);
        setJMenuBar(menuBar);
        //creating a giant JPanel on which everything appears
        JPanel board = createBoardPanel();
        add(board);
        setVisible(true);
    }

    private JPanel createBoardPanel() {
        JPanel gamePanel = new JPanel();
        gamePanel.setPreferredSize(WINDOW_DIMENSION);
        return gamePanel;
    }

    private void fillMenuBar(JMenuBar menuBar) {
        JMenu fileMenu = new JMenu("File");
        JMenu gameMenu = new JMenu("Game");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> {
            //Exit application
            System.exit(0);
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
    }
}