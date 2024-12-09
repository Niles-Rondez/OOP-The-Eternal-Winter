package ui;

import main.Game;
import audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
    private JFrame window;
    private JPanel titlePanel, startButtonPanel, backgroundPanel, buttonPanel, headerPanel, headerButtons, interactionPanel,
            statsPanel, health_manaPanel, questPanel, imagePanel, mainTextPanel, choicePanel;
    private JLabel titleLabel, areaLabel, textLabel, pauseLabel, healthLabel, manaLabel, strLabel, intLabel, dexLabel, conLabel,
            wisLabel, luckLabel, agiLabel;
    private JButton newGameButton, loadGameButton, settingsButton, exitButton, adventureButton, noticeboardButton, merchantButton,
            clinicButton, chapelButton, tavernButton, pauseButton, mapButton, bagButton, characterButton, buyButton,
            sellButton, townButton, choice1, choice2, choice3, choice4, choice5, sleepButton, chestButton,
            innKeeperButton, eatButton, questButton;
    private JTextArea mainTextArea;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    private Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font textFont = new Font("Times New Roman", Font.BOLD, 25);
    private Image mainMenuBackground, loadGameBackground, mainAreaBackground, clinicBackground, chapelBackground,
            merchantBackground, tavernBackground;
    private boolean isTyping = false;
    private String playerClass;
    private volatile String activeScreen = "";
    private String currentBGM = "TitleScreenBGM";

    /*
    Code to add BGM to stuff:
    audioPlayer.stopMusic(); // Stop previous music
    audioPlayer.playMusic("./resources/music/[nameOfBgm].mp3"); // Adjust the file path.
    currentBGM = [nameOfBgm]; // used for if statements
    */

    private Game game;
    private AudioPlayer audioPlayer = new AudioPlayer();

    public GameUI(Game game) {
        this.game = game;
        this.audioPlayer.playMusic("./resources/music/TitleScreenBGM.mp3");
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
        window.setLocationRelativeTo(null);
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

    private JButton createHeaderButton(String iconPath, int width, int height, ActionListener action) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledIcon);

        JButton button = new JButton();
        button.setIcon(icon);
        button.setBackground(Color.BLACK);
        button.setBorderPainted(false); // Remove button border
        button.setContentAreaFilled(false); // Transparent background
        button.setFocusPainted(false); // Remove focus border
        button.addActionListener(action); // Assign action listener

        return button;
    }

    public void showTitleScreen() {
        if (currentBGM != "TitleScreenBGM"){
            audioPlayer.stopMusic();
            audioPlayer.playMusic("./resources/music/TitleScreenBGM.mp3");
            currentBGM = "TitleScreenBGM";
        }
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

    public void inventoryScreen() {
        // Create the dialog
        JDialog characterDialog = new JDialog(window, "Inventory", true);
        characterDialog.setSize(600, 400);
        characterDialog.setLocationRelativeTo(window);
        characterDialog.setUndecorated(true); // Remove default window decorations

        // Create the main panel for the dialog
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BorderLayout());
        characterPanel.setBackground(Color.BLACK);
        characterPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Add a white border

        // Left panel for quest details
        JPanel leftCharacterPanel = new JPanel();
        leftCharacterPanel.setLayout(new BoxLayout(leftCharacterPanel, BoxLayout.Y_AXIS));
        leftCharacterPanel.setBackground(Color.BLACK);
        leftCharacterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel characterInfoPanel = new JPanel();
        characterInfoPanel.setLayout(new GridLayout(4,1));
        characterInfoPanel.setBackground(Color.BLACK);

        // Align labels to the left-most side
        JLabel nameLabel = new JLabel("ItemName");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(nameLabel);

        JLabel itemTypeLabel = new JLabel("Limitations/Item Type");
        itemTypeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        itemTypeLabel.setForeground(Color.WHITE);
        itemTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(itemTypeLabel);

        JLabel descriptionLabel = new JLabel("Item Description");
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(descriptionLabel);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(3,1));
        statusPanel.setBackground(Color.BLACK);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));

        JLabel statsLabel = new JLabel("Stats:");
        statsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsLabel.setForeground(Color.WHITE);

        statsPanel = new JPanel();
        statsPanel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        statsPanel.setLayout(new GridLayout(3,3));

        strLabel = new JLabel("Str: (+10)");
        strLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        strLabel.setForeground(Color.WHITE);
