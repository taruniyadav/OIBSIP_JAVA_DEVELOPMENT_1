import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingApp extends JFrame {

    private int numberToGuess;
    private int attempts;
    private int maxAttempts = 5;
    private int score = 0;
    private int round = 1;
    private final int maxRounds = 3;

    private JLabel instructionLabel, feedbackLabel, scoreLabel, roundLabel;
    private JTextField guessField;
    private JButton guessButton;

    public NumberGuessingApp() {
        setTitle(" Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        instructionLabel = new JLabel("Enter a number between 1 and 100:");
        feedbackLabel = new JLabel(" ");
        scoreLabel = new JLabel("Score: 0");
        roundLabel = new JLabel("Round: 1");

        guessField = new JTextField();
        guessButton = new JButton("Submit Guess");

        add(roundLabel);
        add(instructionLabel);
        add(guessField);
        add(guessButton);
        add(feedbackLabel);
        add(scoreLabel);

        guessButton.addActionListener(new GuessHandler());

        generateNumber();
        setVisible(true);
    }

    private void generateNumber() {
        Random rand = new Random();
        numberToGuess = rand.nextInt(100) + 1;
        attempts = 0;
    }

    private class GuessHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = guessField.getText();
            try {
                int guess = Integer.parseInt(input);
                attempts++;

                if (guess == numberToGuess) {
                    feedbackLabel.setText(" Correct in " + attempts + " attempts!");
                    score += (10 - attempts * 2);
                    scoreLabel.setText("Score: " + score);
                    nextRound();
                } else if (guess < numberToGuess) {
                    feedbackLabel.setText("Too Low! Try again.");
                } else {
                    feedbackLabel.setText("Too High! Try again.");
                }

                if (attempts >= maxAttempts && guess != numberToGuess) {
                    feedbackLabel.setText(" Out of attempts! Number was: " + numberToGuess);
                    nextRound();
                }

            } catch (NumberFormatException ex) {
                feedbackLabel.setText(" Please enter a valid number!");
            }
        }
    }

    private void nextRound() {
        round++;
        if (round > maxRounds) {
            JOptionPane.showMessageDialog(this, "Game Over! Final Score: " + score);
            System.exit(0);
        } else {
            roundLabel.setText("Round: " + round);
            generateNumber();
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        new NumberGuessingApp();
    }
}

