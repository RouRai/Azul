package panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import game.Constants;
import game.*;
import tiles.*;
import java.util.*;
public class MainPanel extends JPanel implements ActionListener{
    private CardLayout cl;
    private BufferedImage background, factory, logo, redTile, yellowTile, whiteTile, blueTile, blackTile, oneTile, gameBoard;
    private ArrayList<Player> players;
    private int bW, tW, stW;
    private JButton backToPlayerScreen;
    public MainPanel(CardLayout cl){
        this.cl = cl;
        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));
        players.add(new Player("Player 4"));
        setUpImages();
        setUpButtons();
        
    }
    public void paintComponent(Graphics g){
        bW = (int)(getWidth() / 4);
        tW = bW / 11;
        stW = (int)(bW / 21.25);
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        drawBoard(g);
        g.setFont(new Font("Italics", Font.ITALIC, 40));
        drawNames(g);
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
            cl.show(Constants.PANEL_CONT, Constants.PLAYER_PANEL);
        }
    }
}
