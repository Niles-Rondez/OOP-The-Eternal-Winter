package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Game {
    JFrame window;
    Container con;
    //panels are basically divs
    JPanel titlePanel, startButtonPanel, mainTextPanel, choicePanel;
    JLabel titleLabel;
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    Font textFont = new Font("Times New Roman", Font.PLAIN, 20);
    titleScreenHandler titleScreenHandler = new titleScreenHandler();

    String text;
    int i;

    public Font getButtonFont() {
        return buttonFont;
    }

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        //initialize window
        window = new JFrame();
        window.setSize(800, 600);
        window.setMinimumSize(new Dimension(800, 600));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        con = window.getContentPane();

        //initialize main menu
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.black);
        titlePanel.setBounds(50, 100, 700, 150);
        titleLabel = new JLabel("THE ETERNAL WINTER");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);


        //Start button
        startButtonPanel = new JPanel();
        startButtonPanel.setBackground(Color.BLACK);
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(buttonFont);
        startButton.addActionListener(titleScreenHandler);

        titlePanel.add(titleLabel);
        startButtonPanel.add(startButton);

        //Display panels
        con.add(titlePanel);
        con.add(startButtonPanel);
        window.setVisible(true);
    }

    public void createGameScreen(){
        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setBounds(100, 100, 600, 250);
        con.add(mainTextPanel);

        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        mainTextArea = new JTextArea("Niles: "); // should probably separate name and text
        mainTextArea.setEditable(false);
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        choicePanel = new JPanel();
        choicePanel.setBackground(Color.black);
        choicePanel.setBounds(50, 350, 700, 150);
        con.add(choicePanel);
        choice1 = new JButton("CHOICE 1");
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(Color.WHITE);
        choice1.setFont(buttonFont);
        choice2 = new JButton("CHOICE 2");
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(Color.WHITE);
        choice2.setFont(buttonFont);
        choice3 = new JButton("CHOICE 3");
        choice3.setBackground(Color.BLACK);
        choice3.setForeground(Color.WHITE);
        choice3.setFont(buttonFont);
        choice4 = new JButton("CHOICE 4");
        choice4.setBackground(Color.BLACK);
        choice4.setForeground(Color.WHITE);
        choice4.setFont(buttonFont);
        choicePanel.add(choice1);
        choicePanel.add(choice2);
        choicePanel.add(choice3);
        choicePanel.add(choice4);
        timer.start();
    }

    public class titleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createGameScreen();
        }
    }

    Timer timer = new Timer(50, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            char c[] = text.toCharArray();
            int arrayNumber = c.length;

            String addedCharacter = "";
            String blank = "";

            addedCharacter = blank + c[i];
            mainTextArea.append(addedCharacter);
            i++;

            if(i == arrayNumber){
                i = 0;
                timer.stop();
            }
        }
    });
}