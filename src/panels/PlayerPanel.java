package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;

import game.Constants;
import datastructures.Box;

public class PlayerPanel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton continueButton, logs;
    private BufferedImage background, gameBoard, factory, blackT, blueT, oneT, redT, yellowT, whiteT;
    private boolean choseTile, placeTile, endTurn, scoreR1, scoreR2, scoreR3, scoreR4, scoreR5, scoreP, nextS;

    public PlayerPanel(CardLayout cl) {
        this.cl = cl;
        background = Constants.getImage("Background");
        setUpButtons();
        setUpImages();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(gameBoard, 0, 0, (int)(getWidth() / 3), (int)(getWidth() / 3), null);
        g.drawImage(factory, (int)(getWidth() / 1.2), (int)(getHeight() / 30), getHeight() / 8, getHeight() / 8, null);

        logs.setBounds((int)(getWidth() / 1.3), (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
    }

    private void setUpButtons() {
        // Instantiates JButtons
        continueButton = new JButton("Continue");
        logs = new JButton("Logs");
        // Adds JButtons to the panels
        add(continueButton, BorderLayout.LINE_END);
        super.add(logs);
        // Adds action listeners to the JButtons
        continueButton.addActionListener(this);
        logs.addActionListener(this);
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
<<<<<<< HEAD

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(gameBoard, 0, 0, (int)(getWidth() / 3), (int)(getWidth() / 3), null);
        g.drawImage(factory, (int)(getWidth() / 1.2), (int)(getHeight() / 30), getHeight() / 8, getHeight() / 8, null);
        continueButton.setBounds(getWidth()/22, (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
        Logs.setBounds((int)(getWidth() / 1.3), (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
    }

    private void setUpButtons() {
        // Instantiates JButtons
        continueButton = new JButton("Continue");
        Logs = new JButton("Logs");
        // Adds JButtons to the panels
        super.add(continueButton);
        super.add(Logs);
        // Adds action listeners to the JButtons

        continueButton.addActionListener(this);
        Logs.addActionListener(this);
=======
>>>>>>> 3300774681479bb3caaac547dc11218eca7f81e1
    }

    // Runs this method when something happens to one of the JButtons
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(continueButton)){
            if(!choseTile){
                cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
                choseTile =!choseTile;
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
                    nextS = !nextS;
                    return;
                }
            }
        }
    }


}