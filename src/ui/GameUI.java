package ui;

import main.Game;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
    private JFrame window;
    private JPanel titlePanel, startButtonPanel, backgroundPanel, buttonPanel, headerPanel, headerButtons, interactionPanel, imagePanel, mainTextPanel, choicePanel;
    private JLabel titleLabel, areaLabel, textLabel, pauseLabel;
    private JButton newGameButton, loadGameButton, settingsButton, exitButton, adventureButton, noticeboardButton, merchantButton, clinicButton,
            chapelButton, tavernButton, pauseButton, mapButton, bagButton, characterButton, buyButton, sleepButton, chestButton, talkToInKeeperButton, eatButton, questsButton, sellButton, townButton, choice1, choice2, choice3, choice4, choice5;
    private JTextArea mainTextArea;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    private Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font textFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Image mainMenuBackground, loadGameBackground, mainAreaBackground, clinicBackground, chapelBackground, merchantBackground, tavernBackground;
    private boolean isTyping = false;
    private String playerClass;
    private volatile String activeScreen = "";


    private Game game;

    public GameUI(Game game) {
        this.game = game;
        mainMenuBackground = new ImageIcon("./resources/MainMenuBackground.png").getImage();
        loadGameBackground = new ImageIcon("./resources/LoadGameBackground.png").getImage();
        mainAreaBackground = new ImageIcon("./resources/TownBackground.png").getImage();
        clinicBackground = new ImageIcon("./resources/Clinic.png").getImage();
        chapelBackground = new ImageIcon("./resources/Chapel.png").getImage();
        merchantBackground = new ImageIcon("./resources/Merchant.png").getImage();
        tavernBackground = new ImageIcon("./resources/Tavern.png").getImage();
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

    private JButton createChoiceButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK); // Black background
        button.setForeground(Color.WHITE); // White text
        button.setPreferredSize(size);
        button.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Adjust font size and style
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Thin white border
        button.setContentAreaFilled(true); // Ensure the button has a solid background
        button.setHorizontalAlignment(SwingConstants.CENTER); // Center align text
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
        activeScreen = "town";
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

//        areaLabel = new JLabel("Town");
//        areaLabel.setForeground(Color.WHITE);
//        areaLabel.setFont(titleFont);
//        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        areaLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0)); // Top, Left, Bottom, Right

        areaLabel = new JLabel("Town", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();

                // Enable anti-aliasing for smoother text rendering
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the outline
                g2d.setFont(getFont());
                g2d.setColor(Color.BLACK); // Outline color
                String text = getText();
                FontMetrics metrics = g2d.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth(text)) / 2;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (dx != 0 || dy != 0) {
                            g2d.drawString(text, x + dx, y + dy);
                        }
                    }
                }

                // Draw the main text
                g2d.setColor(Color.WHITE); // Main text color
                g2d.drawString(text, x, y);

                g2d.dispose();
            }
        };
        areaLabel.setFont(titleFont); // Apply your font
        areaLabel.setOpaque(false); // Transparent background
        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        areaLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0)); // Top, Left, Bottom, Right


        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
//        headerButtons.setLayout(new GridLayout(1,1));

        ImageIcon mapIcon = new ImageIcon("./resources/map.png");
        Image scaledMap = mapIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        mapIcon = new ImageIcon(scaledMap);
        mapButton = new JButton();
        mapButton.setIcon(mapIcon); // Set the icon
        mapButton.setBackground(Color.BLACK);
        mapButton.setBorderPainted(false); // Optional: Remove button border
        mapButton.setContentAreaFilled(false); // Optional: Make button background transparent
        mapButton.setFocusPainted(false); // Optional: Remove focus border
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Top, Left, Bottom, Right

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
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); // Top, Left, Bottom, Right

        headerButtons.add(mapButton);
        headerButtons.add(bagButton);
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
        headerPanel.add(areaLabel, BorderLayout.CENTER);
        headerPanel.add(headerButtons, BorderLayout.EAST);

        interactionPanel = new JPanel();
        interactionPanel.setOpaque(false);
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Add margins around the panel
        interactionPanel.setPreferredSize(new Dimension(window.getWidth(), 200)); // Set a fixed height for the interaction panel


        // Text Label (Top Part of Interaction Panel)
        textLabel = new JLabel("Wandering the town's cold roads.", SwingConstants.CENTER);
        textLabel.setOpaque(true); // Make the background visible
        textLabel.setBackground(Color.BLACK); // Set background to black
        textLabel.setForeground(Color.WHITE); // Set text color to white for contrast
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Smaller font size
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the label
        interactionPanel.add(textLabel, BorderLayout.NORTH);

