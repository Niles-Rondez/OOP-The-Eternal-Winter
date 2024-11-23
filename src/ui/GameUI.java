package ui;

import main.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
    private JFrame window;
    private JPanel titlePanel, ButtonPanel, imagePanel, mainTextPanel, choicePanel;
    private JLabel titleLabel;
    private JButton newGameButton, loadGameButton, settingsButton, exitButton, choice1, choice2, choice3, choice4;
    private JTextArea mainTextArea;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    private Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font textFont = new Font("Times New Roman", Font.BOLD, 25);
    private Image mainMenuBackground;

    private Game game;

    public GameUI(Game game) {
        this.game = game;
        mainMenuBackground = new ImageIcon("./resources/MainMenuBackground.png").getImage();
        createWindow();
    }

    private void createWindow() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
    }

    public void showTitleScreen() {
        // Background panel with image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mainMenuBackground, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Title Panel
        titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BorderLayout());

        titleLabel = new JLabel("THE ETERNAL WINTER");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0)); // Top, Left, Bottom, Right
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Button Panel
        ButtonPanel = new JPanel();
        ButtonPanel.setOpaque(false);
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.Y_AXIS));

        // Create buttons
        Dimension buttonSize = new Dimension(250, 50);
        newGameButton = createButton("New Game", buttonSize);
        loadGameButton = createButton("Load Game", buttonSize);
        settingsButton = createButton("Settings", buttonSize);
        exitButton = createButton("Exit", buttonSize);

        ButtonPanel.add(Box.createVerticalGlue());
        ButtonPanel.add(newGameButton);
        ButtonPanel.add(Box.createVerticalStrut(10));
        ButtonPanel.add(loadGameButton);
        ButtonPanel.add(Box.createVerticalStrut(10));
        ButtonPanel.add(settingsButton);
        ButtonPanel.add(Box.createVerticalStrut(10));
        ButtonPanel.add(exitButton);
        ButtonPanel.add(Box.createVerticalGlue());

        // Add title and button panels to background
        backgroundPanel.add(titlePanel, BorderLayout.NORTH);
        backgroundPanel.add(ButtonPanel, BorderLayout.CENTER);

        // Set background as the content pane
        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    private JButton createButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setFocusable(false);
        button.setFont(new Font("Serif", Font.BOLD, 25));
        button.setForeground(new Color(0x123456));
        button.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> handleButtonAction(text));
        return button;
    }

    private void handleButtonAction(String action) {
        switch (action) {
            case "New Game":
                createGameScreen();
                break;
            case "Load Game":
//                game.loadGame(); // Replace with actual logic
                break;
            case "Settings":
//                game.openSettings(); // Replace with actual logic
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    public void createGameScreen() {
        // Clear title screen components
        window.getContentPane().removeAll();

        // Game screen components
        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(0x123456));
        imagePanel.setLayout(new BorderLayout());

        ImageIcon imagePanelIcon = new ImageIcon("./resources/ImagePanelBackground.jpg");
        Image imagePanelImage = imagePanelIcon.getImage();
        JLabel imagePanelLabel = new JLabel(new ImageIcon(imagePanelImage));
        imagePanel.add(imagePanelLabel, BorderLayout.CENTER);

        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.WHITE);
        mainTextPanel.setLayout(new BorderLayout());
        mainTextArea = new JTextArea();
        mainTextArea.setEditable(false);
        mainTextArea.setForeground(new Color(0x123456));
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(mainTextArea);
        mainTextPanel.add(scrollPane, BorderLayout.CENTER);

        choicePanel = new JPanel();
        choicePanel.setBackground(new Color(0x123456));
        choicePanel.setLayout(new GridLayout(5, 1));
        createChoiceButtons();

        window.add(imagePanel, BorderLayout.NORTH);
        window.add(mainTextPanel, BorderLayout.CENTER);
        window.add(choicePanel, BorderLayout.SOUTH);
        window.revalidate();
        window.repaint();
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

        skipButton.setVisible(false);
    }

    public void updateMainText(String text) {
        mainTextArea.setText(text);
    }

    public void updateMainTextWithTypingEffect(String text) {
        mainTextArea.setText(""); // Clear previous text
        new Thread(() -> {
            StringBuilder displayedText = new StringBuilder();
            for (char character : text.toCharArray()) {
                displayedText.append(character);
                mainTextArea.setText(displayedText.toString());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
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