//        strLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        intLabel = new JLabel("Int: (0)");
        intLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        intLabel.setForeground(Color.WHITE);

        dexLabel = new JLabel("Dex: (0)");
        dexLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        dexLabel.setForeground(Color.WHITE);

        conLabel = new JLabel("Con: (-10)");
        conLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        conLabel.setForeground(Color.WHITE);

        wisLabel = new JLabel("Wis: (0)");
        wisLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        wisLabel.setForeground(Color.WHITE);

        luckLabel = new JLabel("Luck: (0)");
        luckLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        luckLabel.setForeground(Color.WHITE);

        agiLabel = new JLabel("Agi: (+10)");
        agiLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        agiLabel.setForeground(Color.WHITE);

        statsPanel.add(strLabel);
        statsPanel.add(intLabel);
        statsPanel.add(dexLabel);
        statsPanel.add(conLabel);
        statsPanel.add(wisLabel);
        statsPanel.add(luckLabel);
        statsPanel.add(agiLabel);

        statusPanel.add(statsLabel);
        statusPanel.add(statsPanel);

        leftCharacterPanel.add(characterInfoPanel);
        leftCharacterPanel.add(Box.createVerticalStrut(10));
        leftCharacterPanel.add(statusPanel);

        // Buttons for accepting or dropping quests
        JPanel pointsPanel = new JPanel();
        pointsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pointsPanel.setBackground(Color.BLACK);

        JButton dropButton = createChoiceButton("Trash Item", new Dimension(150, 30));
        JButton acceptButton = createChoiceButton("Equip", new Dimension(150, 30));

        pointsPanel.add(dropButton);
        pointsPanel.add(acceptButton);

        leftCharacterPanel.add(Box.createVerticalGlue()); // Push the buttons to the bottom
        leftCharacterPanel.add(pointsPanel);

        // Right panel for quest list
        JPanel questListPanel = new JPanel();
        questListPanel.setLayout(new BorderLayout());
        questListPanel.setBackground(Color.BLACK);
        questListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top-right corner panel for the close button
        JPanel closeButtonPanel = new JPanel();
        closeButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        closeButtonPanel.setBackground(Color.BLACK);

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        closeButton.addActionListener(e -> characterDialog.dispose());

        closeButtonPanel.add(closeButton);

        // Center panel for quest buttons
        JPanel questButtonListPanel = new JPanel();
        questButtonListPanel.setLayout(new BoxLayout(questButtonListPanel, BoxLayout.Y_AXIS));
        questButtonListPanel.setBackground(Color.BLACK);

        JButton quest1Button = createChoiceButton("Item Name 1", new Dimension(200, 30));
        JButton quest2Button = createChoiceButton("Item Name 2", new Dimension(200, 30));
        JButton quest3Button = createChoiceButton("Item Name 3", new Dimension(200, 30));

        quest1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        quest2Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        quest3Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        questButtonListPanel.add(quest1Button);
        questButtonListPanel.add(Box.createVerticalStrut(10));
        questButtonListPanel.add(quest2Button);
        questButtonListPanel.add(Box.createVerticalStrut(10));
        questButtonListPanel.add(quest3Button);

        questListPanel.add(closeButtonPanel, BorderLayout.NORTH); // Add close button at the top
        questListPanel.add(questButtonListPanel, BorderLayout.CENTER); // Add quest buttons in the center

        // Add panels to the main quest panel
        characterPanel.add(leftCharacterPanel, BorderLayout.CENTER);
        characterPanel.add(questListPanel, BorderLayout.EAST);

        characterDialog.add(characterPanel);
        characterDialog.setVisible(true);
    }

    public void characterScreen() {
        // Create the dialog
        JDialog characterDialog = new JDialog(window, "Stats", true);
        characterDialog.setSize(600, 400);
        characterDialog.setLocationRelativeTo(window);
        characterDialog.setUndecorated(true); // Remove default window decorations

        // Create the main panel for the dialog
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BorderLayout());
        characterPanel.setBackground(Color.BLACK);
        characterPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Add a white border

        // Left panel for quest details
        JPanel leftCharacterPanel = new JPanel();
        leftCharacterPanel.setLayout(new BoxLayout(leftCharacterPanel, BoxLayout.Y_AXIS));
        leftCharacterPanel.setBackground(Color.BLACK);
        leftCharacterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel characterInfoPanel = new JPanel();
        characterInfoPanel.setLayout(new GridLayout(4,1));
        characterInfoPanel.setBackground(Color.BLACK);

        // Align labels to the left-most side
        JLabel nameLabel = new JLabel("Name: RykelleBayot");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(nameLabel);

        JLabel classLabel = new JLabel("Class: Gaylord");
        classLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        classLabel.setForeground(Color.WHITE);
        classLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(classLabel);

        health_manaPanel = new JPanel();
        health_manaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        health_manaPanel.setBackground(Color.BLACK);
        health_manaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        healthLabel = new JLabel("HP: 30/30");
        healthLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        healthLabel.setForeground(Color.WHITE);
        healthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        manaLabel = new JLabel("MP: 30/30");
        manaLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        manaLabel.setForeground(Color.WHITE);
        manaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        health_manaPanel.add(healthLabel);
        health_manaPanel.add(manaLabel);

        characterInfoPanel.add(health_manaPanel);

        JLabel statusEffectsLabel = new JLabel("Status Effects: Poison, Str Potion Buff");
        statusEffectsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statusEffectsLabel.setForeground(Color.WHITE);
        statusEffectsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(statusEffectsLabel);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(3,1));
        statusPanel.setBackground(Color.BLACK);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));

        JLabel statsLabel = new JLabel("Stats");
        statsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsLabel.setForeground(Color.WHITE);

        statsPanel = new JPanel();
        statsPanel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        statsPanel.setLayout(new GridLayout(3,3));

        strLabel = new JLabel("Str: (+10)");
        strLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        strLabel.setForeground(Color.WHITE);