// Typing effect for the existing text
        new Thread(() -> {
            String textToType = "Wandering the town's cold roads."; // Original text
            StringBuilder displayedText = new StringBuilder();

            try {
                for (char c : textToType.toCharArray()) {
                    // Stop if the active screen changes
                    if (!"town".equals(activeScreen)) {
                        return;
                    }

                    displayedText.append(c);
                    SwingUtilities.invokeLater(() -> textLabel.setText(displayedText.toString()));
                    Thread.sleep(50); // Delay for typing effect (adjust speed if needed)
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();


// Button Panel (Bottom Part of Interaction Panel)
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 3, 5, 5)); // Add spacing between buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the button panel

// Adjust button size
        Dimension choicebuttonSize = new Dimension(150, 30); // Make buttons smaller to match the image

        adventureButton = createChoiceButton("Adventure", choicebuttonSize);
        noticeboardButton = createChoiceButton("Notice Board", choicebuttonSize);
        merchantButton = createChoiceButton("Merchant", choicebuttonSize);
        clinicButton = createChoiceButton("Clinic", choicebuttonSize);
        chapelButton = createChoiceButton("Chapel", choicebuttonSize);
        tavernButton = createChoiceButton("Tavern", choicebuttonSize);

        buttonPanel.add(adventureButton);
        buttonPanel.add(noticeboardButton);
        buttonPanel.add(merchantButton);
        buttonPanel.add(clinicButton);
        buttonPanel.add(chapelButton);
        buttonPanel.add(tavernButton);

        interactionPanel.add(buttonPanel, BorderLayout.CENTER);

        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(interactionPanel, BorderLayout.SOUTH);

        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    public void clinicScreen() {
        activeScreen = "clinic";
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(clinicBackground, 0, 0, getWidth(), getHeight(), this);
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

//        areaLabel = new JLabel("Town");
//        areaLabel.setForeground(Color.WHITE);
//        areaLabel.setFont(titleFont);
//        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        areaLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0)); // Top, Left, Bottom, Right

        areaLabel = new JLabel("Clinic", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();

                // Enable anti-aliasing for smoother text rendering
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the outline
                g2d.setFont(getFont());
                g2d.setColor(Color.BLACK); // Outline color
                String text = getText();
                FontMetrics metrics = g2d.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth(text)) / 2;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (dx != 0 || dy != 0) {
                            g2d.drawString(text, x + dx, y + dy);
                        }
                    }
                }

                // Draw the main text
                g2d.setColor(Color.WHITE); // Main text color
                g2d.drawString(text, x, y);

                g2d.dispose();
            }
        };
        areaLabel.setFont(titleFont); // Apply your font
        areaLabel.setOpaque(false); // Transparent background
        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        areaLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0)); // Top, Left, Bottom, Right


        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
//        headerButtons.setLayout(new GridLayout(1,1));

        ImageIcon mapIcon = new ImageIcon("./resources/map.png");
        Image scaledMap = mapIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        mapIcon = new ImageIcon(scaledMap);
        mapButton = new JButton();
        mapButton.setIcon(mapIcon); // Set the icon
        mapButton.setBackground(Color.BLACK);
        mapButton.setBorderPainted(false); // Optional: Remove button border
        mapButton.setContentAreaFilled(false); // Optional: Make button background transparent
        mapButton.setFocusPainted(false); // Optional: Remove focus border
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Top, Left, Bottom, Right

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
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); // Top, Left, Bottom, Right

        headerButtons.add(mapButton);
        headerButtons.add(bagButton);
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
        headerPanel.add(areaLabel, BorderLayout.CENTER);
        headerPanel.add(headerButtons, BorderLayout.EAST);

        interactionPanel = new JPanel();
        interactionPanel.setOpaque(false);
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Add margins around the panel
        interactionPanel.setPreferredSize(new Dimension(window.getWidth(), 200)); // Set a fixed height for the interaction panel

        // Text Label (Top Part of Interaction Panel)
        textLabel = new JLabel("", SwingConstants.CENTER); // Start with an empty label
        textLabel.setOpaque(true); // Make the background visible
        textLabel.setBackground(Color.BLACK); // Set background to black
        textLabel.setForeground(Color.WHITE); // Set text color to white for contrast
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Smaller font size
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the label
        interactionPanel.add(textLabel, BorderLayout.NORTH);

