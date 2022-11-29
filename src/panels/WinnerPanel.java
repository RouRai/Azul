package panels;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

import datastructures.Box;
import game.Constants;
import game.Player;

public class WinnerPanel extends JPanel implements ActionListener{

    private BufferedImage background, trophy;
    private JButton returnStart, row1, row2, row3, row4, row5;
    private Box<Player> players; // Will be used when we effectively implement players in our game
    private CardLayout cl;
    public WinnerPanel(CardLayout c, Box<Player> players) {
        cl = c;
        returnStart = new JButton("Return to Start");
        super.add(returnStart);
        returnStart.addActionListener(this);
        this.players = players;
        background = Constants.getImage("EndScreen");
        trophy = Constants.getImage("Trophy");
    }
    public void initializeRButtons() {
        row1 = new JButton("Pick row 1");
        row2 = new JButton("Pick row 2");
        row3 = new JButton("Pick row 3");
        row4 = new JButton("Pick row 4");
        row5 = new JButton("Pick row 5");
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(returnStart)){
            cl.show(Constants.PANEL_CONT, Constants.START_PANEL);
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws the background
        g.setColor(new Color(255, 223, 0));
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.setFont(new Font("Italics", Font.ITALIC, 40));
        // Draws the trophy with the name of the winner. ("Player 1 Won!" was a placeholder for testing, we will)
        // (pass in a Box of players as a parameter and then allow the method getTop to find the top player and get their name)
        returnStart.setBounds((int)(getWidth() / 2)-getWidth()/12, (int)(getHeight() / 1.2), getWidth()/6 , getHeight() / 14);
        g.drawImage(trophy, (getWidth()/2)-(trophy.getWidth()/2), getHeight()/32, trophy.getWidth(), trophy.getHeight(), this);
        g.drawString(/*getTop(players) + */"Player 1 Wins!", (int)(getWidth()/2.4), getHeight()/10);
    }

    // Returns the top player in terms of points
    private String getTop(Box<Player> players) {
        Player top = null;

        for(Player p : players) {
            if(top.compareTo(p) == -1) {
                top = p;
            }
        }

        return top.getName();
    }
}