//        strLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        intLabel = new JLabel("Int: (0)");
        intLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        intLabel.setForeground(Color.WHITE);

        dexLabel = new JLabel("Dex: (0)");
        dexLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        dexLabel.setForeground(Color.WHITE);

        conLabel = new JLabel("Con: (-10)");
        conLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        conLabel.setForeground(Color.WHITE);

        wisLabel = new JLabel("Wis: (0)");
        wisLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        wisLabel.setForeground(Color.WHITE);

        luckLabel = new JLabel("Luck: (0)");
        luckLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        luckLabel.setForeground(Color.WHITE);

        agiLabel = new JLabel("Agi: (+10)");
        agiLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        agiLabel.setForeground(Color.WHITE);

        statsPanel.add(strLabel);
        statsPanel.add(intLabel);
        statsPanel.add(dexLabel);
        statsPanel.add(conLabel);
        statsPanel.add(wisLabel);
        statsPanel.add(luckLabel);
        statsPanel.add(agiLabel);

        JLabel availablePointsLabel = new JLabel("Available Stat points: 20");
        availablePointsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        availablePointsLabel.setForeground(Color.WHITE);

        statusPanel.add(statsLabel);
        statusPanel.add(statsPanel);
        statusPanel.add(availablePointsLabel);

        leftCharacterPanel.add(characterInfoPanel);
        leftCharacterPanel.add(Box.createVerticalStrut(10));
        leftCharacterPanel.add(statusPanel);

        // Buttons for accepting or dropping quests
        JPanel pointsPanel = new JPanel();
        pointsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pointsPanel.setBackground(Color.BLACK);

        JButton dropButton = createChoiceButton("Distribute Points", new Dimension(150, 30));
        JButton acceptButton = createChoiceButton("Reset", new Dimension(150, 30));

        pointsPanel.add(dropButton);
        pointsPanel.add(acceptButton);

        leftCharacterPanel.add(Box.createVerticalGlue()); // Push the buttons to the bottom
        leftCharacterPanel.add(pointsPanel);

        // Right panel for quest list
        JPanel rightCharacterPanel = new JPanel();
        rightCharacterPanel.setLayout(new BorderLayout());
        rightCharacterPanel.setBackground(Color.BLACK);
        rightCharacterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top-right corner panel for the close button
        JPanel closeButtonPanel = new JPanel();
        closeButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        closeButtonPanel.setBackground(Color.BLACK);

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        closeButton.addActionListener(e -> characterDialog.dispose());

        closeButtonPanel.add(closeButton);

        // Center panel for quest buttons
        JPanel armoryButtonPanel = new JPanel();
        armoryButtonPanel.setLayout(new BoxLayout(armoryButtonPanel, BoxLayout.Y_AXIS));
        armoryButtonPanel.setBackground(Color.BLACK);

        JButton headArmorButton = createChoiceButton("Head Armor", new Dimension(200, 30));
        JButton bodyArmorButton = createChoiceButton("Body Armor", new Dimension(200, 30));
        JButton legArmorButton = createChoiceButton("Leg Armor", new Dimension(200, 30));
        JButton mainHandButton = createChoiceButton("Main Hand", new Dimension(200, 30));
        JButton offhandButton = createChoiceButton("Off-Hand", new Dimension(200, 30));

        headArmorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bodyArmorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        legArmorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainHandButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        offhandButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        armoryButtonPanel.add(headArmorButton);
        armoryButtonPanel.add(Box.createVerticalStrut(10));
        armoryButtonPanel.add(bodyArmorButton);
        armoryButtonPanel.add(Box.createVerticalStrut(10));
        armoryButtonPanel.add(legArmorButton);
        armoryButtonPanel.add(Box.createVerticalStrut(10));
        armoryButtonPanel.add(mainHandButton);
        armoryButtonPanel.add(Box.createVerticalStrut(10));
        armoryButtonPanel.add(offhandButton);

        JLabel cashText = new JLabel("Cash: $99999999");
        cashText.setForeground(Color.WHITE);
        cashText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cashText.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        rightCharacterPanel.add(closeButtonPanel, BorderLayout.NORTH); // Add close button at the top
        rightCharacterPanel.add(armoryButtonPanel, BorderLayout.CENTER); // Add quest buttons in the center
        rightCharacterPanel.add(cashText, BorderLayout.SOUTH);

        // Add panels to the main quest panel
        characterPanel.add(leftCharacterPanel, BorderLayout.WEST);
        characterPanel.add(rightCharacterPanel, BorderLayout.EAST);

        characterDialog.add(characterPanel);
        characterDialog.setVisible(true);
    }

    public void mainArea() {
        if (currentBGM != "TownBGM"){
            audioPlayer.stopMusic();
            audioPlayer.playMusic("./resources/music/TownBGM.mp3");
            currentBGM = "TownBGM";
        }
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
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton);

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen()
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
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
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton);

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen()
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
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

        buyButton = createChoiceButton("Buy Potions", choicebuttonSize);
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

    public void buyPotionsScreen() {
        // Create the dialog
        JDialog characterDialog = new JDialog(window, "Buy Potions", true);
        characterDialog.setSize(600, 400);
        characterDialog.setLocationRelativeTo(window);
        characterDialog.setUndecorated(true); // Remove default window decorations

        // Create the main panel for the dialog
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BorderLayout());
        characterPanel.setBackground(Color.BLACK);
        characterPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Add a white border

        // Left panel for quest details
        JPanel leftCharacterPanel = new JPanel();
        leftCharacterPanel.setLayout(new BoxLayout(leftCharacterPanel, BoxLayout.Y_AXIS));
        leftCharacterPanel.setBackground(Color.BLACK);
        leftCharacterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel characterInfoPanel = new JPanel();
        characterInfoPanel.setLayout(new GridLayout(4,1));
        characterInfoPanel.setBackground(Color.BLACK);

        // Align labels to the left-most side
        JLabel nameLabel = new JLabel("Item Name");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(nameLabel);

        JLabel itemTypeLabel = new JLabel("Limitations/Item Type");
        itemTypeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        itemTypeLabel.setForeground(Color.WHITE);
        itemTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(itemTypeLabel);

        JLabel descriptionLabel = new JLabel("Item Description");
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(descriptionLabel);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(3,1));
        statusPanel.setBackground(Color.BLACK);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));

        JLabel statsLabel = new JLabel("Stats:");
        statsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsLabel.setForeground(Color.WHITE);

        statsPanel = new JPanel();
        statsPanel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        statsPanel.setLayout(new GridLayout(3,3));

        strLabel = new JLabel("Str: (+10)");
        strLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        strLabel.setForeground(Color.WHITE);
