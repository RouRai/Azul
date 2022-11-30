package panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import game.Constants;
import game.*;
import logic.*;
import java.util.*;
import datastructures.*;
public class MainPanel extends JPanel implements ActionListener{
    private CardLayout cl;
    private BufferedImage background, factory, logo, redTile, yellowTile, whiteTile, blueTile, blackTile, oneTile, gameBoard;
    private ArrayList<Player> players;
    private int bW, tW, stW;
    private JButton backToPlayerScreen;
    private datastructures.LinkedList<TileObject> lid1, lid2, lid3, lid4;

    public MainPanel(CardLayout cl){
        this.cl = cl;
        players = new ArrayList<>();
        lid1 = new datastructures.LinkedList<>();
        lid2 = new datastructures.LinkedList<>();
        lid3 = new datastructures.LinkedList<>();
        lid4 = new datastructures.LinkedList<>();
        /*players.add(new Player("Player 1", lid1));
        players.add(new Player("Player 2", lid2));
        players.add(new Player("Player 3", lid3));
        players.add(new Player("Player 4", lid4));*/
        setUpImages();
        setUpButtons();
    }
    public void paintComponent(Graphics g){
        bW = (int)(getWidth() / 4);
        tW = bW / 11;
        stW = (int)(bW / (573 / 25));
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        drawBoard(g);
        g.setFont(new Font("Italics", Font.ITALIC, 40));
        drawNames(g);
        g.drawImage(logo, getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2, null);
        drawp1Score(g);
        drawp2Score(g);
        drawp4Score(g);
    }
    private void drawp1Score(Graphics g){
        int s = players.get(0).getScore();
        g.setColor(Color.BLACK);
        int k;
        g.fillRect(getWidth() / 90 + (19 *stW) + (19 * getWidth() / 9500), (int)(getHeight() / 41), stW, stW);
        if(s == 0){
            g.fillRect(getWidth() / 90, 0, stW, stW);
        } else if (s % 100 == 0){
            k = 19;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 7.6), stW, stW);
        } else if (s % 100 >= 81){
            k = (s % 100) - 81;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 7.6), stW, stW);
        } else if (s % 100 >= 61){
            k = (s % 100) - 61;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 10), stW, stW);
        } else if( s % 100 >= 41){
            k = (s % 100) - 41;
            g.fillRect(getWidth() / 90 + (19 *stW) + (19 * getWidth() / 9500), (int)(getHeight() / 20), stW, stW);
        } else if( s % 100 >= 1){
            k = (s % 100) - 1;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 41), stW, stW);
        }
        g.fillRect(getWidth() / 90 + (19 *stW) + (19 * getWidth() / 9500), (int)(getHeight() / 41), stW, stW);
    }
    private void drawp2Score(Graphics g){
        int s = players.get(1).getScore();
        g.setColor(Color.BLACK);
        int k;
        g.fillRect((int)(getWidth() / 1.83) + (19 *stW) + (19 * getWidth() / 9500), 0, stW, stW);
    }
    private void drawp4Score(Graphics g){
        int s = players.get(3).getScore();
        g.setColor(Color.BLACK);
        int k;
        if(s == 0){
            g.fillRect(getWidth() / 90, getHeight() / 2, stW, stW);
        } else if(s % 100 == 0){
            k = 19;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 1.58), stW, stW);
        } else if(s % 100 >= 81){
            k = (s % 100) - 81;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 1.58), stW, stW);
        } else if (s % 100 >= 61){
            k = (s % 100) - 61;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 1.66), stW, stW);
        } else if(s % 100 >= 41){
            k = (s % 100) - 41;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 1.74), stW, stW);
        } else if (s % 100 >= 21){
            k = (s % 100) - 21;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 1.81), stW, stW);
        } else if (s % 100 >= 1){
            k = (s % 100) - 1;
            g.fillRect(getWidth() / 90 + (k *stW) + (k * getWidth() / 9500), (int)(getHeight() / 1.9), stW, stW);
        }
    }
    private void drawNames(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString(players.get(0).getName(), (int)(getWidth() / 3.8), getHeight() / 20);
        g.drawString(players.get(1).getName(), (int)(getWidth() / 1.55), getHeight() / 20);
        g.drawString(players.get(2).getName(), (int)(getWidth() / 1.55), (int)(getHeight() / 1.05));
        g.drawString(players.get(3).getName(), (int)(getWidth() / 3.8), (int)(getHeight() / 1.05));
    }
    private void drawBoard(Graphics g){
        g.drawImage(gameBoard, 0, 0, bW, bW, null);
        g.drawImage(gameBoard, 0, getHeight() / 2, bW, bW, null);
        g.drawImage(gameBoard, getWidth() - bW, 0, bW, bW, null);
        g.drawImage(gameBoard, getWidth() - bW, getHeight() / 2, bW, bW, null);
    }
    private void setUpImages() {
        background = Constants.getImage("Background");
        factory = Constants.getImage("Factory");
        logo = Constants.getImage("AzulLogo");
        redTile = Constants.getImage("AzulTileRed");
        blackTile = Constants.getImage("AzulTileBlack");
        whiteTile = Constants.getImage("AzulTileWhite");
        blueTile = Constants.getImage("AzulTileBlue");
        yellowTile = Constants.getImage("AzulTileYellow");
        oneTile = Constants.getImage("AzulTileOne");
        background = Constants.getImage("Background");
        gameBoard = Constants.getImage("AzulBoard");
        factory = Constants.getImage("Factory");
    }
    private void setUpButtons(){
        backToPlayerScreen = new JButton("Return");
        super.add(backToPlayerScreen);
        backToPlayerScreen.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(backToPlayerScreen)){
            //cl.show(Constants.PANEL_CONT, Constants.PLAYER_PANEL);
        }
    }
}
