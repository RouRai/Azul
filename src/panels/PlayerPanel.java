package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import game.*;
import datastructures.LinkedList;

public class PlayerPanel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton continueButton, logs, row1, row2, row3, r4, row5, penalty;
    private BufferedImage background, gameBoard, factory, blackT, blueT, oneT, redT, yellowT, whiteT;
    private boolean choseTile, placeTile, endTurn, scoreR1, scoreR2, scoreR3, scoreR4, scoreR5, scoreP, nextS;
    private Player p;
    public PlayerPanel(CardLayout cl) {
        this.cl = cl;
        p = new Player("Player 1");
        background = Constants.getImage("Background");
        setUpButtons();
        setUpImages();
    }

    private void setUpImages() {
        background = Constants.getImage("Background");
        gameBoard = Constants.getImage("AzulBoard");
        factory = Constants.getImage("Factory");
        blackT = Constants.getImage("AzulTileBlack");
        blueT = Constants.getImage("AzulTileBlue");
        oneT = Constants.getImage("AzulTileOne");
        redT = Constants.getImage("AzulTileRed");
        yellowT = Constants.getImage("AzulTileYellow");
        whiteT = Constants.getImage("AzulTileWhite");
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(gameBoard, 0, 0, (int)(getWidth() / 3), (int)(getWidth() / 3), null);
        continueButton.setBounds(getWidth()/22, (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
        logs.setBounds((int)(getWidth() / 1.3), (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
    }

    private void setUpButtons() {
        // Instantiates JButtons
        continueButton = new JButton("Continue");
        logs = new JButton("Logs");
        // Adds JButtons to the panels
        super.add(continueButton);
        super.add(logs);
        // Adds action listeners to the JButtons

        continueButton.addActionListener(this);
        logs.addActionListener(this);
    }

    // Runs this method when something happens to one of the JButtons
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(continueButton)){
            checkState();
        }
    }
    public void chose(){
        choseTile =! choseTile;
    }
    private void reset(){
        choseTile = false;
        placeTile = false;
        endTurn = false;
        scoreR1 = false;
        scoreR2 = false;
        scoreR3 = false;
        scoreR4 = false;
        scoreR5 = false;
        scoreP = false;
        nextS = false;
    }
    private void checkState(){
        if(!choseTile){
            cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
            return;
        }
        if(!placeTile){
            placeTile = !placeTile;
            return;
        }
        if(!endTurn){
           endTurn = !endTurn;
            return;
        }
        if(true){ //Needs to be changed
            if(!scoreR1){
                
            scoreR1 = !scoreR1;
            return;
            }
            if(!scoreR2){
                scoreR2 = !scoreR2;
                return;
            }
            if(!scoreR3){
                scoreR3 = !scoreR3;
                return;
            }
            if(!scoreR4){
                scoreR4 = !scoreR4;
                return;
            }
            if(!scoreR5){
                scoreR5 = !scoreR5;
                return;
            }
            if(!scoreP){
                scoreP = !scoreP;
                return;
            }
            if(!nextS){
                reset();
                return;
            }
        }
    }
}