//        strLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        intLabel = new JLabel("Int: (0)");
        intLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        intLabel.setForeground(Color.WHITE);

        dexLabel = new JLabel("Dex: (0)");
        dexLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        dexLabel.setForeground(Color.WHITE);

        conLabel = new JLabel("Con: (-10)");
        conLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        conLabel.setForeground(Color.WHITE);

        wisLabel = new JLabel("Wis: (0)");
        wisLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        wisLabel.setForeground(Color.WHITE);

        luckLabel = new JLabel("Luck: (0)");
        luckLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        luckLabel.setForeground(Color.WHITE);

        agiLabel = new JLabel("Agi: (+10)");
        agiLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        agiLabel.setForeground(Color.WHITE);

        statsPanel.add(strLabel);
        statsPanel.add(intLabel);
        statsPanel.add(dexLabel);
        statsPanel.add(conLabel);
        statsPanel.add(wisLabel);
        statsPanel.add(luckLabel);
        statsPanel.add(agiLabel);

        statusPanel.add(statsLabel);
        statusPanel.add(statsPanel);

        leftCharacterPanel.add(characterInfoPanel);
        leftCharacterPanel.add(Box.createVerticalStrut(10));
        leftCharacterPanel.add(statusPanel);

        // Buttons for accepting or dropping quests
        JPanel pointsPanel = new JPanel();
        pointsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pointsPanel.setBackground(Color.BLACK);

        JButton dropButton = createChoiceButton("Buy Item", new Dimension(150, 30));

        pointsPanel.add(dropButton);

        leftCharacterPanel.add(Box.createVerticalGlue()); // Push the buttons to the bottom
        leftCharacterPanel.add(pointsPanel);

        // Right panel for quest list
        JPanel questListPanel = new JPanel();
        questListPanel.setLayout(new BorderLayout());
        questListPanel.setBackground(Color.BLACK);
        questListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top-right corner panel for the close button
        JPanel closeButtonPanel = new JPanel();
        closeButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        closeButtonPanel.setBackground(Color.BLACK);

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        closeButton.addActionListener(e -> characterDialog.dispose());

        closeButtonPanel.add(closeButton);

        // Center panel for quest buttons
        JPanel questButtonListPanel = new JPanel();
        questButtonListPanel.setLayout(new BoxLayout(questButtonListPanel, BoxLayout.Y_AXIS));
        questButtonListPanel.setBackground(Color.BLACK);

        JButton quest1Button = createChoiceButton("Item Name 1", new Dimension(200, 30));
        JButton quest2Button = createChoiceButton("Item Name 2", new Dimension(200, 30));
        JButton quest3Button = createChoiceButton("Item Name 3", new Dimension(200, 30));

        quest1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        quest2Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        quest3Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        questButtonListPanel.add(quest1Button);
        questButtonListPanel.add(Box.createVerticalStrut(10));
        questButtonListPanel.add(quest2Button);
        questButtonListPanel.add(Box.createVerticalStrut(10));
        questButtonListPanel.add(quest3Button);

        questListPanel.add(closeButtonPanel, BorderLayout.NORTH); // Add close button at the top
        questListPanel.add(questButtonListPanel, BorderLayout.CENTER); // Add quest buttons in the center

        // Add panels to the main quest panel
        characterPanel.add(leftCharacterPanel, BorderLayout.CENTER);
        characterPanel.add(questListPanel, BorderLayout.EAST);

        characterDialog.add(characterPanel);
        characterDialog.setVisible(true);
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
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton);

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen()
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
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

