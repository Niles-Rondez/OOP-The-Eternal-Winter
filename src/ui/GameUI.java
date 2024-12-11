package ui;

import characters.PlayerCharacter;
import main.Game;
import audio.AudioPlayer;
import adventure.AdventureManager;
import enemies.Enemy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameUI {
    private JFrame window;
    private JPanel titlePanel, startButtonPanel, backgroundPanel, buttonPanel, headerPanel, headerButtons, interactionPanel,
            statsPanel, health_manaPanel, questPanel, imagePanel, mainTextPanel, choicePanel;
    private JLabel titleLabel, areaLabel, textLabel, dialogueLabel, enemyLabel, pauseLabel, healthLabel, manaLabel, strLabel, intLabel, dexLabel, conLabel,
            wisLabel, luckLabel, agiLabel;
    private JButton newGameButton, loadGameButton, settingsButton, exitButton, adventureButton, noticeboardButton, merchantButton,
            clinicButton, chapelButton, tavernButton, pauseButton, mapButton, bagButton, characterButton, buyButton,
            sellButton, townButton, choice1, choice2, choice3, choice4, choice5, sleepButton, chestButton,
            innKeeperButton, eatButton, questButton, attackButton, skillButton, itemButton, runButton;
    private JTextArea mainTextArea;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    private Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font textFont = new Font("Times New Roman", Font.PLAIN, 25);
    private Image mainMenuBackground, loadGameBackground, mainAreaBackground, clinicBackground, chapelBackground,
            merchantBackground, tavernBackground, area1Background, direWolfBackground, skeletonBackground, goblinBackground, dragonBackground;
    private boolean isTyping = false;
    private String playerClass;
    private volatile String activeScreen = "";
    private String currentBGM = "TitleScreenBGM";
    private String[] enemies = {"A Direwolf", "A Skeleton", "A Goblin", "A Dragon"};
    private String currentEnemy;
    private String lastScreen; // To track the previous screen
    private String lastActiveScreen; // To store the last active screen
    private Map<String, String> attackDetails;
    private Map<String, String> skillDetails;
    private Map<String, String> itemDetails;
    private Random random;
    private AdventureManager adventureManager;

    private static boolean guiMode = true; // Set to true if running in GUI mode

    public static boolean isGuiMode() {
        return guiMode;
    }

    public static void setGuiMode(boolean mode) {
        guiMode = mode;
    }

    /*
    Code to add BGM to stuff:
    audioPlayer.stopMusic(); // Stop previous music
    audioPlayer.playMusic("./resources/music/[nameOfBgm].mp3"); // Adjust the file path.
    currentBGM = [nameOfBgm]; // used for if statements
    */

    private Game game;
    private AudioPlayer audioPlayer = new AudioPlayer();
    private PlayerCharacter player;



    public GameUI(Game game) {
        this.player = new PlayerCharacter();
        this.adventureManager = new AdventureManager(player);

        this.game = game;
        this.audioPlayer.playMusic("./resources/music/TitleScreenBGM.mp3");
        mainMenuBackground = new ImageIcon("./resources/MainMenuBackground.png").getImage();
        loadGameBackground = new ImageIcon("./resources/LoadGameBackground.png").getImage();
        mainAreaBackground = new ImageIcon("./resources/TownBackground.png").getImage();
        clinicBackground = new ImageIcon("./resources/Clinic.png").getImage();
        chapelBackground = new ImageIcon("./resources/Chapel.png").getImage();
        merchantBackground = new ImageIcon("./resources/Merchant.png").getImage();
        tavernBackground = new ImageIcon("./resources/Tavern.png").getImage();
        area1Background = new ImageIcon("./resources/Area1.png").getImage();
        direWolfBackground = new ImageIcon("./resources/DireWolfBattle.png").getImage();
        skeletonBackground = new ImageIcon("./resources/SkeletonBattle.png").getImage();
        goblinBackground = new ImageIcon("./resources/GoblinBattle.png").getImage();
        dragonBackground = new ImageIcon("./resources/DragonBattle.png").getImage();
        createWindow();
    }

    private void createWindow() {
        window = new JFrame();
        window.setSize(1280,960);
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
        exitButton.addActionListener(e -> {
            window.getGlassPane().setVisible(false);
            showTitleScreen(); // Switch to the main menu
        });

        newGameButton.addActionListener(e -> {
            window.getGlassPane().setVisible(false);
            mainArea();
        });

        loadGameButton.addActionListener(e -> {
            window.getGlassPane().setVisible(false);
            mainArea();
        });



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

    public void pauseGame() {
        // Save the current screen as the last active screen
        lastActiveScreen = activeScreen;

        JPanel pauseOverlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        pauseOverlay.setLayout(new BoxLayout(pauseOverlay, BoxLayout.Y_AXIS));
        pauseOverlay.setOpaque(false);

        JButton resumeButton = createButton("Resume", new Dimension(200, 50));
        resumeButton.addActionListener(e -> {
            window.getGlassPane().setVisible(false);
            switchToLastScreen(); // Restore the last active screen
        });

        JButton mainMenuButton = createButton("Main Menu", new Dimension(200, 50));
        mainMenuButton.addActionListener(e -> {
            window.getGlassPane().setVisible(false);
            showTitleScreen(); // Switch to the main menu
        });

        JButton exitButton = createButton("Exit", new Dimension(200, 50));
        exitButton.addActionListener(e -> System.exit(0));

        pauseOverlay.add(Box.createVerticalGlue());
        pauseOverlay.add(resumeButton);
        pauseOverlay.add(Box.createVerticalStrut(20));
        pauseOverlay.add(mainMenuButton);
        pauseOverlay.add(Box.createVerticalStrut(20));
        pauseOverlay.add(exitButton);
        pauseOverlay.add(Box.createVerticalGlue());

        window.setGlassPane(pauseOverlay);
        pauseOverlay.setVisible(true);
    }

    private void switchToLastScreen() {
        if (lastActiveScreen == null) {
            System.out.println("No previous screen to return to!");
            return;
        }

        switch (lastActiveScreen) {
            case "town":
                mainArea();
                break;
            case "adventure":
                adventureScreen();
                break;
            case "clinic":
                clinicScreen();
                break;
            case "chapel":
                chapelScreen();
                break;
            case "merchant":
                merchantScreen();
                break;
            case "tavern":
                tavernScreen();
                break;
            default:
                System.out.println("Unknown screen: " + lastActiveScreen);
        }

        // Clear the lastActiveScreen to avoid reloading incorrect screens in the future
        lastActiveScreen = null;
    }

    public void adventureScreen() {
        // Background panel with image
        activeScreen = "Adventure";
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(area1Background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Title Panel
        headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        this.random = new Random();

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
        pauseButton.addActionListener(e -> pauseGame()); // Add your pause logic here

        JPanel adventurePanel = new JPanel();
        adventurePanel.setOpaque(false);
        adventurePanel.setLayout(new GridLayout(3,1));

        areaLabel = new JLabel("Adventuring...", SwingConstants.CENTER) {
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

        enemyLabel = new JLabel("", SwingConstants.CENTER) {
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
        enemyLabel.setFont(titleFont); // Apply your font
        enemyLabel.setOpaque(false); // Transparent background
        enemyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enemyLabel.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0)); // Top, Left, Bottom, Right

        JPanel adventureButtons = new JPanel();
        adventureButtons.setOpaque(false);
        adventureButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

        Dimension adventurebuttonSize = new Dimension(250, 50);
        JButton fightButton = createButton("Fight", adventurebuttonSize);
        fightButton.addActionListener(e -> handleFight());
        JButton runButton = createButton("Run", adventurebuttonSize);

        adventureButtons.add(fightButton);
        adventureButtons.add(runButton);

        adventurePanel.add(areaLabel);
        adventurePanel.add(enemyLabel);
        adventurePanel.add(adventureButtons);


        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        /* mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton); */

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen(player)
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
        headerPanel.add(headerButtons, BorderLayout.EAST);

        // Create an array of all area backgrounds
        Image[] areaBackgrounds = {
                new ImageIcon("./resources/Area1.png").getImage(),
                new ImageIcon("./resources/Area2.png").getImage(),
                new ImageIcon("./resources/Area3.png").getImage(),
                new ImageIcon("./resources/Area4.png").getImage()
        };

        // Background panel with image
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(area1Background, 0, 0, getWidth(), getHeight(), this); // Default Area1 background
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Button Panel (Center the buttons)
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());  // Use GridBagLayout to center the buttons

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;  // Set horizontal position
        gbc.gridy = 0;  // Set vertical position
        gbc.insets = new Insets(5, 0, 10, 0);  // Reduced vertical spacing to move the buttons up

        // Create buttons
        Dimension buttonSize = new Dimension(250, 50);
        JButton contAdventureButton = createButton("Continue Adventuring", buttonSize);
        JButton backtoTownButton = createButton("Back to Town", buttonSize);

        // Add buttons to the panel using GridBagLayout
        buttonPanel.add(contAdventureButton, gbc);

        gbc.gridy++;  // Move to the next row
        buttonPanel.add(backtoTownButton, gbc);

        // Add title and button panels to background
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(adventurePanel, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for continue adventure button to randomize the background
        contAdventureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Execute a random encounter and update the UI based on the outcome
                int roll = random.nextInt(100);
                if (roll < 40) { // 40% chance for an enemy encounter
                    Enemy enemy = adventureManager.generateRandomEnemy(); // Ensure this method is accessible
                    currentEnemy = enemy.getName();
                    enemyLabel.setText("A wild " + currentEnemy + " has appeared!");
                    backgroundPanel.repaint();
                    // Trigger combat or prepare combat screen
                    // Call your combat handling logic here if needed
                } else if (roll < 80) { // 40% chance for nothing
                    currentEnemy = null; // No enemy
                    enemyLabel.setText("The path is clear. No enemies in sight.");
                    backgroundPanel.repaint();
                } else { // 20% chance for treasure
                    int gold = random.nextInt(20) + 10; // Random gold between 10 and 30
                    player.addGold(gold);
                    enemyLabel.setText("You found " + gold + " gold! Total gold: " + player.getGold());
                    backgroundPanel.repaint();
                }

                // Optionally update the background to a new random area
                Image newBackground = areaBackgrounds[random.nextInt(areaBackgrounds.length)];
                area1Background = newBackground;
                backgroundPanel.repaint();
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Randomly select a background, excluding the current one
                Random rand = new Random();
// Randomly select a new background and enemy
                Image newBackground = areaBackgrounds[rand.nextInt(areaBackgrounds.length)];
                String newEnemy = enemies[rand.nextInt(enemies.length)];

// Update background and enemy label
                area1Background = newBackground;
                currentEnemy = newEnemy;
                enemyLabel.setText(newEnemy + " has appeared!");
                backgroundPanel.repaint();

            }
        });

        // Set background as the content pane
        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    public void direwolfFightScreen() {
        lastScreen = "Dire Wolf";
        currentEnemy = "Dire Wolf";
        setupFightScreen(direWolfBackground);
    }

    public void skeletonFightScreen() {
        lastScreen = "Skeleton";
        currentEnemy = "Skeleton";
        setupFightScreen(skeletonBackground);
    }

    public void goblinFightScreen() {
        lastScreen = "Goblin";
        currentEnemy = "Goblin";
        setupFightScreen(goblinBackground);
    }

    public void dragonFightScreen() {
        lastScreen = "Dragon";
        currentEnemy = "Dragon";
        setupFightScreen(dragonBackground);
    }

    private void setupFightScreen(Image backgroundImage) {
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());

        pauseButton = createHeaderButton("./resources/pause_icon.png", 40, 40, e -> System.out.println("Game Paused"));
        headerPanel.add(pauseButton, BorderLayout.WEST);

        setupInitialInteractionPanel();

        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(interactionPanel, BorderLayout.SOUTH);

        window.setContentPane(backgroundPanel);
        window.revalidate();
    }

    private void setupInitialInteractionPanel() {
        interactionPanel = new JPanel();
        interactionPanel.setOpaque(false);
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        interactionPanel.setPreferredSize(new Dimension(window.getWidth(), 300));

        // Update textLabel based on the current enemy
        textLabel = new JLabel(currentEnemy != null ? currentEnemy : "Enemy", SwingConstants.LEFT);
        textLabel.setOpaque(false);
        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        interactionPanel.add(textLabel, BorderLayout.NORTH);

        dialogueLabel = new JLabel("Prepare for battle!", SwingConstants.LEFT);
        dialogueLabel.setOpaque(true);
        dialogueLabel.setForeground(Color.WHITE);
        dialogueLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        dialogueLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialogueLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        dialogueLabel.setPreferredSize(new Dimension(window.getWidth() - 300, 40));
        dialogueLabel.setBackground(new Color(0, 0, 0, 200));

        interactionPanel.add(dialogueLabel, BorderLayout.WEST);

        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(4, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        Dimension choiceButtonSize = new Dimension(150, 50);

        attackButton = createChoiceButton("Attack", choiceButtonSize);
        skillButton = createChoiceButton("Use Skill", choiceButtonSize);
        itemButton = createChoiceButton("Use Item", choiceButtonSize);
        runButton = createChoiceButton("Run", choiceButtonSize);

        initializeAttackDetails();
        initializeSkillAndItemDetails();

        attackButton.addActionListener(e -> showAttackOptions());
        skillButton.addActionListener(e -> showSkillOptions());
        itemButton.addActionListener(e -> showItemOptions());

        // Update run button to go back to the appropriate fight screen
        runButton.addActionListener(e -> adventureScreen());

        buttonPanel.add(attackButton);
        buttonPanel.add(skillButton);
        buttonPanel.add(itemButton);
        buttonPanel.add(runButton);

        interactionPanel.add(buttonPanel, BorderLayout.EAST);
    }

    private void goToEnemyFightScreen() {
        if (lastScreen == null) {
            System.out.println("Error: lastScreen is null. Cannot navigate back.");
            JOptionPane.showMessageDialog(window, "No previous screen to go back to!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (lastScreen) {
            case "Dire Wolf":
                direwolfFightScreen();
                break;
            case "Skeleton":
                skeletonFightScreen();
                break;
            case "Goblin":
                goblinFightScreen();
                break;
            case "Dragon":
                dragonFightScreen();
                break;
            default:
                System.out.println("Error: Unknown last screen - " + lastScreen);
                JOptionPane.showMessageDialog(window, "Unknown screen: " + lastScreen, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeAttackDetails() {
        attackDetails = new HashMap<>();
        attackDetails.put("Attack 1", "<html><b>Basic Strike</b><br>Description: A swift strike.<br>Damage: 10<br>Effect: None</html>");
    }

    private void initializeSkillAndItemDetails() {
        skillDetails = new HashMap<>();
        skillDetails.put("Skill 1", "<html><b>Skill 1</b><br>Description: A healing spell.<br>Effect: Restores 20 HP</html>");
        skillDetails.put("Skill 2", "<html><b>Skill 2</b><br>Description: A fireball.<br>Damage: 30<br>Effect: Burn</html>");
        skillDetails.put("Skill 3", "<html><b>Skill 3</b><br>Description: A shield spell.<br>Effect: Blocks damage for 1 turn</html>");
        skillDetails.put("Skill 4", "<html><b>Skill 4</b><br>Description: A speed boost.<br>Effect: Increases agility</html>");

        itemDetails = new HashMap<>();
        itemDetails.put("Item 1", "<html><b>Item 1</b><br>Description: A healing potion.<br>Effect: Restores 50 HP</html>");
        itemDetails.put("Item 2", "<html><b>Item 2</b><br>Description: A mana potion.<br>Effect: Restores 30 MP</html>");
        itemDetails.put("Item 3", "<html><b>Item 3</b><br>Description: A smoke bomb.<br>Effect: Escape battle</html>");
        itemDetails.put("Item 4", "<html><b>Item 4</b><br>Description: A strength elixir.<br>Effect: Increases attack</html>");
    }

    // Method to show the attack options
    private void showAttackOptions() {
        textLabel.setText("Choose your attack!");
        dialogueLabel.setText("What will be your next move?");

        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10)); // Grid layout for 4 attacks + 1 back button

        String[] attacks = {"Attack 1", "Attack 2", "Attack 3", "Attack 4"};
        for (String attack : attacks) {
            JButton attackButton = createChoiceButton(attack, new Dimension(150, 30));
            attackButton.addActionListener(e -> updateAttackDetails(attack));
            buttonPanel.add(attackButton);
        }

        // Dynamic "Back" button
        JButton backButton = createChoiceButton("Back", new Dimension(150, 30));
        backButton.addActionListener(e -> goToEnemyFightScreen());
        buttonPanel.add(backButton);

        interactionPanel.revalidate();
        interactionPanel.repaint();
    }

    private void showSkillOptions() {
        textLabel.setText("Choose your skill!");
        dialogueLabel.setText("Select a skill to use.");

        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10)); // Grid layout for 4 skills + 1 back button

        String[] skills = {"Skill 1", "Skill 2", "Skill 3", "Skill 4"};
        for (String skill : skills) {
            JButton skillButton = createChoiceButton(skill, new Dimension(150, 30));
            skillButton.addActionListener(e -> updateSkillDetails(skill));
            buttonPanel.add(skillButton);
        }

        // Dynamic "Back" button
        JButton backButton = createChoiceButton("Back", new Dimension(150, 30));
        backButton.addActionListener(e -> goToEnemyFightScreen());
        buttonPanel.add(backButton);

        interactionPanel.revalidate();
        interactionPanel.repaint();
    }

    private void showItemOptions() {
        textLabel.setText("Choose an item!");
        dialogueLabel.setText("Select an item to use.");

        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10)); // Grid layout for 4 items + 1 back button

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
        for (String item : items) {
            JButton itemButton = createChoiceButton(item, new Dimension(150, 30));
            itemButton.addActionListener(e -> updateItemDetails(item));
            buttonPanel.add(itemButton);
        }

        // Dynamic "Back" button
        JButton backButton = createChoiceButton("Back", new Dimension(150, 30));
        backButton.addActionListener(e -> goToEnemyFightScreen());
        buttonPanel.add(backButton);

        interactionPanel.revalidate();
        interactionPanel.repaint();
    }

    // Method to update attack details dynamically
    private void updateAttackDetails(String attack) {
        // Get attack details
        String details = attackDetails.getOrDefault(attack, "No details available for this attack.");

        // Update the dialogue label
        dialogueLabel.setText(details);

        // Ensure proper repaint
        dialogueLabel.repaint();
        interactionPanel.revalidate();
        interactionPanel.repaint();
    }

    private void updateSkillDetails(String skill) {
        String details = skillDetails.getOrDefault(skill, "No details available for this skill.");
        dialogueLabel.setText(details);
        dialogueLabel.repaint();
        interactionPanel.revalidate();
        interactionPanel.repaint();
    }

    private void updateItemDetails(String item) {
        String details = itemDetails.getOrDefault(item, "No details available for this item.");
        dialogueLabel.setText(details);
        dialogueLabel.repaint();
        interactionPanel.revalidate();
        interactionPanel.repaint();
    }

    private void handleFight() {
        if (currentEnemy == null) {
            JOptionPane.showMessageDialog(window, "No enemy to fight!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Example fight logic
        switch (currentEnemy) {
            case "A Direwolf":
                direwolfFightScreen();
                break;
            case "A Skeleton":
                skeletonFightScreen();
                break;
            case "A Goblin":
                goblinFightScreen();
                break;
            case "A Dragon":
                dragonFightScreen();
                break;
            default:
                JOptionPane.showMessageDialog(window, "The fight ended in a draw!", "Fight Result", JOptionPane.INFORMATION_MESSAGE);
        }

        // Reset current enemy after the fight
        currentEnemy = null;
        enemyLabel.setText("");
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

        pointsPanel.add(acceptButton);
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

    public void characterScreen(PlayerCharacter player) {
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

        // Left panel for character details
        JPanel leftCharacterPanel = new JPanel();
        leftCharacterPanel.setLayout(new BoxLayout(leftCharacterPanel, BoxLayout.Y_AXIS));
        leftCharacterPanel.setBackground(Color.BLACK);
        leftCharacterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel characterInfoPanel = new JPanel();
        characterInfoPanel.setLayout(new GridLayout(4, 1));
        characterInfoPanel.setBackground(Color.BLACK);

        // Dynamically set the character's name
        JLabel nameLabel = new JLabel("Name: " + player.getName());
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(nameLabel);

        // Dynamically set the character's class
        JLabel classLabel = new JLabel("Class: " + player.getCharacterClass());
        classLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        classLabel.setForeground(Color.WHITE);
        classLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(classLabel);

        // Set health and mana based on the player's stats
        health_manaPanel = new JPanel();
        health_manaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        health_manaPanel.setBackground(Color.BLACK);
        health_manaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Dynamically set the health and mana
        healthLabel = new JLabel("HP: " + player.getStats().getHealth() + "/" + player.getMaxHealth());
        healthLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        healthLabel.setForeground(Color.WHITE);
        healthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        manaLabel = new JLabel("MP: " + player.getStats().getMana() + "/" + player.getMaxMana());
        manaLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        manaLabel.setForeground(Color.WHITE);
        manaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        health_manaPanel.add(healthLabel);
        health_manaPanel.add(manaLabel);

        characterInfoPanel.add(health_manaPanel);

        // Status effects
        JLabel statusEffectsLabel = new JLabel("Status Effects: Poison, Str Potion Buff");
        statusEffectsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statusEffectsLabel.setForeground(Color.WHITE);
        statusEffectsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(statusEffectsLabel);

        // Stats section
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(3, 1));
        statusPanel.setBackground(Color.BLACK);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));

        JLabel statsLabel = new JLabel("Stats");
        statsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsLabel.setForeground(Color.WHITE);  // Set font color to white

        statsPanel = new JPanel();
        statsPanel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        statsPanel.setLayout(new GridLayout(3, 3));

// Dynamically set stats based on PlayerCharacter's stats
        strLabel = new JLabel("Str: " + player.getStats().getStrength());
        strLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));  // Set font size and color
        strLabel.setForeground(Color.WHITE);  // Set font color to white

        intLabel = new JLabel("Int: " + player.getStats().getIntelligence());
        intLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        intLabel.setForeground(Color.WHITE);  // Set font color to white

        dexLabel = new JLabel("Dex: " + player.getStats().getDexterity());
        dexLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        dexLabel.setForeground(Color.WHITE);  // Set font color to white

        conLabel = new JLabel("Con: " + player.getStats().getConstitution());
        conLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        conLabel.setForeground(Color.WHITE);  // Set font color to white

        wisLabel = new JLabel("Wis: " + player.getStats().getWisdom());
        wisLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        wisLabel.setForeground(Color.WHITE);  // Set font color to white

        luckLabel = new JLabel("Luck: " + player.getStats().getLuck());
        luckLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        luckLabel.setForeground(Color.WHITE);  // Set font color to white

        agiLabel = new JLabel("Agi: " + player.getStats().getAgility());
        agiLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        agiLabel.setForeground(Color.WHITE);  // Set font color to white

