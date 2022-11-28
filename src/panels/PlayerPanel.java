package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import game.*;
import datastructures.Box;

public class PlayerPanel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton continueButton, expandButton, logs, row1, row2, row3, row4, row5, penalty;
    private BufferedImage background, gameBoard, factory, blackT, blueT, oneT, redT, yellowT, whiteT;
    private boolean choseTile, placeTile, endTurn, scoreR1, scoreR2, scoreR3, scoreR4, scoreR5, scoreP, nextS;
    private Player p;
    private int stW, tW;
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
        stW = (int)((getWidth() / 3) / 21.25);
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tW = (int)((getWidth() / 3) / 11.35);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Italics", Font.ITALIC, 40));
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2.setStroke(new BasicStroke(15));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), 0, (int)((getWidth() / 3) * 2), getHeight()));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), (int)((getHeight() / 6)), (int)((getWidth())), (int)((getHeight() / 6))));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), (int)((getHeight() / 4) * 3), (int)((getWidth())), (int)((getHeight() / 4) * 3)));
        g2.drawString(p.getName(), (int)((getWidth() / 5) * 4), getHeight() / 10);
        g2.drawImage(blackT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 3), tW, tW, null);
        g2.drawImage(redT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 4), tW, tW, null);
        g2.drawImage(blueT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 5), tW, tW, null);
        g2.drawImage(whiteT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 6), tW, tW, null);
        g2.drawImage(yellowT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 7), tW, tW, null);
        g2.drawImage(gameBoard, 0, 0, (int)(getWidth() / 3), (int)(getWidth() / 3), null);
        continueButton.setBounds(getWidth()/22, (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
        expandButton.setBounds(getWidth()/22, (int)(getHeight() / 1.3), getWidth() / 8, getHeight() / 15);
        logs.setBounds((int)(getWidth() / 1.3), (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
        row1.setBounds((int)(getWidth() / 3), (int)(getHeight() / 4.65), (int)(getWidth() / 10), (int)(getHeight() / 20));
        row2.setBounds((int)(getWidth() / 3), (int)(getHeight() / 3.7), (int)(getWidth() / 10), (int)(getHeight() / 20));
        row3.setBounds((int)(getWidth() / 3), (int)(getHeight() / 3.05), (int)(getWidth() / 10), (int)(getHeight() / 20));
        row4.setBounds((int)(getWidth() / 3), (int)(getHeight() / 2.6), (int)(getWidth() / 10), (int)(getHeight() / 20));
        row5.setBounds((int)(getWidth() / 3), (int)(getHeight() / 2.25), (int)(getWidth() / 10), (int)(getHeight() / 20));
        penalty.setBounds((int)(getWidth() / 3),(int)(getHeight() / 1.9), (int)(getWidth() / 10), (int)(getHeight() / 20));
        g2.setColor(Color.BLACK);
        drawScore(g2);
        drawPlayerBoard(g2);
        drawPyramid(g2);
    }

    private void drawPlayerBoard(Graphics2D g2){
        g2.drawImage(blueT, (int)(getWidth() / 5.8), (int)(getHeight() / 4.55), tW, tW, null); //blue tile in col 1
        g2.drawImage(blueT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55) + tW), tW, tW, null);//blue tile in col 2
        g2.drawImage(blueT, (int)((getWidth() / 5.85) + (2 * tW)), (int)((getHeight() / 4.55) + (2 * tW)), tW, tW, null);//blue tile in col 3
        g2.drawImage(blueT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55) + (3 * tW)), tW, tW, null);//blue tile in col 4
        g2.drawImage(blueT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55) + (4 * tW)), tW, tW, null);//blue tile in col 5
        g2.drawImage(whiteT, (int)(getWidth() / 5.8), (int)((getHeight() / 4.55) + tW), tW, tW, null);//white tile in col 1
        g2.drawImage(whiteT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55) + (2 * tW)), tW, tW, null);//white tile in col 2
        g2.drawImage(whiteT, (int)((getWidth() / 5.85) + (2 * tW)), (int)((getHeight() / 4.55) + (3 * tW)), tW, tW, null);//white tile in col 3
        g2.drawImage(whiteT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55) + (4 * tW)), tW, tW, null);//white tile in col 4
        g2.drawImage(whiteT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55)), tW, tW, null);//white tile in col 5
        g2.drawImage(blackT, (int)(getWidth() / 5.8), (int)((getHeight() / 4.55) +(2 * tW)), tW, tW, null);//black tile in col 1
        g2.drawImage(blackT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55) + (3 * tW)), tW, tW, null);//black tile in col 2
        g2.drawImage(blueT, (int)((getWidth() / 5.85) + (2 * tW)), (int)((getHeight() / 4.55) + (4 * tW)), tW, tW, null);//black tile in col 3
        g2.drawImage(blackT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55)), tW, tW, null);//black tile in col 4
        g2.drawImage(blackT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55) + (tW)), tW, tW, null);//black tile in col 5
        g2.drawImage(redT, (int)(getWidth() / 5.8), (int)((getHeight() / 4.55) +(3 * tW)), tW, tW, null);//red tile in col 1
        g2.drawImage(redT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55) + (4 * tW)), tW, tW, null);//red tile in col 2
        g2.drawImage(redT, (int)(getWidth() / 5.85) + (2* tW), (int)((getHeight() / 4.55) ), tW, tW, null);//red tile in col 3
        g2.drawImage(redT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55) + tW), tW, tW, null);//red tile in col 4
        g2.drawImage(redT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55) + (2 * tW)), tW, tW, null);//red tile in col 5
        g2.drawImage(yellowT, (int)(getWidth() / 5.8), (int)((getHeight() / 4.55) +(4 * tW)), tW, tW, null);//yellow tile in col 1
        g2.drawImage(yellowT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55)), tW, tW, null);//yellow tile in col 2
        g2.drawImage(yellowT, (int)((getWidth() / 5.85) + (2 * tW)), (int)((getHeight() / 4.55) + (tW)), tW, tW, null);//yellow tile in col 3
        g2.drawImage(yellowT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55) + (2 * tW)), tW, tW, null);//yellow tile in col 4
        g2.drawImage(yellowT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55) + (3 * tW)), tW, tW, null);//yellow tile in col 5
    }
    public void drawPyramid(Graphics2D g) {
        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55), tW, tW, null); // row 1 

        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55) + tW, tW, tW, null); // row 2
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW, (int)(getHeight() / 4.55) + tW, tW, tW, null);

        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55) + 2 * tW, tW, tW, null); // row 3
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW, (int)(getHeight() / 4.55) + 2 * tW, tW, tW, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 2, (int)(getHeight() / 4.55) + 2 * tW, tW, tW, null);

        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55) + 3 * tW, tW, tW, null); // row 4
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW, (int)(getHeight() / 4.55) + 3 * tW, tW, tW, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 2, (int)(getHeight() / 4.55) + 3 * tW, tW, tW, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 3, (int)(getHeight() / 4.55) + 3 * tW, tW, tW, null);

        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55) + 4 * tW, tW, tW, null); // row 5
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW, (int)(getHeight() / 4.55) + 4 * tW, tW, tW, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 2, (int)(getHeight() / 4.55) + 4 * tW, tW, tW, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 3, (int)(getHeight() / 4.55) + 4 * tW, tW, tW, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 4, (int)(getHeight() / 4.55) + 4 * tW, tW, tW, null);
    }

    private void drawScore(Graphics2D g){
        //WORK NEEDED
        int k;
        if(p.getActualPoints() == 0){
            g.fillRect((getWidth() / 70), 0, stW, stW);
        } else if(p.getActualPoints() % 100 <= 20 && p.getActualPoints() % 100 >= 1){
            k = (p.getActualPoints() % 100) - 1;
            g.fillRect(getWidth() / 70 + k * stW, getHeight() / 29, stW, stW);
        } else if(p.getActualPoints() % 100 <= 40 && p.getActualPoints() % 100 >= 21){
            k = (p.getActualPoints() % 100) - 21;
            g.fillRect(getWidth() / 70 + k * stW, getHeight() / 15, stW, stW);
        } else if(p.getActualPoints() % 100 <= 60 && p.getActualPoints() % 100 >= 41){
            k = (p.getActualPoints() % 100) - 41;
            g.fillRect(getWidth() / 70 + k * (stW), (int)(getHeight() / 9.6), stW, stW);
        } else if(p.getActualPoints() % 100 <= 80 && p.getActualPoints() % 100 >= 61){
            k = (p.getActualPoints() % 100) - 61;
            g.fillRect(getWidth() / 70 + k * (stW), (int)(getHeight() / 7.2), stW, stW);
        } else if(p.getActualPoints() % 100 < 100){
            k = (p.getActualPoints() % 100) - 81;
            g.fillRect(getWidth() / 70 + k * (stW), (int)(getHeight() / 5.8), stW, stW);
        } else if(p.getActualPoints() % 100 == 0){
            k = 19;
            g.fillRect(getWidth() / 70 + k * (stW), (int)(getHeight() / 5.8), stW, stW);
        }
    }
    
    private void setUpButtons() {
        // Instantiates JButtons
        continueButton = new JButton("Continue");
        logs = new JButton("Logs");
        row1 = new JButton("Select Row 1");
        row2 = new JButton("Select Row 2");
        row3 = new JButton("Select Row 3");
        row4 = new JButton("Select Row 4");
        row5 = new JButton("Select Row 5");
        penalty = new JButton("Select Penalty");
        expandButton = new JButton("Expand");
        // Adds JButtons to the panels
        super.add(continueButton);
        super.add(logs);
        super.add(row1);
        super.add(row2);
        super.add(row3);
        super.add(row4);
        super.add(row5);
        super.add(penalty);
        super.add(expandButton);
        // Adds action listeners to the JButtons

        continueButton.addActionListener(this);
        logs.addActionListener(this);
        row1.addActionListener(this);
        row2.addActionListener(this);
        row3.addActionListener(this);
        row4.addActionListener(this);
        row5.addActionListener(this);
        penalty.addActionListener(this);
        expandButton.addActionListener(this);
    }

    // Runs this method when something happens to one of the JButtons
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(continueButton)){
            checkState();
        } else if(e.getSource().equals(expandButton)){
            cl.show(Constants.PANEL_CONT, Constants.MAIN_PANEL);
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