// Array of NPC dialogue lines
        // Array of clinic's dialogue lines
        String[] npcLines = {
                "Welcome to the clinic, weary traveler.",
                "Let me tend to your wounds and ease your pain.",
                "Health is the greatest treasure, after all."
        };

// Typing effect for clinic's dialogue with activeScreen check
        new Thread(() -> {
            try {
                for (String line : npcLines) {
                    // Stop if the active screen changes
                    if (!"clinic".equals(activeScreen)) {
                        return;
                    }

                    StringBuilder displayedText = new StringBuilder();
                    for (char c : line.toCharArray()) {
                        // Stop if the active screen changes during typing
                        if (!"clinic".equals(activeScreen)) {
                            return;
                        }

                        displayedText.append(c);
                        SwingUtilities.invokeLater(() -> textLabel.setText(displayedText.toString()));
                        Thread.sleep(50); // Typing effect delay (adjust speed if needed)
                    }

                    // Pause between lines
                    if (!"clinic".equals(activeScreen)) {
                        return; // Stop if the active screen changes during the pause
                    }
                    Thread.sleep(2000); // Pause between lines
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();



// Button Panel (Bottom Part of Interaction Panel)
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 3, 5, 5)); // Add spacing between buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the button panel

// Adjust button size
        Dimension choicebuttonSize = new Dimension(150, 30); // Make buttons smaller to match the image

        buyButton = createChoiceButton("Buy", choicebuttonSize);
        sellButton = createChoiceButton("Sell", choicebuttonSize);
        townButton = createChoiceButton("Town", choicebuttonSize);

        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(townButton);

        interactionPanel.add(buttonPanel);

        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(interactionPanel, BorderLayout.SOUTH);

        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    public void chapelScreen() {
        activeScreen = "chapel";
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(chapelBackground, 0, 0, getWidth(), getHeight(), this);
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

//        areaLabel = new JLabel("Town");
//        areaLabel.setForeground(Color.WHITE);
//        areaLabel.setFont(titleFont);
//        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        areaLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0)); // Top, Left, Bottom, Right

        areaLabel = new JLabel("Chapel", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();

                // Enable anti-aliasing for smoother text rendering
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the outline
                g2d.setFont(getFont());
                g2d.setColor(Color.BLACK); // Outline color
                String text = getText();
                FontMetrics metrics = g2d.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth(text)) / 2;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (dx != 0 || dy != 0) {
                            g2d.drawString(text, x + dx, y + dy);
                        }
                    }
                }

                // Draw the main text
                g2d.setColor(Color.WHITE); // Main text color
                g2d.drawString(text, x, y);

                g2d.dispose();
            }
        };
        areaLabel.setFont(titleFont); // Apply your font
        areaLabel.setOpaque(false); // Transparent background
        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        areaLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0)); // Top, Left, Bottom, Right


        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
//        headerButtons.setLayout(new GridLayout(1,1));

        ImageIcon mapIcon = new ImageIcon("./resources/map.png");
        Image scaledMap = mapIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        mapIcon = new ImageIcon(scaledMap);
        mapButton = new JButton();
        mapButton.setIcon(mapIcon); // Set the icon
        mapButton.setBackground(Color.BLACK);
        mapButton.setBorderPainted(false); // Optional: Remove button border
        mapButton.setContentAreaFilled(false); // Optional: Make button background transparent
        mapButton.setFocusPainted(false); // Optional: Remove focus border
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Top, Left, Bottom, Right

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
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); // Top, Left, Bottom, Right

        headerButtons.add(mapButton);
        headerButtons.add(bagButton);
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
        headerPanel.add(areaLabel, BorderLayout.CENTER);
        headerPanel.add(headerButtons, BorderLayout.EAST);

        interactionPanel = new JPanel();
        interactionPanel.setOpaque(false);
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Add margins around the panel
        interactionPanel.setPreferredSize(new Dimension(window.getWidth(), 200)); // Set a fixed height for the interaction panel

