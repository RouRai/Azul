package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import game.*;
import datastructures.Box;

public class PlayerPanel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton continueButton, logs, row1, row2, row3, row4, row5, penalty;
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
        row1.setBounds((int)(getWidth() / 3), (int)(getHeight() / 4.65), (int)(getWidth() / 15), (int)(getHeight() / 20));
        row2.setBounds((int)(getWidth() / 3), (int)(getHeight() / 3.75), (int)(getWidth() / 15), (int)(getHeight() / 20));
        row3.setBounds((int)(getWidth() / 3), (int)(getHeight() / 3.10), (int)(getWidth() / 15), (int)(getHeight() / 20));
        row4.setBounds((int)(getWidth() / 3), (int)(getHeight() / 2.65), (int)(getWidth() / 15), (int)(getHeight() / 20));
        row5.setBounds((int)(getWidth() / 3), (int)(getHeight() / 2.3), (int)(getWidth() / 15), (int)(getHeight() / 20));

    }

    private void setUpButtons() {
        // Instantiates JButtons
        continueButton = new JButton("Continue");
        logs = new JButton("Logs");
        row1 = new JButton("Select R1");
        row2 = new JButton("Select row 2");
        row3 = new JButton("Select row 3");
        row4 = new JButton("Select row 4");
        row5 = new JButton("Select row 5");
        // Adds JButtons to the panels
        super.add(continueButton);
        super.add(logs);
        super.add(row1);
        super.add(row2);
        super.add(row3);
        super.add(row4);
        super.add(row5);

        // Adds action listeners to the JButtons

        continueButton.addActionListener(this);
        logs.addActionListener(this);
        row1.addActionListener(this);
        row2.addActionListener(this);
        row3.addActionListener(this);
        row4.addActionListener(this);
        row5.addActionListener(this);
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
