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
    choiceButtonHandler choiceButtonHandler = new choiceButtonHandler();

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
        startButton.setFocusPainted(false);

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

        //text = "text"
        mainTextArea = new JTextArea(); // should probably separate name and text
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
        con.add(choicePanel);

        choice1 = new JButton("CHOICE 1");
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(Color.WHITE);
        choice1.setFont(buttonFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceButtonHandler);
        choice1.setActionCommand("choice1");

        choice2 = new JButton("CHOICE 2");
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(Color.WHITE);
        choice2.setFont(buttonFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceButtonHandler);
        choice2.setActionCommand("choice2");

        choice3 = new JButton("CHOICE 3");
        choice3.setBackground(Color.BLACK);
        choice3.setForeground(Color.WHITE);
        choice3.setFont(buttonFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceButtonHandler);
        choice3.setActionCommand("choice3");

        choice4 = new JButton("CHOICE 4");
        choice4.setBackground(Color.BLACK);
        choice4.setForeground(Color.WHITE);
        choice4.setFont(buttonFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceButtonHandler);
        choice4.setActionCommand("choice4");

        choicePanel.add(choice1);
        choicePanel.add(choice2);
        choicePanel.add(choice3);
        choicePanel.add(choice4);
        tutorial();
    }

    public class titleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createGameScreen();
        }
    }

    public class choiceButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            switch (position) {
                case "tutorial":
                    switch (choice){
                        case "choice1":
                            tutorial2();
                            break;
                    }
                    break;
                case "tutorial2":
                    switch (choice){
                        case "choice1":
                            tutorial();
                            break;
                    }
                    break;
            }
        }
    }

    /* Typewriter effect, will implement

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
    });*/

    String position;
    public void tutorial(){
        position="tutorial"; // current location tracker
        mainTextArea.setText("This is the start of the Tutorial");
        choice1.setText(">");
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }
    public void tutorial2(){
        position="tutorial2";
        mainTextArea.setText("Storyline blahblahblah then i ask for your name and then I ask for you class");
        choice1.setText(">");
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }
}