// Text Label (Top Part of Interaction Panel)
        // Text Label (Top Part of Interaction Panel)
        textLabel = new JLabel("", SwingConstants.CENTER); // Start with an empty label
        textLabel.setOpaque(true); // Make the background visible
        textLabel.setBackground(Color.BLACK); // Set background to black
        textLabel.setForeground(Color.WHITE); // Set text color to white for contrast
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Smaller font size
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the label
        interactionPanel.add(textLabel, BorderLayout.NORTH);

        // Priest's dialogue with typing effect
        new Thread(() -> {
            String[] npcLines = {
                    "May the Lord be with you.",
                    "These walls have stood for centuries.",
                    "Pray, and your faith will be rewarded."
            };

            try {
                for (String line : npcLines) {
                    if (!"chapel".equals(activeScreen)) {
                        return; // Stop if the active screen changes
                    }
                    StringBuilder displayedText = new StringBuilder();
                    for (char c : line.toCharArray()) {
                        if (!"chapel".equals(activeScreen)) {
                            return; // Stop if the active screen changes
                        }
                        displayedText.append(c);
                        SwingUtilities.invokeLater(() -> textLabel.setText(displayedText.toString()));
                        Thread.sleep(35); // Typing effect delay (slightly faster)
                    }
                    Thread.sleep(1500); // Shorter pause between lines
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();


// Button Panel (Bottom Part of Interaction Panel)
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 3, 5, 5)); // Add spacing between buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the button panel

// Adjust button size
        Dimension choicebuttonSize = new Dimension(150, 30); // Make buttons smaller to match the image

        buyButton = createChoiceButton("Buy", choicebuttonSize);
        sellButton = createChoiceButton("Sell", choicebuttonSize);
        townButton = createChoiceButton("Town", choicebuttonSize);

        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(townButton);

        interactionPanel.add(buttonPanel);

        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(interactionPanel, BorderLayout.SOUTH);

        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    public void merchantScreen() {
        activeScreen = "merchant";
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(merchantBackground, 0, 0, getWidth(), getHeight(), this);
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

//        areaLabel = new JLabel("Town");
//        areaLabel.setForeground(Color.WHITE);
//        areaLabel.setFont(titleFont);
//        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        areaLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0)); // Top, Left, Bottom, Right

        areaLabel = new JLabel("Merchant", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();

                // Enable anti-aliasing for smoother text rendering
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the outline
                g2d.setFont(getFont());
                g2d.setColor(Color.BLACK); // Outline color
                String text = getText();
                FontMetrics metrics = g2d.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth(text)) / 2;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (dx != 0 || dy != 0) {
                            g2d.drawString(text, x + dx, y + dy);
                        }
                    }
                }

                // Draw the main text
                g2d.setColor(Color.WHITE); // Main text color
                g2d.drawString(text, x, y);

                g2d.dispose();
            }
        };
        areaLabel.setFont(titleFont); // Apply your font
        areaLabel.setOpaque(false); // Transparent background
        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        areaLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0)); // Top, Left, Bottom, Right


        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
//        headerButtons.setLayout(new GridLayout(1,1));

        ImageIcon mapIcon = new ImageIcon("./resources/map.png");
        Image scaledMap = mapIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        mapIcon = new ImageIcon(scaledMap);
        mapButton = new JButton();
        mapButton.setIcon(mapIcon); // Set the icon
        mapButton.setBackground(Color.BLACK);
        mapButton.setBorderPainted(false); // Optional: Remove button border
        mapButton.setContentAreaFilled(false); // Optional: Make button background transparent
        mapButton.setFocusPainted(false); // Optional: Remove focus border
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Top, Left, Bottom, Right

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
        characterButton.setIcon(characterIcon); // Set the icon1
        characterButton.setBackground(Color.BLACK);
        characterButton.setBorderPainted(false); // Optional: Remove button border
        characterButton.setContentAreaFilled(false); // Optional: Make button background transparent
        characterButton.setFocusPainted(false); // Optional: Remove focus border
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); // Top, Left, Bottom, Right

        headerButtons.add(mapButton);
        headerButtons.add(bagButton);
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
        headerPanel.add(areaLabel, BorderLayout.CENTER);
        headerPanel.add(headerButtons, BorderLayout.EAST);

        interactionPanel = new JPanel();
        interactionPanel.setOpaque(false);
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Add margins around the panel
        interactionPanel.setPreferredSize(new Dimension(window.getWidth(), 200)); // Set a fixed height for the interaction panel

