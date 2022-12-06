package panels;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

import datastructures.*;
import game.Constants;
import logic.*;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;

public class WinnerPanel extends JPanel implements ActionListener{

    private BufferedImage background, trophy;
    private JButton returnStart;
    private LinkedList<Player> players; // Will be used when we effectively implement players in our game
    private CardLayout cl;
    public WinnerPanel(CardLayout c, LinkedList<Player> players) {
        cl = c;
        returnStart = new JButton("Return to Start");
        super.add(returnStart);
        returnStart.addActionListener(this);
        this.players = players;
        background = Constants.getImage("EndScreen");
        trophy = Constants.getImage("Trophy");
    }
    public void initializeRButtons() {
        
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
        //g.setColor(Color.yellow);
        g.drawString("Leaderboard", (getWidth()/8), getHeight()/16);
        // PlayerTemp thing1 = new PlayerTemp("ez1", null, null);
        // PlayerTemp thing2 = new PlayerTemp("ez2", null, null);
        // PlayerTemp thing3 = new PlayerTemp("ez3", null, null);
        // PlayerTemp thing4 = new PlayerTemp("ez4", null, null);
        // thing1.setScoreOR(1);
        // thing2.setScoreOR(555);
        // thing3.setScoreOR(1);
        // thing4.setScoreOR(3);
        // LinkedList<PlayerTemp> ez = new LinkedList();
        // ez.add(thing1);
        // ez.add(thing2);
        // ez.add(thing3);
        // ez.add(thing4);
        // ez.add(new PlayerTemp("ez", null, null));
        ArrayList<Player> list= getPlacement(players);
        g.drawString(list.get(0).getName() + " - " + list.get(0).getScore(), (getWidth()/8), getHeight()/8);
        g.drawString(list.get(1).getName() + " - " + list.get(1).getScore(), (getWidth()/8), getHeight()/6);
        g.drawString(list.get(2).getName() + " - " + list.get(2).getScore(), (getWidth()/8), (int)(getHeight()/4.75));
        g.drawString(list.get(3).getName() + " - " + list.get(3).getScore(), (getWidth()/8), (int)(getHeight()/3.95));

    }

    // Returns the top player in terms of points
    private String getTop(LinkedList<Player> players) {
        Player top = null;

        for(Player p : players) {
            if(top.compareTo(p) == -1) {
                top = p;
            }
        }

        return top.getName();
    }
    private ArrayList<Player> getPlacement(LinkedList<Player> players) {
        ArrayList<Player> ret = new ArrayList<>();
        for (Player p: players) {
            if (ret.size() == 0) {
                ret.add(p);
            } else {
                int idx = 0;
                for (int i = 0; i < ret.size(); i++) {
                    if (p.getScore() < ret.get(i).getScore()) {
                        idx++;
                    }
                }
                ret.add(idx, p);
            }
            
        }
        return ret;
    }
}