// Array of priest's dialogue lines
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
                        Thread.sleep(50); // Typing effect delay
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

        buyButton = createChoiceButton("Heal", choicebuttonSize);
        sellButton = createChoiceButton("Shop", choicebuttonSize);
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
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
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

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen()
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
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

        buyButton = createChoiceButton("Buy Weapons", choicebuttonSize);
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
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(tavernBackground, 0, 0, getWidth(), getHeight(), this);
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

        areaLabel = new JLabel("Tavern", SwingConstants.CENTER) {
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
        headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton);

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen()
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
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
        textLabel = new JLabel("Tavern Keeper Dialogue.", SwingConstants.CENTER);
        textLabel.setOpaque(true); // Make the background visible
        textLabel.setBackground(Color.BLACK); // Set background to black
        textLabel.setForeground(Color.WHITE); // Set text color to white for contrast
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Smaller font size
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the label
        interactionPanel.add(textLabel, BorderLayout.NORTH);

// Button Panel (Bottom Part of Interaction Panel)
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 3, 5, 5)); // Add spacing between buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the button panel

// Adjust button size
        Dimension choicebuttonSize = new Dimension(150, 30); // Make buttons smaller to match the image

        sleepButton = createChoiceButton("Sleep", choicebuttonSize);
        chestButton = createChoiceButton("Chest", choicebuttonSize);
        innKeeperButton = createChoiceButton("Talk To InnKeeper", choicebuttonSize);
        eatButton = createChoiceButton("Eat", choicebuttonSize);
        questButton = createChoiceButton("Quests", choicebuttonSize);
        townButton = createChoiceButton("Town", choicebuttonSize);

        buttonPanel.add(sleepButton);
        buttonPanel.add(chestButton);
        buttonPanel.add(innKeeperButton);
        buttonPanel.add(eatButton);
        buttonPanel.add(questButton);
        buttonPanel.add(townButton);

        interactionPanel.add(buttonPanel);

        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(interactionPanel, BorderLayout.SOUTH);

        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    public void questBoard() {
        // Create the dialog
        JDialog questDialog = new JDialog(window, "Quest Board", true);
        questDialog.setSize(600, 400);
        questDialog.setLocationRelativeTo(window);
        questDialog.setUndecorated(true); // Remove default window decorations

        // Create the main panel for the dialog
        JPanel questPanel = new JPanel();
        questPanel.setLayout(new BorderLayout());
        questPanel.setBackground(Color.BLACK);
        questPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Add a white border

        // Left panel for quest details
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.BLACK);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel questDetails = new JPanel();
        questDetails.setLayout(new GridLayout(4,1));
        questDetails.setBackground(Color.BLACK);

        // Align labels to the left-most side
        JLabel questNameLabel = new JLabel("Quest Name");
        questNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        questNameLabel.setForeground(Color.WHITE);
        questNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questDetails.add(questNameLabel);

        JLabel difficultyLabel = new JLabel("Difficulty");
        difficultyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        difficultyLabel.setForeground(Color.WHITE);
        difficultyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questDetails.add(difficultyLabel);

        JLabel descriptionLabel = new JLabel("<html>Quest Description</html>");
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questDetails.add(descriptionLabel);

        JLabel rewardsLabel = new JLabel("Rewards");
        rewardsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rewardsLabel.setForeground(Color.WHITE);
        rewardsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questDetails.add(rewardsLabel);

        leftPanel.add(questDetails);
        leftPanel.add(Box.createVerticalStrut(10));

        // Buttons for accepting or dropping quests
        JPanel questButtonPanel = new JPanel();
        questButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        questButtonPanel.setBackground(Color.BLACK);

        JButton dropButton = createChoiceButton("Drop Quest", new Dimension(150, 30));
        JButton acceptButton = createChoiceButton("Accept", new Dimension(150, 30));

        questButtonPanel.add(dropButton);
        questButtonPanel.add(acceptButton);

        leftPanel.add(Box.createVerticalGlue()); // Push the buttons to the bottom
        leftPanel.add(questButtonPanel);

        // Right panel for quest list
        JPanel questListPanel = new JPanel();
        questListPanel.setLayout(new BorderLayout());
        questListPanel.setBackground(Color.BLACK);
        questListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top-right corner panel for the close button
        JPanel closeButtonPanel = new JPanel();
        closeButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        closeButtonPanel.setBackground(Color.BLACK);

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        closeButton.addActionListener(e -> questDialog.dispose());

        closeButtonPanel.add(closeButton);

        // Center panel for quest buttons
        JPanel questButtonListPanel = new JPanel();
        questButtonListPanel.setLayout(new BoxLayout(questButtonListPanel, BoxLayout.Y_AXIS));
        questButtonListPanel.setBackground(Color.BLACK);

        JButton quest1Button = createChoiceButton("Quest Name 1", new Dimension(200, 30));
        JButton quest2Button = createChoiceButton("Quest Name 2", new Dimension(200, 30));
        JButton quest3Button = createChoiceButton("Quest Name 3", new Dimension(200, 30));

        quest1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        quest2Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        quest3Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        questButtonListPanel.add(quest1Button);
        questButtonListPanel.add(Box.createVerticalStrut(10));
        questButtonListPanel.add(quest2Button);
        questButtonListPanel.add(Box.createVerticalStrut(10));
        questButtonListPanel.add(quest3Button);

        questListPanel.add(closeButtonPanel, BorderLayout.NORTH); // Add close button at the top
        questListPanel.add(questButtonListPanel, BorderLayout.CENTER); // Add quest buttons in the center

        // Add panels to the main quest panel
        questPanel.add(leftPanel, BorderLayout.CENTER);
        questPanel.add(questListPanel, BorderLayout.EAST);

        questDialog.add(questPanel);
        questDialog.setVisible(true);
    }

    public void weaponsShopScreen() {
        // Create the dialog
        JDialog characterDialog = new JDialog(window, "Inventory", true);
        characterDialog.setSize(600, 400);
        characterDialog.setLocationRelativeTo(window);
        characterDialog.setUndecorated(true); // Remove default window decorations

        // Create the main panel for the dialog
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BorderLayout());
        characterPanel.setBackground(Color.BLACK);
        characterPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Add a white border

        // Left panel for quest details
        JPanel leftCharacterPanel = new JPanel();
        leftCharacterPanel.setLayout(new BoxLayout(leftCharacterPanel, BoxLayout.Y_AXIS));
        leftCharacterPanel.setBackground(Color.BLACK);
        leftCharacterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel characterInfoPanel = new JPanel();
        characterInfoPanel.setLayout(new GridLayout(4,1));
        characterInfoPanel.setBackground(Color.BLACK);

        // Align labels to the left-most side
        JLabel nameLabel = new JLabel("ItemName");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(nameLabel);

        JLabel itemTypeLabel = new JLabel("Limitations/Item Type");
        itemTypeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        itemTypeLabel.setForeground(Color.WHITE);
        itemTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(itemTypeLabel);

        JLabel descriptionLabel = new JLabel("Item Description");
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(descriptionLabel);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(3,1));
        statusPanel.setBackground(Color.BLACK);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));

        JLabel statsLabel = new JLabel("Stats:");
        statsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsLabel.setForeground(Color.WHITE);

        statsPanel = new JPanel();
        statsPanel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        statsPanel.setLayout(new GridLayout(3,3));

        strLabel = new JLabel("Str: (+10)");
        strLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        strLabel.setForeground(Color.WHITE);
