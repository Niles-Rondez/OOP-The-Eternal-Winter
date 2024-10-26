package ui;

import main.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
    private JFrame window;
    private JPanel titlePanel, startButtonPanel, mainTextPanel, choicePanel;
    private JLabel titleLabel;
    private JButton startButton, choice1, choice2, choice3, choice4;
    private JTextArea mainTextArea;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    private Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font textFont = new Font("Times New Roman", Font.PLAIN, 20);

    private Game game;

    public GameUI(Game game) {
        this.game = game;
        createWindow();
    }

    private void createWindow() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setMinimumSize(new Dimension(800, 600));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
    }

    public void showTitleScreen() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.black);
        titlePanel.setBounds(50, 100, 700, 150);
        titleLabel = new JLabel("THE ETERNAL WINTER");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBackground(Color.BLACK);
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(buttonFont);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> game.startGame());

        titlePanel.add(titleLabel);
        startButtonPanel.add(startButton);

        window.add(titlePanel);
        window.add(startButtonPanel);
        window.setVisible(true);
    }

    public void createGameScreen() {
        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextArea = new JTextArea();
        mainTextArea.setEditable(false);
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        choicePanel = new JPanel();
        choicePanel.setBackground(Color.black);
        choicePanel.setBounds(250, 350, 300, 150);
        choicePanel.setLayout(new GridLayout(4, 1));
        createChoiceButtons();

        window.add(mainTextPanel);
        window.add(choicePanel);
    }

    private void createChoiceButtons() {
        choice1 = new JButton("CHOICE 1");
        choice2 = new JButton("CHOICE 2");
        choice3 = new JButton("CHOICE 3");
        choice4 = new JButton("CHOICE 4");

        JButton[] choices = {choice1, choice2, choice3, choice4};
        for (JButton choice : choices) {
            choice.setBackground(Color.BLACK);
            choice.setForeground(Color.WHITE);
            choice.setFont(buttonFont);
            choice.setFocusPainted(false);
            choice.addActionListener(new ChoiceButtonHandler());
            choice.setActionCommand(choice.toString());
            choicePanel.add(choice);
        }
    }

    public void updateMainText(String text) {
        mainTextArea.setText(text);
    }

    public void updateChoices(String[] options) {
        JButton[] choices = {choice1, choice2, choice3, choice4};
        for (int i = 0; i < choices.length; i++) {
            if (i < options.length) {
                choices[i].setText(options[i]);
                choices[i].setVisible(true);
            } else {
                choices[i].setVisible(false);
            }
        }
    }

    private class ChoiceButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            game.handleChoice(e.getActionCommand());
        }
    }
}
