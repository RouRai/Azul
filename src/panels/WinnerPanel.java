package panels;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

import datastructures.Box;
import game.Constants;
import game.Player;

public class WinnerPanel extends JPanel{

    private BufferedImage background, trophy;
    private Box<Player> players; // Will be used when we effectively implement players in our game

    public WinnerPanel(CardLayout cl, Box<Player> players) {
        this.players = players;
        background = Constants.getImage("EndScreen");
        trophy = Constants.getImage("Trophy");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws the background
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        // Draws the trophy with the name of the winner. ("Player 1 Won!" was a placeholder for testing, we will)
        // (pass in a Box of players as a parameter and then allow the method getTop to find the top player and get their name)
        g.drawImage(trophy, (getWidth()/2)-(trophy.getWidth()/2), getHeight()/32, trophy.getWidth(), trophy.getHeight(), this);
        g.drawString("Player 1 Won!", getWidth()/2-getWidth()/32, getHeight()/10);
    }

    // Returns the top player in terms of points
    private Player getTop(Box<Player> players) {
        Player top = null;

        for(Player p : players) {
            if(top.compareTo(p) == -1) {
                top = p;
            }
        }

        return top;
    }
}