// Add labels to statsPanel
        statsPanel.add(strLabel);
        statsPanel.add(intLabel);
        statsPanel.add(dexLabel);
        statsPanel.add(conLabel);
        statsPanel.add(wisLabel);
        statsPanel.add(luckLabel);
        statsPanel.add(agiLabel);

        statusPanel.add(statsLabel);
        statusPanel.add(statsPanel);

        // Right panel for quest list
        JPanel rightCharacterPanel = new JPanel();
        rightCharacterPanel.setLayout(new BorderLayout());
        rightCharacterPanel.setBackground(Color.BLACK);
        rightCharacterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        leftCharacterPanel.add(characterInfoPanel);
        leftCharacterPanel.add(Box.createVerticalStrut(10));
        leftCharacterPanel.add(statusPanel);

        // Adding the Equipped Items Section
        JPanel equippedPanel = new JPanel();
        equippedPanel.setLayout(new BoxLayout(equippedPanel, BoxLayout.Y_AXIS));
        equippedPanel.setBackground(Color.BLACK);
        equippedPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel equippedLabel = new JLabel("Equipped Items:");
        equippedLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        equippedLabel.setForeground(Color.WHITE);

        // Display equipped items
        JLabel headLabel = new JLabel("Head: " + (player.getEquipment("Head") != null ? player.getEquipment("Head").getName() : "None"));
        headLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        headLabel.setForeground(Color.WHITE);

        JLabel bodyLabel = new JLabel("Body: " + (player.getEquipment("Body") != null ? player.getEquipment("Body").getName() : "None"));
        bodyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        bodyLabel.setForeground(Color.WHITE);

        JLabel legsLabel = new JLabel("Legs: " + (player.getEquipment("Legs") != null ? player.getEquipment("Legs").getName() : "None"));
        legsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        legsLabel.setForeground(Color.WHITE);

        JLabel mainHandLabel = new JLabel("Main Hand: " + (player.getEquipment("Weapon") != null ? player.getEquipment("Weapon").getName() : "None"));
        mainHandLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        mainHandLabel.setForeground(Color.WHITE);

        JLabel offHandLabel = new JLabel("Off Hand: " + (player.getEquipment("OffHand") != null ? player.getEquipment("OffHand").getName() : "None"));
        offHandLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        offHandLabel.setForeground(Color.WHITE);


        // Add all the labels to the equipped panel
        equippedPanel.add(equippedLabel);
        equippedPanel.add(headLabel);
        equippedPanel.add(bodyLabel);
        equippedPanel.add(legsLabel);
        equippedPanel.add(mainHandLabel);
        equippedPanel.add(offHandLabel);

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

        // Add the Equipped panel to the right panel
        rightCharacterPanel.add(closeButtonPanel, BorderLayout.NORTH); // Add close button at the top
        rightCharacterPanel.add(equippedPanel, BorderLayout.CENTER);

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
        pauseButton.addActionListener(e -> pauseGame()); // Add your pause logic here

        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        /* mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton); */

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen(player)
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
//        headerPanel.add(areaLabel, BorderLayout.CENTER);
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
        pauseButton.addActionListener(e -> pauseGame()); // Add your pause logic here

        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        /* mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton); */

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen(player)
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
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
        sellButton = createChoiceButton("Sell Potions", choicebuttonSize);
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
        JLabel nameLabel = new JLabel("Potion Name");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(nameLabel);

        JLabel itemTypeLabel = new JLabel("Limitations/Potion Type");
        itemTypeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        itemTypeLabel.setForeground(Color.WHITE);
        itemTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(itemTypeLabel);

        JLabel descriptionLabel = new JLabel("Potion Description");
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

        JButton dropButton = createChoiceButton("Buy Potion", new Dimension(150, 30));

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

        JButton quest1Button = createChoiceButton("Potion Name 1", new Dimension(200, 30));
        JButton quest2Button = createChoiceButton("Potion Name 2", new Dimension(200, 30));
        JButton quest3Button = createChoiceButton("Potion Name 3", new Dimension(200, 30));

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

    public void sellPotionsScreen() {
        // Create the dialog
        JDialog characterDialog = new JDialog(window, "Sell Potions", true);
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
        JLabel nameLabel = new JLabel("Potion Name");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(nameLabel);

        JLabel itemTypeLabel = new JLabel("Limitations/Potion Type");
        itemTypeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        itemTypeLabel.setForeground(Color.WHITE);
        itemTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(itemTypeLabel);

        JLabel descriptionLabel = new JLabel("Potion Description");
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

        JButton dropButton = createChoiceButton("Sell Potion", new Dimension(150, 30));

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

        JButton quest1Button = createChoiceButton("Potion Name 1", new Dimension(200, 30));
        JButton quest2Button = createChoiceButton("Potion Name 2", new Dimension(200, 30));
        JButton quest3Button = createChoiceButton("Potion Name 3", new Dimension(200, 30));

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
        pauseButton.addActionListener(e -> pauseGame()); // Add your pause logic here


        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        /* mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton); */

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen(player)
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
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

        buyButton = createChoiceButton("Pray", choicebuttonSize);
        sellButton = createChoiceButton("Heal", choicebuttonSize);
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
        pauseButton.addActionListener(e -> pauseGame()); // Add your pause logic here

//
        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
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

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen(player)
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
//        headerPanel.add(areaLabel, BorderLayout.CENTER);
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

        buyButton = createChoiceButton("Buy Equipment", choicebuttonSize);
        sellButton = createChoiceButton("Sell Equipment", choicebuttonSize);
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
        pauseButton.addActionListener(e -> pauseGame()); // Add your pause logic here

        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

// Map Button
        /* mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton); */

// Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

// Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen(player)
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
//        headerPanel.add(areaLabel, BorderLayout.CENTER);
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
        innKeeperButton = createChoiceButton("Talk To Tavern Keeper", choicebuttonSize);
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

    public void tavernDialogueScreen() {
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
        pauseButton.addActionListener(e -> pauseGame()); // Add your pause logic here

        headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

        // Map Button
        /* mapButton = createHeaderButton(
                "./resources/map.png", 40, 40,
                e -> System.out.println("Map button clicked")
        );
        mapButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerButtons.add(mapButton); */

        // Bag Button
        bagButton = createHeaderButton(
                "./resources/Bag.png", 40, 40,
                e -> inventoryScreen()
        );
        headerButtons.add(bagButton);

        // Character Button
        characterButton = createHeaderButton(
                "./resources/Character.png", 40, 40,
                e -> characterScreen(player)
        );
        characterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        headerButtons.add(characterButton);

        headerPanel.add(pauseButton, BorderLayout.WEST);
        headerPanel.add(headerButtons, BorderLayout.EAST);

        interactionPanel = new JPanel();
        interactionPanel.setOpaque(false);
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Add margins around the panel
        interactionPanel.setPreferredSize(new Dimension(window.getWidth(), 300)); // Set a fixed height for the interaction panel

        // Text Label (Top Part of Interaction Panel)
        textLabel = new JLabel("Tavern Keeper", SwingConstants.LEFT);
        textLabel.setOpaque(false); // Make the background visible
        textLabel.setBackground(Color.BLACK); // Set background to black
        textLabel.setForeground(Color.WHITE); // Set text color to white for contrast
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Smaller font size
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the label
        interactionPanel.add(textLabel, BorderLayout.NORTH);

        dialogueLabel = new JLabel("Tavern Dialogue", SwingConstants.LEFT);
        dialogueLabel.setOpaque(true); // Make the background visible
        dialogueLabel.setForeground(Color.WHITE); // Set text color to white for contrast
        dialogueLabel.setBackground(Color.BLACK);
        dialogueLabel.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Smaller font size
        dialogueLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the label
        dialogueLabel.setPreferredSize(new Dimension(window.getWidth() - 300, 40)); // Set width to 100 less than window's width, height to 40

        // Set the background color with reduced opacity (RGBA color)
        dialogueLabel.setBackground(new Color(0, 0, 0, 200)); // RGBA where A is the alpha (opacity)


        interactionPanel.add(dialogueLabel, BorderLayout.WEST);

        // Button Panel (Bottom Part of Interaction Panel)
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(4, 1, 5, 5)); // Add spacing between buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the button panel

        // Adjust button size
        Dimension choicebuttonSize = new Dimension(150, 50); // Make buttons smaller to match the image

        choice1 = createChoiceButton("Choice 1", choicebuttonSize);
        choice2 = createChoiceButton("Choice 2", choicebuttonSize);
        choice3 = createChoiceButton("Choice 3", choicebuttonSize);
        JButton backtoTavernButton = createChoiceButton("Back", choicebuttonSize);
        backtoTavernButton.addActionListener(e -> tavernScreen());

        buttonPanel.add(choice1);
        buttonPanel.add(choice2);
        buttonPanel.add(choice3);
        buttonPanel.add(backtoTavernButton);

        interactionPanel.add(buttonPanel, BorderLayout.EAST);

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
        JButton acceptButton = createChoiceButton("Accept Quest", new Dimension(150, 30));

        questButtonPanel.add(acceptButton);
        questButtonPanel.add(dropButton);

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

    public void foodBoard() {
        // Create the dialog
        JDialog questDialog = new JDialog(window, "Food Board", true);
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
        JLabel questNameLabel = new JLabel("Food Name");
        questNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        questNameLabel.setForeground(Color.WHITE);
        questNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questDetails.add(questNameLabel);

        JLabel difficultyLabel = new JLabel("Food Rarity");
        difficultyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        difficultyLabel.setForeground(Color.WHITE);
        difficultyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questDetails.add(difficultyLabel);

        JLabel descriptionLabel = new JLabel("<html>Food Description</html>");
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questDetails.add(descriptionLabel);

        JLabel rewardsLabel = new JLabel("Effects");
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

        JButton dropButton = createChoiceButton("Buy", new Dimension(150, 30));
        JButton acceptButton = createChoiceButton("Buy and Eat", new Dimension(150, 30));

        questButtonPanel.add(acceptButton);
        questButtonPanel.add(dropButton);

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

        JButton quest1Button = createChoiceButton("Food Name 1", new Dimension(200, 30));
        JButton quest2Button = createChoiceButton("Food Name 2", new Dimension(200, 30));
        JButton quest3Button = createChoiceButton("Food Name 3", new Dimension(200, 30));

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

    public void buyEquipmentShopScreen() {
        // Create the dialog
        JDialog characterDialog = new JDialog(window, "Equipment", true);
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
        JLabel nameLabel = new JLabel("Equipment Name");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(nameLabel);

        JLabel itemTypeLabel = new JLabel("Limitations/Equipment Type");
        itemTypeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        itemTypeLabel.setForeground(Color.WHITE);
        itemTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(itemTypeLabel);

        JLabel descriptionLabel = new JLabel("Equipment Description");
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

        JLabel costLabel = new JLabel("Equipment Cost: $10000000");
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

        JButton dropButton = createChoiceButton("Buy Equipment", new Dimension(150, 30));
        JButton acceptButton = createChoiceButton("Sell Equipment", new Dimension(150, 30));

        pointsPanel.add(dropButton);
        //pointsPanel.add(acceptButton);

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

        JButton quest1Button = createChoiceButton("Equipment Name 1", new Dimension(200, 30));
        JButton quest2Button = createChoiceButton("Equipment Name 2", new Dimension(200, 30));
        JButton quest3Button = createChoiceButton("Equipment Name 3", new Dimension(200, 30));

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

    public void sellEquipmentShopScreen() {
        // Create the dialog
        JDialog characterDialog = new JDialog(window, "Equipment", true);
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
        JLabel nameLabel = new JLabel("Equipment Name");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(nameLabel);

        JLabel itemTypeLabel = new JLabel("Limitations/Equipment Type");
        itemTypeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        itemTypeLabel.setForeground(Color.WHITE);
        itemTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        characterInfoPanel.add(itemTypeLabel);

        JLabel descriptionLabel = new JLabel("Equipment Description");
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

        JLabel costLabel = new JLabel("Equipment Cost: $10000000");
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

        JButton dropButton = createChoiceButton("Buy Equipment", new Dimension(150, 30));
        JButton acceptButton = createChoiceButton("Sell Equipment", new Dimension(150, 30));

        //pointsPanel.add(dropButton);
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

        JButton quest1Button = createChoiceButton("Equipment Name 1", new Dimension(200, 30));
        JButton quest2Button = createChoiceButton("Equipment Name 2", new Dimension(200, 30));
        JButton quest3Button = createChoiceButton("Equipment Name 3", new Dimension(200, 30));

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


    public void chestScreen() {
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

        JButton dropButton = createChoiceButton("Withdraw Item", new Dimension(150, 30));
        JButton acceptButton = createChoiceButton("Deposit Item", new Dimension(150, 30));

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
            case "Town":
                mainArea();
                break;
            case "Back to Town":
                mainArea();
                break;
            case "Adventure":
                adventureScreen();
                break;
            case "Notice Board":
                questBoard();
                break;
            case "Clinic":
                clinicScreen();
                break;
            case "Buy Potions":
                buyPotionsScreen();
                break;
            case "Sell Potions":
                sellPotionsScreen();
                break;
            case "Chapel":
                chapelScreen();
                break;
            case "Merchant":
                merchantScreen();
                break;
            case "Buy Equipment":
                buyEquipmentShopScreen();
                break;
            case "Sell Equipment":
                sellEquipmentShopScreen();
                break;
            case "Tavern":
                tavernScreen();
                break;
            case "Chest":
                chestScreen();
                break;
            case "Eat":
                foodBoard();
                break;
            case "Talk To Tavern Keeper":
                tavernDialogueScreen();
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
            if (!isTyping) {
                JButton source = (JButton) e.getSource();
                String choiceText = source.getText();

                // Handle class selection based on the choice text
                if (choiceText.contains("Warrior")) {
                    player.finalizeCharacter("Warrior"); // Finalize as Warrior
                } else if (choiceText.contains("Mage")) {
                    player.finalizeCharacter("Mage"); // Finalize as Mage
                } else if (choiceText.contains("Ranger")) {
                    player.finalizeCharacter("Ranger"); // Finalize as Ranger
                } else if (choiceText.contains("Assassin")) {
                    player.finalizeCharacter("Assassin"); // Finalize as Assassin
                } else if (choiceText.contains("Monk")) {
                    player.finalizeCharacter("Monk"); // Finalize as Monk
                } else if (choiceText.equals("Escape")) {
                    mainArea(); // Handle escape choice
                }

                game.handleChoice(choiceText);
                // Print class info after selection
                System.out.println("Class set to: " + player.getCharacterClass());
            }
        }
    }

}
