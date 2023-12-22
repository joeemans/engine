package view;

import controller.EasyDifficulty;
import controller.HardDifficulty;
import controller.MediumDifficulty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsMenu extends JFrame {

    private JComboBox<String> difficultyComboBox;
    private JSlider sensitivitySlider;

    public OptionsMenu(MainWindow mainMenu) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setTitle("Options Window");
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(null);

        Font font = new Font("Calibri", Font.PLAIN, 18);

        JLabel difficultyLabel = new JLabel("Difficulty:");
        difficultyLabel.setFont(font);
        difficultyLabel.setBounds(50, 50, 100, 50);

        String[] difficultyOptions = {"Easy", "Medium", "Hard", "Dynamic"};
        difficultyComboBox = new JComboBox<>(difficultyOptions);
        difficultyComboBox.setBounds(200, 50, 150, 50);

        JLabel sensitivityLabel = new JLabel("Sensitivity:");
        sensitivityLabel.setFont(font);
        sensitivityLabel.setBounds(50, 200, 100, 50);

        sensitivitySlider = new JSlider(1, 14);
        sensitivitySlider.setBounds(200, 200, 150, 50);

        JButton mainMenuButton = new JButton("Main Menu");
        Font boldFont = new Font("Calibri", Font.BOLD, 12);
        mainMenuButton.setBackground(Color.lightGray);
        mainMenuButton.setFont(boldFont);
        mainMenuButton.setBounds(50, 300, 100, 100);

        JButton restoreDefaultButton = new JButton("Restore Default values");
        restoreDefaultButton.setFont(boldFont);
        restoreDefaultButton.setBackground(Color.lightGray);
        restoreDefaultButton.setBounds(200, 300, 200, 100);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.lightGray);
        confirmButton.setFont(boldFont);
        confirmButton.setBounds(450, 300, 100, 100);

        // Add action listeners to buttons
        mainMenuButton.addActionListener(e -> {
            dispose();
            mainMenu.setVisible(true);
        });

        restoreDefaultButton.addActionListener(e -> {
            mainMenu.setDifficultyState(new EasyDifficulty());
            mainMenu.setSensitivity(7);
            sensitivitySlider.setValue(7);
            difficultyComboBox.setSelectedIndex(0);
        });

        confirmButton.addActionListener(e -> {
            String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
            int sensitivityValue = sensitivitySlider.getValue();
            mainMenu.setSensitivity(sensitivityValue);

            switch (selectedDifficulty){
                case ("Easy"): {
                    mainMenu.setDifficultyState(new EasyDifficulty());
                    break;
                }
                case ("Medium"):{
                    mainMenu.setDifficultyState(new MediumDifficulty());
                    break;
                }
                case ("Hard"):{
                    mainMenu.setDifficultyState(new HardDifficulty());
                    break;
                }
                case ("Dynamic"):{
                    mainMenu.setDifficultyState(null);
                    break;
                }
                default: break;
            }
            mainMenu.setVisible(true);
            dispose();
        });

        add(difficultyLabel);
        add(difficultyComboBox);
        add(sensitivityLabel);
        add(sensitivitySlider);
        add(mainMenuButton);
        add(restoreDefaultButton);
        add(confirmButton);

        setVisible(true);
    }
}
