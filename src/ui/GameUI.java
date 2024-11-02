package ui;

import main.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
    private JFrame window;
    private JPanel titlePanel, ButtonPanel, titleScreenDesignPanel, imagePanel, mainTextPanel, choicePanel;
    private JLabel titleLabel, titleScreenDesignLabel, imagePanelLabel;
    private JButton startButton, optionsButton, exitButton, choice1, choice2, choice3, choice4;
    private JTextArea mainTextArea;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    private Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font textFont = new Font("Times New Roman", Font.BOLD, 25);

    private Game game;

    public GameUI(Game game) {
        this.game = game;
        createWindow();
    }

    private void createWindow() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(new Color(0x123456));
        window.setLayout(new BorderLayout());
    }

    public void showTitleScreen() {
        titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0x123456));
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(100, 200));
        titleLabel = new JLabel("THE ETERNAL WINTER");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);

        ImageIcon logoIcon = new ImageIcon("./resources/logo.png");
        Image logoImage = logoIcon.getImage();
        Image scaledLogo = logoImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        titleLabel.setIcon(new ImageIcon(scaledLogo));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);

        ButtonPanel = new JPanel();
        ButtonPanel.setBackground(new Color(0x123456));
        ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 75));;
        ButtonPanel.setPreferredSize(new Dimension(50, 200));

        startButton = new JButton("Start");
        startButton.setBackground(Color.WHITE);
        startButton.setPreferredSize(new Dimension(250, 50));
        startButton.setFocusable(false);
        startButton.setFont(new Font("Serif", Font.BOLD, 25));
        startButton.setForeground(new Color(0x123456));
        startButton.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
        startButton.addActionListener(e -> game.startGame());

        titleScreenDesignPanel = new JPanel();
        titleScreenDesignPanel.setBackground(new Color(0x123456));
        titleScreenDesignPanel.setLayout(new BorderLayout());
        titleScreenDesignPanel.setPreferredSize(new Dimension(100, 200));
        titleScreenDesignLabel = new JLabel();
        ImageIcon titleScreenDesignImage = new ImageIcon("./resources/TitleScreenBackground.png");
        Image titleScreenDesign = titleScreenDesignImage.getImage();
        Image scaledTitleScreenDesign = titleScreenDesign.getScaledInstance(800, 200, Image.SCALE_SMOOTH);
        titleScreenDesignLabel.setIcon(new ImageIcon(scaledTitleScreenDesign));
        titleScreenDesignLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleScreenDesignLabel.setVerticalAlignment(SwingConstants.CENTER);


        titlePanel.add(titleLabel, BorderLayout.CENTER);
        ButtonPanel.add(startButton);
        titleScreenDesignPanel.add(titleScreenDesignLabel, BorderLayout.CENTER);

        window.add(titlePanel, BorderLayout.NORTH);
        window.add(ButtonPanel, BorderLayout.CENTER);
        window.add(titleScreenDesignPanel, BorderLayout.SOUTH);
        window.setVisible(true);
    }

    public void createGameScreen() {
        titlePanel.setVisible(false);
        ButtonPanel.setVisible(false);
        titleScreenDesignLabel.setVisible(false);

        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(0x123456));
        imagePanel.setPreferredSize(new Dimension(100, 300));
        imagePanel.setLayout(new BorderLayout());

        ImageIcon imagePanelIcon = new ImageIcon("./resources/ImagePanelBackground.jpg");
        Image imagePanelImage = imagePanelIcon.getImage();
        Image scaledimagePanelImage = imagePanelImage.getScaledInstance(800, 280, Image.SCALE_SMOOTH);

        imagePanelLabel = new JLabel();
        imagePanelLabel.setIcon(new ImageIcon(scaledimagePanelImage));
        imagePanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanelLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagePanel.add(imagePanelLabel, BorderLayout.CENTER);

        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.WHITE);
        mainTextPanel.setLayout(new BorderLayout());
        mainTextPanel.setVisible(true);
        mainTextArea = new JTextArea();
        mainTextArea.setEditable(false);
        mainTextArea.setForeground(new Color(0x123456));
        mainTextArea.setBackground(Color.WHITE);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(mainTextArea);

        mainTextPanel.add(scrollPane, BorderLayout.CENTER);

        choicePanel = new JPanel();
        choicePanel.setBackground(new Color(0x123456));
        choicePanel.setPreferredSize(new Dimension(100, 200));
        choicePanel.setLayout(new GridLayout(5, 1));
        createChoiceButtons();

        window.add(imagePanel, BorderLayout.NORTH);
        window.add(mainTextPanel, BorderLayout.CENTER);
        window.add(choicePanel, BorderLayout.SOUTH);
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
            button.setBackground(Color.white);
            button.setForeground(new Color(0x123456));
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
                if (comp instanceof JButton && !((JButton) comp).getText().equals("Skip")) {
                    comp.setEnabled(true);
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
