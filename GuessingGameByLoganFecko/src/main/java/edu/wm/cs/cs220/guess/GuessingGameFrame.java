package edu.wm.cs.cs220.guess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GuessingGameFrame extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    private int answer;
    private int low;
    private int high;
    private int numGuess;

    private JLabel currentLimits;
    private JLabel hlGuess;
    private JLabel lhGuess;

    private JLabel numGuesses;
    private JLabel lastGuess;
    private JLabel guesses;
    private JLabel guess;
    private JPanel mLeft;
    private JPanel mRight;

    private JLabel enterGuess;
    private JTextField guessField;
    private JButton optimalGuess;
    private JPanel button;

    private JPanel top;
    private JPanel middle;
    private JPanel bottom;

    public GuessingGameFrame() {
        Random random = new Random();
        this.answer = random.nextInt(101);
        this.low = 0;
        this.high = 101;
        this.numGuess = 0;

        this.setTitle("Guessing Game");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        currentLimits = new JLabel("Current Limits:");
        hlGuess = new JLabel("      Highest Low Guess: N/A");
        lhGuess = new JLabel("      Lowest High Guess: N/A");

        top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(currentLimits);
        top.add(hlGuess);
        top.add(lhGuess);

        numGuesses = new JLabel("Num Guesses:");
        lastGuess = new JLabel("        Last Guess:");
        guesses = new JLabel("0");
        guess = new JLabel("      No Guess Yet");

        middle = new JPanel();
        middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));
        mLeft = new JPanel();
        mLeft.setLayout(new GridLayout(2,1,0,0));
        mRight = new JPanel();
        mRight.setLayout(new GridLayout(2,1,0,0));
        mLeft.add(numGuesses);
        numGuesses.setHorizontalAlignment(SwingConstants.CENTER);
        mRight.add(lastGuess);
        lastGuess.setHorizontalAlignment(SwingConstants.LEFT);
        mLeft.add(guesses);
        guesses.setHorizontalAlignment(SwingConstants.CENTER);
        mRight.add(guess);
        guess.setHorizontalAlignment(SwingConstants.LEFT);
        middle.add(mLeft);
        middle.add(mRight);

        enterGuess = new JLabel("Enter Guess: ");
        guessField = new JTextField();
        guessField.addActionListener(new GuessActionListener());
        optimalGuess = new JButton("Optimal Guess");
        optimalGuess.addActionListener(new OptimalActionListener());

        bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.add(enterGuess, BorderLayout.WEST);
        bottom.add(guessField, BorderLayout.CENTER);
        button = new JPanel();
        button.add(optimalGuess);
        bottom.add(button, BorderLayout.SOUTH);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(top, BorderLayout.NORTH);
        contentPane.add(middle, BorderLayout.CENTER);
        contentPane.add(bottom, BorderLayout.SOUTH);

        setFonts();
        setColors();
        setMiscVisuals();
        this.pack();
    }

    private void setFonts() {
        Font plainSmall = new Font("BasicFontSmall", Font.PLAIN, 14);
        Font plainBig = new Font("BasicFontBig", Font.PLAIN, 20);
        Font topGuesses = new Font("SmallerMain", Font.PLAIN, 20);
        Font middleGuesses = new Font("LargerMain", Font.PLAIN, 28);

        currentLimits.setFont(plainSmall);
        hlGuess.setFont(topGuesses);
        lhGuess.setFont((topGuesses));
        enterGuess.setFont(plainBig);
        guessField.setFont(plainBig);
        guesses.setFont(middleGuesses);
        guess.setFont(middleGuesses);
    }

    private void setColors(){
        top.setBackground(new Color(223, 223, 223));
        bottom.setBackground(new Color(223, 223, 223));
        button.setBackground(new Color(223, 223, 223));
        hlGuess.setForeground(new Color(4, 0, 138));
        lhGuess.setForeground(new Color(4, 0, 138));
        guesses.setForeground(new Color(4,0,138));
        guess.setForeground(new Color(4,0,138));
    }

    private void setMiscVisuals() {
        top.setBorder(new EmptyBorder(20, 20, 20, 20));
        middle.setBorder(new EmptyBorder(20, 20, 20, 20));
        bottom.setBorder(new EmptyBorder(20,20,20,20));
        guessField.setHorizontalAlignment((SwingConstants.CENTER));
    }

    public class GuessActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                String text = guessField.getText();
                int curGuess = Integer.parseInt(text);
                UpdateGuessingGameFrame(curGuess);
            } catch (NumberFormatException nfe){}
            guessField.setText("");
        }
    }

    public class OptimalActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            int curGuess = (low+high)/2;
            UpdateGuessingGameFrame(curGuess);
        }
    }

    private void UpdateGuessingGameFrame (int curGuess){
        if (curGuess < 0 | curGuess > 100){
            return;
        } else {
            numGuess ++;
            guesses.setText(Integer.toString(numGuess));
            if (curGuess == answer) {
                guess.setText("      " + curGuess + ": WINNER!");
                guess.setForeground(new Color(8, 191, 0));
                endPopup();
            }
            else if (curGuess < answer){
                guess.setText("      " + curGuess + ": Too Low");
                guess.setForeground(new Color(177, 31, 31));
                if (curGuess > low) {
                    low = curGuess;
                    hlGuess.setText("      Lowest High Guess: " + curGuess);
                }
            }
            else {
                guess.setText("      " + curGuess + ": Too High");
                guess.setForeground(new Color(177, 31, 31));
                if (curGuess < high) {
                    high = curGuess;
                    lhGuess.setText("      Highest Low Guess: " + curGuess);
                }
            }
        }
    }

    private void endPopup(){
        String[] options = {"No, thank you", "Yes!"};
        int choice = JOptionPane.showOptionDialog(GuessingGameFrame.this,
                "Congratulations! You guessed my number in " + numGuess + " guesses." +
                "\n \n Would you like to play again?",
                "Play Again?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (choice == JOptionPane.YES_OPTION){
            System.exit(0);
        } else if (choice == JOptionPane.NO_OPTION){
            GuessingGameFrame.this.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GuessingGameFrame main = new GuessingGameFrame();
                    main.setVisible(true);
                }
            });
        }
    }
}
