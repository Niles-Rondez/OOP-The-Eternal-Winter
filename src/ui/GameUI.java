package ui;

import main.Game;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
    private JFrame window;
    private JPanel titlePanel, backgroundPanel, buttonPanel, headerPanel, headerButtons, imagePanel, mainTextPanel, choicePanel;
    private JLabel titleLabel, areaLabel, pauseLabel;
    private JButton newGameButton, loadGameButton, settingsButton, exitButton, adventureButton, noticeboardButton, merchantButton, clinicButton,
            chapelButton, tavernButton, pauseButton, mapButton, bagButton, characterButton, choice1, choice2, choice3, choice4;
    private JTextArea mainTextArea;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    private Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font textFont = new Font("Times New Roman", Font.BOLD, 25);
    private Image mainMenuBackground, loadGameBackground, mainAreaBackground;

    private Game game;

    public GameUI(Game game) {
        this.game = game;
        mainMenuBackground = new ImageIcon("./resources/MainMenuBackground.png").getImage();
        loadGameBackground = new ImageIcon("./resources/LoadGameBackground.png").getImage();
        mainAreaBackground = new ImageIcon("./resources/TownBackground.png").getImage();
        createWindow();
    }

    private void createWindow() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
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

    public void showTitleScreen() {
        // Background panel with image
        backgroundPanel = new JPanel() {
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
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create buttons
        Dimension buttonSize = new Dimension(250, 50);
        newGameButton = createButton("New Game", buttonSize);
        loadGameButton = createButton("Load Game", buttonSize);
        settingsButton = createButton("Settings", buttonSize);
        exitButton = createButton("Exit", buttonSize);

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(loadGameButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(settingsButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalGlue());

        // Add title and button panels to background
        backgroundPanel.add(titlePanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set background as the content pane
        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    public void loadGameScreen() {
        // Background panel with image
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(loadGameBackground, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Title Panel
        titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BorderLayout());

        titleLabel = new JLabel("Load Game File");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0)); // Top, Left, Bottom, Right
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create buttons
        Dimension buttonSize = new Dimension(250, 50);
        newGameButton = createButton("Act I", buttonSize);
        loadGameButton = createButton("Act II", buttonSize);
        settingsButton = createButton("Empty", buttonSize);
        exitButton = createButton("Back", buttonSize);

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(loadGameButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(settingsButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalGlue());

        // Add title and button panels to background
        backgroundPanel.add(titlePanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set background as the content pane
        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    public void mainArea() {
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mainAreaBackground, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());

        // Load the pause icon
        ImageIcon pauseIcon = new ImageIcon("./resources/pause_icon.png"); // Replace with your image path
        Image scaledPauseIcon = pauseIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize if needed
        pauseIcon = new ImageIcon(scaledPauseIcon);

        // Create the pause button with the icon
        pauseButton = new JButton();
        pauseButton.setIcon(pauseIcon); // Set the icon
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setBorderPainted(false); // Optional: Remove button border
        pauseButton.setContentAreaFilled(false); // Optional: Make button background transparent
        pauseButton.setFocusPainted(false); // Optional: Remove focus border
        pauseButton.addActionListener(e -> System.out.println("Paused!")); // Add your pause logic here

        areaLabel = new JLabel("Area");
        areaLabel.setForeground(Color.WHITE);
        areaLabel.setFont(titleFont);
        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        areaLabel.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0)); // Top, Left, Bottom, Right

        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0)); // Top, Left, Bottom, Right
        headerButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        ImageIcon mapIcon = new ImageIcon("./resources/map.png");
        Image scaledMap = mapIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        mapIcon = new ImageIcon(scaledMap);
        mapButton = new JButton();
        mapButton.setIcon(mapIcon); // Set the icon
        mapButton.setBackground(Color.BLACK);
        mapButton.setBorderPainted(false); // Optional: Remove button border
        mapButton.setContentAreaFilled(false); // Optional: Make button background transparent
        mapButton.setFocusPainted(false); // Optional: Remove focus border
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0)); // Top, Left, Bottom, Right

        ImageIcon bagIcon = new ImageIcon("./resources/Bag.png");
        Image scaledBag = bagIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        bagIcon = new ImageIcon(scaledBag);
        bagButton = new JButton();
        bagButton.setIcon(bagIcon); // Set the icon
        bagButton.setBackground(Color.BLACK);
        bagButton.setBorderPainted(false); // Optional: Remove button border
        bagButton.setContentAreaFilled(false); // Optional: Make button background transparent
        bagButton.setFocusPainted(false); // Optional: Remove focus border

        ImageIcon characterIcon = new ImageIcon("./resources/Character.png");
        Image scaledCharacter = characterIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        characterIcon = new ImageIcon(scaledCharacter);
        characterButton = new JButton();
        characterButton.setIcon(characterIcon); // Set the icon
        characterButton.setBackground(Color.BLACK);
        characterButton.setBorderPainted(false); // Optional: Remove button border
        characterButton.setContentAreaFilled(false); // Optional: Make button background transparent
        characterButton.setFocusPainted(false); // Optional: Remove focus border
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // Top, Left, Bottom, Right

        headerButtons.add(mapButton);
        headerButtons.add(bagButton);
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
        headerPanel.add(areaLabel, BorderLayout.CENTER);
        headerPanel.add(headerButtons, BorderLayout.EAST);

        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 3));

        Dimension buttonSize = new Dimension(250, 50);
        adventureButton = createButton("Adventure", buttonSize);
        noticeboardButton = createButton("Notice Board", buttonSize);
        merchantButton = createButton("Merchant", buttonSize);
        clinicButton = createButton("Clinic", buttonSize);
        chapelButton = createButton("Chapel", buttonSize);
        tavernButton = createButton("Tavern", buttonSize);

        buttonPanel.add(adventureButton);
        buttonPanel.add(noticeboardButton);
        buttonPanel.add(merchantButton);
        buttonPanel.add(clinicButton);
        buttonPanel.add(chapelButton);
        buttonPanel.add(tavernButton);

        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        window.setContentPane(backgroundPanel);
        window.revalidate();
    }


    private void handleButtonAction(String action) {
        switch (action) {
            case "New Game":
                mainArea();
                break;
            case "Load Game":
                loadGameScreen();
                break;
            case "Settings":
//                game.openSettings(); // Replace with actual logic
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Back":
                showTitleScreen();
                break;
        }
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
