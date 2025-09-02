package edu.wm.cs.cs220.guess;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                GuessingGameFrame main = new GuessingGameFrame();
                main.setVisible(true);
                JOptionPane.showMessageDialog(main, "<html>"
                        +"<h1 style='font-size:18px;' >Guessing Game</h1>"
                        + "<p> The rules are simple. I'm thinking of a number between 1 and 100, and you have to guess what it is. <br><br>"
                        + "<p>Each time you guess, I'll give you a hint by telling you if your guess was too high or too low. <br><br>"
                        + "If you're not sure what to guess, I've given you a button to push. It will pick the best value given your previous guesses.<br>"
                        + "That's it. Good luck!" + "</html>", "Guessing Game Instructions", JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }
}