//        strLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        intLabel = new JLabel("Int: (0)");
        intLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        intLabel.setForeground(Color.WHITE);

        dexLabel = new JLabel("Dex: (0)");
        dexLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        dexLabel.setForeground(Color.WHITE);

        conLabel = new JLabel("Con: (-10)");
        conLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        conLabel.setForeground(Color.WHITE);

        wisLabel = new JLabel("Wis: (0)");
        wisLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        wisLabel.setForeground(Color.WHITE);

        luckLabel = new JLabel("Luck: (0)");
        luckLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        luckLabel.setForeground(Color.WHITE);

        agiLabel = new JLabel("Agi: (+10)");
        agiLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        agiLabel.setForeground(Color.WHITE);

        statsPanel.add(strLabel);
        statsPanel.add(intLabel);
        statsPanel.add(dexLabel);
        statsPanel.add(conLabel);
        statsPanel.add(wisLabel);
        statsPanel.add(luckLabel);
        statsPanel.add(agiLabel);

        JLabel costLabel = new JLabel("Item Cost: $10000000");
        costLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        costLabel.setForeground(Color.WHITE);
        costLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        statusPanel.add(statsLabel);
        statusPanel.add(statsPanel);
        statusPanel.add(costLabel);

        leftCharacterPanel.add(characterInfoPanel);
        leftCharacterPanel.add(Box.createVerticalStrut(10));
        leftCharacterPanel.add(statusPanel);

        // Buttons for accepting or dropping quests
        JPanel pointsPanel = new JPanel();
        pointsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pointsPanel.setBackground(Color.BLACK);

        JButton dropButton = createChoiceButton("Sell Item", new Dimension(150, 30));
        JButton acceptButton = createChoiceButton("Buy", new Dimension(150, 30));

        pointsPanel.add(dropButton);
        pointsPanel.add(acceptButton);

        leftCharacterPanel.add(Box.createVerticalGlue()); // Push the buttons to the bottom
        leftCharacterPanel.add(pointsPanel);

        // Right panel for quest list
        JPanel questListPanel = new JPanel();
        questListPanel.setLayout(new BorderLayout());
        questListPanel.setBackground(Color.BLACK);
        questListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top-right corner panel for the close button
        JPanel closeButtonPanel = new JPanel();
        closeButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        closeButtonPanel.setBackground(Color.BLACK);

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        closeButton.addActionListener(e -> characterDialog.dispose());

        closeButtonPanel.add(closeButton);

        // Center panel for quest buttons
        JPanel questButtonListPanel = new JPanel();
        questButtonListPanel.setLayout(new BoxLayout(questButtonListPanel, BoxLayout.Y_AXIS));
        questButtonListPanel.setBackground(Color.BLACK);

        JButton quest1Button = createChoiceButton("Item Name 1", new Dimension(200, 30));
        JButton quest2Button = createChoiceButton("Item Name 2", new Dimension(200, 30));
        JButton quest3Button = createChoiceButton("Item Name 3", new Dimension(200, 30));

        quest1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        quest2Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        quest3Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        questButtonListPanel.add(quest1Button);
        questButtonListPanel.add(Box.createVerticalStrut(10));
        questButtonListPanel.add(quest2Button);
        questButtonListPanel.add(Box.createVerticalStrut(10));
        questButtonListPanel.add(quest3Button);

        questListPanel.add(closeButtonPanel, BorderLayout.NORTH); // Add close button at the top
        questListPanel.add(questButtonListPanel, BorderLayout.CENTER); // Add quest buttons in the center

        // Add panels to the main quest panel
        characterPanel.add(leftCharacterPanel, BorderLayout.CENTER);
        characterPanel.add(questListPanel, BorderLayout.EAST);

        characterDialog.add(characterPanel);
        characterDialog.setVisible(true);
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
//                game.openSettings(); // Replace with actual logic
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
            case "Buy Potions":
                buyPotionsScreen();
                break;
            case "Chapel":
                chapelScreen();
                break;
            case "Merchant":
                merchantScreen();
                break;
            case "Tavern":
                tavernScreen();
                break;
            case "Buy Weapons":
                weaponsShopScreen();
                break;
            case "Quests":
                questBoard();
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
