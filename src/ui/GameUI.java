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
        mainTextPanel.setBounds(100, 100, 600, 300);
        mainTextArea = new JTextArea();
        mainTextArea.setEditable(false);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setPreferredSize(new Dimension(580, 250));
        mainTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(mainTextArea);
        scrollPane.setPreferredSize(new Dimension(580, 250));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        mainTextPanel.add(scrollPane);

        choicePanel = new JPanel();
        choicePanel.setBackground(Color.black);
        choicePanel.setBounds(250, 400, 300, 150);
        choicePanel.setLayout(new GridLayout(5, 1));
        createChoiceButtons();

        window.add(mainTextPanel);
        window.add(choicePanel);
        window.setVisible(true);
    }

    private void createChoiceButtons() {
        choice1 = new JButton();
        choice2 = new JButton();
        choice3 = new JButton();
        choice4 = new JButton();
        JButton skipButton = new JButton("Skip");

        JButton[] choices = {choice1, choice2, choice3, choice4, skipButton};
        for (JButton button : choices) {
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            button.setFont(buttonFont);
            button.setFocusPainted(false);
            button.addActionListener(new ChoiceButtonHandler());
            choicePanel.add(button);
        }

        // Set the skip button to be hidden initially
        skipButton.setVisible(false);
    }

    public void updateMainText(String text) {
        mainTextArea.setText(text);
    }

    private volatile boolean isTyping = false;

    public void updateMainTextWithTypingEffect(String text) {
        mainTextArea.setText(""); // Clear previous text
        isTyping = true; // Set typing to true

        // Hide other choice buttons and show the skip button
        for (int i = 0; i < choicePanel.getComponentCount(); i++) {
            Component comp = choicePanel.getComponent(i);
            if (comp instanceof JButton && !((JButton) comp).getText().equals("Skip")) {
                comp.setEnabled(false); // Make all buttons except skip unclickable
            }
        }

        JButton skipButton = (JButton) choicePanel.getComponent(4);
        skipButton.setVisible(true); // Show skip button
        skipButton.addActionListener(e -> skipTyping(text)); // Allow skipping the typing

        new Thread(() -> {
            StringBuilder displayedText = new StringBuilder();
            for (char character : text.toCharArray()) {
                if (!isTyping) break; // Exit if typing is skipped
                displayedText.append(character);
                mainTextArea.setText(displayedText.toString());
                try {
                    Thread.sleep(50); // Adjust the delay as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Handle interruption properly
                }
            }

            // Typing is done
            isTyping = false;
            mainTextArea.setText(text); // Optionally set full text here
            skipButton.setVisible(false); // Hide skip button

            // Show the other choice buttons again
            for (int i = 0; i < choicePanel.getComponentCount(); i++) {
                Component comp = choicePanel.getComponent(i);
                if (comp instanceof JButton && !((JButton) comp).getText().equals("Skip")) { // Cast to JButton
                    comp.setEnabled(true); // Enable all buttons except skip
                }
            }
        }).start();
    }

    private void skipTyping(String fullText) {
        isTyping = false; // Stop typing
        mainTextArea.setText(fullText); // Show the full text immediately
        JButton skipButton = (JButton) choicePanel.getComponent(4); // Cast to JButton
        skipButton.setVisible(false); // Hide skip button

        // Show the other choice buttons again
        for (int i = 0; i < choicePanel.getComponentCount(); i++) {
            Component comp = choicePanel.getComponent(i);
            if (comp instanceof JButton && !((JButton) comp).getText().equals("Skip")) { // Cast to JButton
                comp.setEnabled(true); // Enable all buttons except skip
            }
        }
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
            JButton source = (JButton) e.getSource();
            game.handleChoice(source.getText());
        }
    }
}