// Text Label (Top Part of Interaction Panel)
        textLabel = new JLabel("", SwingConstants.CENTER); // Start with an empty label
        textLabel.setOpaque(true); // Make the background visible
        textLabel.setBackground(Color.BLACK); // Set background to black
        textLabel.setForeground(Color.WHITE); // Set text color to white for contrast
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Smaller font size
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the label
        interactionPanel.add(textLabel, BorderLayout.NORTH);

// Array of merchant's dialogue lines
        // Array of merchant's dialogue lines
        String[] npcLines = {
                "Welcome, traveler. Care to see my wares?",
                "I offer treasures both common and rare.",
                "Take your time, and let me know what catches your eye."
        };

// Typing effect for merchant's dialogue with activeScreen check
        new Thread(() -> {
            try {
                for (String line : npcLines) {
                    // Stop if the active screen changes
                    if (!"merchant".equals(activeScreen)) {
                        return;
                    }

                    StringBuilder displayedText = new StringBuilder();
                    for (char c : line.toCharArray()) {
                        // Stop if the active screen changes during typing
                        if (!"merchant".equals(activeScreen)) {
                            return;
                        }

                        displayedText.append(c);
                        SwingUtilities.invokeLater(() -> textLabel.setText(displayedText.toString()));
                        Thread.sleep(50); // Typing effect delay (adjust speed if needed)
                    }

                    // Pause between lines
                    if (!"merchant".equals(activeScreen)) {
                        return; // Stop if the active screen changes during the pause
                    }
                    Thread.sleep(2000); // Pause between lines
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();


// Button Panel (Bottom Part of Interaction Panel)
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 3, 5, 5)); // Add spacing between buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the button panel

// Adjust button size
        Dimension choicebuttonSize = new Dimension(150, 30); // Make buttons smaller to match the image

        buyButton = createChoiceButton("Buy", choicebuttonSize);
        sellButton = createChoiceButton("Sell", choicebuttonSize);
        townButton = createChoiceButton("Town", choicebuttonSize);

        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(townButton);

        interactionPanel.add(buttonPanel);

        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(interactionPanel, BorderLayout.SOUTH);

        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    public void tavernScreen() {
        activeScreen = "tavern";
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(tavernBackground, 0, 0, getWidth(), getHeight(), this); // Tavern-specific background
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());

        // Load the pause icon
        ImageIcon pauseIcon = new ImageIcon("./resources/pause_icon.png");
        Image scaledPauseIcon = pauseIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        pauseIcon = new ImageIcon(scaledPauseIcon);

        // Pause button setup
        pauseButton = new JButton();
        pauseButton.setIcon(pauseIcon);
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusPainted(false);
        pauseButton.addActionListener(e -> System.out.println("Paused!"));

        // Tavern label setup
        areaLabel = new JLabel("Tavern", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setFont(getFont());
                g2d.setColor(Color.BLACK);
                String text = getText();
                FontMetrics metrics = g2d.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth(text)) / 2;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (dx != 0 || dy != 0) {
                            g2d.drawString(text, x + dx, y + dy);
                        }
                    }
                }
                g2d.setColor(Color.WHITE);
                g2d.drawString(text, x, y);
                g2d.dispose();
            }
        };
        areaLabel.setFont(titleFont);
        areaLabel.setOpaque(false);
        areaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        areaLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0));

        // Header buttons setup
        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

        // Map button setup
        ImageIcon mapIcon = new ImageIcon("./resources/map.png");
        Image scaledMap = mapIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        mapIcon = new ImageIcon(scaledMap);
        mapButton = new JButton();
        mapButton.setIcon(mapIcon);
        mapButton.setBackground(Color.BLACK);
        mapButton.setBorderPainted(false);
        mapButton.setContentAreaFilled(false);
        mapButton.setFocusPainted(false);
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Bag button setup
        ImageIcon bagIcon = new ImageIcon("./resources/Bag.png");
        Image scaledBag = bagIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        bagIcon = new ImageIcon(scaledBag);
        bagButton = new JButton();
        bagButton.setIcon(bagIcon);
        bagButton.setBackground(Color.BLACK);
        bagButton.setBorderPainted(false);
        bagButton.setContentAreaFilled(false);
        bagButton.setFocusPainted(false);

        // Character button setup
        ImageIcon characterIcon = new ImageIcon("./resources/Character.png");
        Image scaledCharacter = characterIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        characterIcon = new ImageIcon(scaledCharacter);
        characterButton = new JButton();
        characterButton.setIcon(characterIcon);
        characterButton.setBackground(Color.BLACK);
        characterButton.setBorderPainted(false);
        characterButton.setContentAreaFilled(false);
        characterButton.setFocusPainted(false);
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

        headerButtons.add(mapButton);
        headerButtons.add(bagButton);
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
        headerPanel.add(areaLabel, BorderLayout.CENTER);
        headerPanel.add(headerButtons, BorderLayout.EAST);

        // Interaction panel setup
        interactionPanel = new JPanel();
        interactionPanel.setOpaque(false);
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        interactionPanel.setPreferredSize(new Dimension(window.getWidth(), 200));

        // Text Label
        textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setOpaque(true);
        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        interactionPanel.add(textLabel, BorderLayout.NORTH);

        // Typing effect
        new Thread(() -> {
            String[] npcLines = {
                    "Welcome, adventurer!",
                    "Relax, enjoy a drink.",
                    "Have you heard any tales lately?"
            };

            try {
                for (String line : npcLines) {
                    if (!"tavern".equals(activeScreen)) {
                        return;
                    }
                    StringBuilder displayedText = new StringBuilder();
                    for (char c : line.toCharArray()) {
                        if (!"tavern".equals(activeScreen)) {
                            return;
                        }
                        displayedText.append(c);
                        SwingUtilities.invokeLater(() -> textLabel.setText(displayedText.toString()));
                        Thread.sleep(35);
                    }
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Button Panel
        // Button Panel for Tavern with 6 buttons
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 3, 5, 5)); // 2 rows and 3 columns for six buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

// Button dimensions
        Dimension buttonSize = new Dimension(150, 30);

// Create buttons with appropriate labels
        sleepButton = createChoiceButton("Sleep", buttonSize);
        chestButton = createChoiceButton("Chest", buttonSize);
        talkToInKeeperButton = createChoiceButton("Talk to InKeeper", buttonSize);
        eatButton = createChoiceButton("Eat", buttonSize);
        questsButton = createChoiceButton("Quests", buttonSize);
        townButton = createChoiceButton("Town", buttonSize);

// Add buttons to the panel
        buttonPanel.add(sleepButton);
        buttonPanel.add(chestButton);
        buttonPanel.add(talkToInKeeperButton);
        buttonPanel.add(eatButton);
        buttonPanel.add(questsButton);
        buttonPanel.add(townButton);

// Add button panel to the interaction panel
        interactionPanel.add(buttonPanel);

        interactionPanel.add(buttonPanel);

        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(interactionPanel, BorderLayout.SOUTH);

        window.setContentPane(backgroundPanel);
        window.revalidate();
    }


    private void handleButtonAction(String action) {
        switch (action) {
            case "New Game":
                game.startGame();
                break;
            case "Load Game":
                loadGameScreen();
                break;
            case "Settings":
//            game.openSettings(); // Replace with actual logic
                break;
            case "Back":
                showTitleScreen();
                break;
            case "Town":
                mainArea();
                break;
            case "Clinic":
                clinicScreen();
                break;
            case "Chapel":
                chapelScreen();
                break;
            case "Merchant":
                merchantScreen();
                break;
            case "Tavern": // Added Tavern case
                tavernScreen();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }


    public void prologueGameScreen() {
        // Set up the window layout for better alignment
        window.setLayout(new BorderLayout());

        // Create main text panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.BLACK); // Set background to black for contrast
        mainTextPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering

        // Configure JTextArea
        mainTextArea = new JTextArea();
        mainTextArea.setEditable(false);
        mainTextArea.setForeground(Color.WHITE); // Set text color to white
        mainTextArea.setBackground(Color.BLACK); // Set background to black
        mainTextArea.setFont(textFont); // Use your custom font
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setPreferredSize(new Dimension(580, 250));
        mainTextArea.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20)); // Add padding for centering effect

        // Add JTextArea to JScrollPane
        JScrollPane scrollPane = new JScrollPane(mainTextArea);
        scrollPane.setPreferredSize(new Dimension(580, 250));
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // No border for cleaner look
        mainTextPanel.add(scrollPane);

        // Create and configure the choice panel
        choicePanel = new JPanel();
        choicePanel.setBackground(Color.BLACK);
        choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.Y_AXIS)); // Align choices vertically
        choicePanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center choices horizontally
        createChoiceButtons();

        // Clear the current content and add the new layout
        window.getContentPane().removeAll(); // Remove any previous content
        window.add(mainTextPanel, BorderLayout.CENTER); // Center the main text panel
        window.add(choicePanel, BorderLayout.SOUTH); // Place choice panel at the bottom
        window.revalidate();
        window.repaint();
    }

    private void createChoiceButtons() {
        // Use BoxLayout for vertical alignment
        choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.Y_AXIS));

        // Align the panel to the top
        choicePanel.setAlignmentY(Component.TOP_ALIGNMENT);

        // Initialize buttons
        choice1 = new JButton("Choice 1");
        choice2 = new JButton("Choice 2");
        choice3 = new JButton("Choice 3");
        choice4 = new JButton("Choice 4");
        choice5 = new JButton("Choice 5"); // Add the 5th button

        // Add all buttons to an array for easier handling
        JButton[] choices = {choice1, choice2, choice3, choice4, choice5};
        for (JButton button : choices) {
            button.setBackground(Color.black);  // Set background to black
            button.setForeground(Color.white);  // Set text color to white
            button.setFont(buttonFont);         // Apply the desired font
            button.setFocusPainted(false);      // Remove focus border for aesthetic
            button.setPreferredSize(new Dimension(350, 50)); // Wider buttons
            button.setMaximumSize(new Dimension(350, 50));   // Prevent buttons from expanding
            button.setBorder(BorderFactory.createLineBorder(Color.white, 2)); // Add white border
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center buttons horizontally
            button.addActionListener(new ChoiceButtonHandler());
            button.setVisible(false); // Hide the button by default
            choicePanel.add(button); // Add button to the panel
            choicePanel.add(Box.createVerticalStrut(10)); // Add spacing between buttons
        }

        // Remove unnecessary spacing at the end
        choicePanel.add(Box.createVerticalGlue());
    }

    public void updateMainText(String text) {
        mainTextArea.setText(text);
    }

    public void updateMainTextWithTypingEffect(String text) {
        if (isTyping) {
            return;  // Skip if typing is already in progress
        }

        mainTextArea.setText(""); // Clear previous text
        isTyping = true;  // Set the flag to true to indicate typing is in progress

        new Thread(() -> {
            StringBuilder displayedText = new StringBuilder();
            for (char character : text.toCharArray()) {
                displayedText.append(character);
                mainTextArea.setText(displayedText.toString());
                try {
                    Thread.sleep(25);  // Delay for typing effect
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            isTyping = false;  // Reset the flag when typing is finished
        }).start();
    }

    public void updateChoices(String[] options) {
        JButton[] choices = {choice1, choice2, choice3, choice4, choice5};
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
            if (!isTyping) {  // Only proceed if no typing effect is running
                JButton source = (JButton) e.getSource();
                String choiceText = source.getText();

                // Check if the choice is a class selection
                if (choiceText.equals("Warrior") || choiceText.equals("Mage") ||
                        choiceText.equals("Ranger") || choiceText.equals("Assassin") || choiceText.equals("Monk")) {
                    // Set the player's class based on their choice
                    playerClass = choiceText;
                }
                // Check if the choice is "Escape"
                else if (choiceText.equals("Escape")) {
                    // Transition to the main area when "Escape" is selected
                    mainArea();
                }

                // Handle the choice as usual (for other dialogues/choices)
                game.handleChoice(choiceText);
            }
        }
    }


}
