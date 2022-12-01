package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import game.*;
import logic.*;
import datastructures.*;
import logic.*;
import datastructures.LinkedList;

public class PlayerPanel extends JPanel implements ActionListener, MouseListener{

    private CardLayout cl;
    private String selected;
    private JButton continueButton, expandButton, logs, row1, row2, row3, row4, row5, penalty;
    private BufferedImage background, gameBoard, factory, blackT, blueT, oneT, redT, yellowT, whiteT;
    private boolean choseTile, placeTile, endTurn, chooseTile;
    private Player p;
    private Factory pFactory;
    private int stW, tW, tH, stH, numTiles;
    public PlayerPanel(CardLayout cl) {
        this.cl = cl;
        //p = new Player("Player 1", );
        background = Constants.getImage("Background");
        setUpButtons();
        setUpImages();
    }

    public void setPlayer(Player pl){
        p = pl;
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
        stH = (int)(((getHeight() / 1.6) / 21.25));
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tW = (int)((getWidth() / 3) / 11.35);
        tH = (int)((getHeight() / 1.6) / 11.35);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Italics", Font.ITALIC, 40));
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2.setStroke(new BasicStroke(15));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), 0, (int)((getWidth() / 3) * 2), getHeight()));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), (int)((getHeight() / 6)), (int)((getWidth())), (int)((getHeight() / 6))));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), (int)((getHeight() / 4) * 3), (int)((getWidth())), (int)((getHeight() / 4) * 3)));
        g2.drawString(p.getName(), (int)((getWidth() / 5) * 4), getHeight() / 10);
        g2.drawImage(blackT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 3), tW, tH, null);
        g2.drawImage(redT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 4), tW, tH, null);
        g2.drawImage(blueT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 5), tW, tH, null);
        g2.drawImage(whiteT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 6), tW, tH, null);
        g2.drawImage(yellowT, (int)((getWidth() / 7) * 5),  (int)((getHeight() / 11) * 7), tW, tH, null);
        g2.drawImage(gameBoard, 0, (int)(getHeight() / 4.8), (int)(getWidth() / 3), (int)(getHeight() / 2.4), null);
        continueButton.setBounds(getWidth()/22, (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
        expandButton.setBounds(getWidth()/22, (int)(getHeight() / 1.3), getWidth() / 8, getHeight() / 15);
        logs.setBounds((int)(getWidth() / 1.3), (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
        row1.setBounds((int)(getWidth() / 3), (int)(getHeight() / 4.65), (int)(getWidth() / 10), (int)(getHeight() / 20));
        row2.setBounds((int)(getWidth() / 3), (int)(getHeight() / 3.7), (int)(getWidth() / 10), (int)(getHeight() / 20));
        row3.setBounds((int)(getWidth() / 3), (int)(getHeight() / 3.05), (int)(getWidth() / 10), (int)(getHeight() / 20));
        row4.setBounds((int)(getWidth() / 3), (int)(getHeight() / 2.6), (int)(getWidth() / 10), (int)(getHeight() / 20));
        row5.setBounds((int)(getWidth() / 3), (int)(getHeight() / 2.25), (int)(getWidth() / 10), (int)(getHeight() / 20));
        penalty.setBounds((int)(getWidth() / 3),(int)(getHeight() / 1.9), (int)(getWidth() / 10), (int)(getHeight() / 20));
        drawScore(g2);
        drawPlayerBoard(g2);
        drawPyramid(g2);
        drawPenalty(g2);
        if(pFactory != null){
            drawFactoryTiles(g2);
        }
    }
    public void drawFactoryTiles(Graphics2D g){
        g.setColor(Color.WHITE);
        g.drawString("" + pFactory.getNumTiles(Constants.BLACK_TILE), (int)((getWidth() / 7) * 6), (int)((getHeight() / 11) * 3) + tW);
        g.drawString("" + pFactory.getNumTiles(Constants.RED_TILE), (int)((getWidth() / 7) * 6), (int)((getHeight() / 11) * 4) + tW);
        g.drawString("" + pFactory.getNumTiles(Constants.BLUE_TILE), (int)((getWidth() / 7) * 6), (int)((getHeight() / 11) * 5) + tW);
        g.drawString("" + pFactory.getNumTiles(Constants.WHITE_TILE), (int)((getWidth() / 7) * 6),  (int)((getHeight() / 11) * 6) + tW);
        g.drawString("" + pFactory.getNumTiles(Constants.YELLOW_TILE), (int)((getWidth() / 7) * 6),  (int)((getHeight() / 11) * 7) + tW);
    }
    public void addFactory(Factory x)
    {
        pFactory = x;
    }
    private void drawPlayerBoard(Graphics2D g2){
        g2.drawImage(blueT, (int)(getWidth() / 5.8), (int)(getHeight() / 4.55), tW, tH, null); //blue tile in col 1
        g2.drawImage(blueT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55) + tH), tW, tH, null);//blue tile in col 2
        g2.drawImage(blueT, (int)((getWidth() / 5.85) + (2 * tW)), (int)((getHeight() / 4.55) + (2 * tH)), tW, tH, null);//blue tile in col 3
        g2.drawImage(blueT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55) + (3 * tH)), tW, tH, null);//blue tile in col 4
        g2.drawImage(blueT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55) + (4 * tH)), tW, tH, null);//blue tile in col 5
        g2.drawImage(whiteT, (int)(getWidth() / 5.8), (int)((getHeight() / 4.55) + tH), tW, tH, null);//white tile in col 1
        g2.drawImage(whiteT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55) + (2 * tH)), tW, tH, null);//white tile in col 2
        g2.drawImage(whiteT, (int)((getWidth() / 5.85) + (2 * tW)), (int)((getHeight() / 4.55) + (3 * tH)), tW, tH, null);//white tile in col 3
        g2.drawImage(whiteT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55) + (4 * tH)), tW, tH, null);//white tile in col 4
        g2.drawImage(whiteT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55)), tW, tH, null);//white tile in col 5
        g2.drawImage(blackT, (int)(getWidth() / 5.8), (int)((getHeight() / 4.55) +(2 * tH)), tW, tH, null);//black tile in col 1
        g2.drawImage(blackT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55) + (3 * tH)), tW, tH, null);//black tile in col 2
        g2.drawImage(blackT, (int)((getWidth() / 5.85) + (2 * tW)), (int)((getHeight() / 4.55) + (4 * tH)), tW, tH, null);//black tile in col 3
        g2.drawImage(blackT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55)), tW, tH, null);//black tile in col 4
        g2.drawImage(blackT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55) + (tH)), tW, tH, null);//black tile in col 5
        g2.drawImage(redT, (int)(getWidth() / 5.8), (int)((getHeight() / 4.55) +(3 * tH)), tW, tH, null);//red tile in col 1
        g2.drawImage(redT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55) + (4 * tH)), tW, tH, null);//red tile in col 2
        g2.drawImage(redT, (int)(getWidth() / 5.85) + (2* tW), (int)((getHeight() / 4.55) ), tW, tH, null);//red tile in col 3
        g2.drawImage(redT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55) + tH), tW, tH, null);//red tile in col 4
        g2.drawImage(redT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55) + (2 * tH)), tW, tH, null);//red tile in col 5
        g2.drawImage(yellowT, (int)(getWidth() / 5.8), (int)((getHeight() / 4.55) +(4 * tH)), tW, tH, null);//yellow tile in col 1
        g2.drawImage(yellowT, (int)((getWidth() / 5.8) + tW), (int)((getHeight() / 4.55)), tW, tH, null);//yellow tile in col 2
        g2.drawImage(yellowT, (int)((getWidth() / 5.85) + (2 * tW)), (int)((getHeight() / 4.55) + (tH)), tW, tH, null);//yellow tile in col 3
        g2.drawImage(yellowT, (int)((getWidth() / 5.85) + (3 * tW)), (int)((getHeight() / 4.55) + (2 * tH)), tW, tH, null);//yellow tile in col 4
        g2.drawImage(yellowT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55) + (3 * tH)), tW, tH, null);//yellow tile in col 5
    }
    public void drawPyramid(Graphics2D g) {
        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55), tW, tH, null); // row 1 

        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55) + tH, tW, tH, null); // row 2
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW, (int)(getHeight() / 4.55) + tH, tW, tH, null);

        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55) + 2 * tH, tW, tH, null); // row 3
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW, (int)(getHeight() / 4.55) + 2 * tH, tW, tH, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 2, (int)(getHeight() / 4.55) + 2 * tH, tW, tH, null);

        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55) + 3 * tH, tW, tH, null); // row 4
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW, (int)(getHeight() / 4.55) + 3 * tH, tW, tH, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 2, (int)(getHeight() / 4.55) + 3 * tH, tW, tH, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 3, (int)(getHeight() / 4.55) + 3 * tH, tW, tH, null);

        g.drawImage(oneT, (int)(getWidth() / 7.75), (int)(getHeight() / 4.55) + 4 * tH, tW, tH, null); // row 5
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW, (int)(getHeight() / 4.55) + 4 * tH, tW, tH, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 2, (int)(getHeight() / 4.55) + 4 * tH, tW, tH, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 3, (int)(getHeight() / 4.55) + 4 * tH, tW, tH, null);
        g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * 4, (int)(getHeight() / 4.55) + 4 * tH, tW, tH, null);
    }
    public void drawPenalty(Graphics2D g) {
        
        g.drawImage(gameBoard, (int)(getWidth() / 85) , (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW + (getWidth() / 200), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 2 + (getWidth() / 150), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 3 + (getWidth() / 100), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 4 + (getWidth() / 95), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 5 + (getWidth() / 80), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 6 + (getWidth() / 50), (int)(getHeight() / 1.87567879789), tW, tH, null);

    }

    private void drawScore(Graphics2D g){
        //WORK NEEDED
        g.setColor(Color.WHITE);
        g.drawString("Score :" + p.getScore(), getWidth() / 100, getHeight() / 10);
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
        } else if(e.getSource().equals(row1)){
            if(chooseTile){
                if(selected.equals("black")){
                    try {
                        p.getPatternLine().setRowType(1, Constants.BLACK_TILE);
                        p.getPatternLine().addToRow(numTiles, 1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("red")){
                    try {
                        p.getPatternLine().setRowType(1, Constants.RED_TILE);
                        p.getPatternLine().addToRow(numTiles, 1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("blue")){
                    try {
                        p.getPatternLine().setRowType(1, Constants.BLUE_TILE);
                        p.getPatternLine().addToRow(numTiles, 1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("white")){
                    try {
                        p.getPatternLine().setRowType(1, Constants.WHITE_TILE);
                        p.getPatternLine().addToRow(numTiles, 1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("yellow")){
                    try {
                        p.getPatternLine().setRowType(1, Constants.YELLOW_TILE);
                        p.getPatternLine().addToRow(numTiles, 1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            repaint();
        } else if(e.getSource().equals(row2)){
            if(chooseTile){
                if(selected.equals("black")){
                    try {
                        p.getPatternLine().setRowType(2, Constants.BLACK_TILE);
                        p.getPatternLine().addToRow(numTiles, 2);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("red")){
                    try {
                        p.getPatternLine().setRowType(2, Constants.RED_TILE);
                        p.getPatternLine().addToRow(numTiles, 2);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("blue")){
                    try {
                        p.getPatternLine().setRowType(2, Constants.BLUE_TILE);
                        p.getPatternLine().addToRow(numTiles, 2);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("white")){
                    try {
                        p.getPatternLine().setRowType(2, Constants.WHITE_TILE);
                        p.getPatternLine().addToRow(numTiles, 2);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("yellow")){
                    try {
                        p.getPatternLine().setRowType(2, Constants.YELLOW_TILE);
                        p.getPatternLine().addToRow(numTiles, 2);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            repaint();
        } else if(e.getSource().equals(row3)){
            if(chooseTile){
                if(selected.equals("black")){
                    try {
                        p.getPatternLine().setRowType(3, Constants.BLACK_TILE);
                        p.getPatternLine().addToRow(numTiles, 3);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("red")){
                    try {
                        p.getPatternLine().setRowType(3, Constants.RED_TILE);
                        p.getPatternLine().addToRow(numTiles, 3);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("blue")){
                    try {
                        p.getPatternLine().setRowType(3, Constants.BLUE_TILE);
                        p.getPatternLine().addToRow(numTiles, 3);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("white")){
                    try {
                        p.getPatternLine().setRowType(3, Constants.WHITE_TILE);
                        p.getPatternLine().addToRow(numTiles, 3);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("yellow")){
                    try {
                        p.getPatternLine().setRowType(3, Constants.YELLOW_TILE);
                        p.getPatternLine().addToRow(numTiles, 3);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            repaint();
        } else if(e.getSource().equals(row4)){
            if(chooseTile){
                if(selected.equals("black")){
                    try {
                        p.getPatternLine().setRowType(4, Constants.BLACK_TILE);
                        p.getPatternLine().addToRow(numTiles, 4);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("red")){
                    try {
                        p.getPatternLine().setRowType(4, Constants.RED_TILE);
                        p.getPatternLine().addToRow(numTiles, 4);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("blue")){
                    try {
                        p.getPatternLine().setRowType(4, Constants.BLUE_TILE);
                        p.getPatternLine().addToRow(numTiles, 4);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("white")){
                    try {
                        p.getPatternLine().setRowType(4, Constants.WHITE_TILE);
                        p.getPatternLine().addToRow(numTiles, 4);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("yellow")){
                    try {
                        p.getPatternLine().setRowType(4, Constants.YELLOW_TILE);
                        p.getPatternLine().addToRow(numTiles, 4);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            repaint();
        } else if(e.getSource().equals(row5)){
            if(chooseTile){
                if(selected.equals("black")){
                    try {
                        p.getPatternLine().setRowType(5, Constants.BLACK_TILE);
                        p.getPatternLine().addToRow(numTiles, 5);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("red")){
                    try {
                        p.getPatternLine().setRowType(5, Constants.RED_TILE);
                        p.getPatternLine().addToRow(numTiles, 5);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("blue")){
                    try {
                        p.getPatternLine().setRowType(5, Constants.BLUE_TILE);
                        p.getPatternLine().addToRow(numTiles, 5);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("white")){
                    try {
                        p.getPatternLine().setRowType(5, Constants.WHITE_TILE);
                        p.getPatternLine().addToRow(numTiles, 5);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if(selected.equals("yellow")){
                    try {
                        p.getPatternLine().setRowType(5, Constants.YELLOW_TILE);
                        p.getPatternLine().addToRow(numTiles, 5);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            repaint();
        }
    }
    public void chose(){
        choseTile =! choseTile;
    }
    private void reset(){
        choseTile = false;
        placeTile = false;
        endTurn = false;
        chooseTile = false;
    }
    private void checkState(){
        if(!choseTile){
            cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
            GamePanel.setPlayerCameFrom(p);
            return;
        }
        if(!chooseTile){
            return;
        }
        if(!placeTile){
            //placeTile = !placeTile;
            return;
        }
        if(!endTurn){
           endTurn = !endTurn;
            return;
        }
    }
    public void changeChoseTile(){
        choseTile = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        int x = e.getX();
        int y = e.getY();
        if(!chooseTile){
            chooseTile = true;
            if(x >= (int)((getWidth() / 7) * 5) && x <= (int)((getWidth() / 7) * 5) + tW){
                if(y >= (int)((getHeight() / 11) * 3) && y <= (int)((getHeight() / 11) * 3) + tH){
                    numTiles = pFactory.getNumTiles(Constants.BLACK_TILE);
                    selected = "black";
                } else if(y >= (int)((getHeight() / 11) * 4) && y <= (int)((getHeight() / 11) * 4) + tH){
                    numTiles = pFactory.getNumTiles(Constants.RED_TILE);
                    selected = "red";
                } else if(y >= (int)((getHeight() / 11) * 5) && y <= (int)((getHeight() / 11) * 5) + tH){
                    numTiles = pFactory.getNumTiles(Constants.BLUE_TILE);
                    selected = "blue";
                } else if(y >= (int)((getHeight() / 11) * 6) && y <= (int)((getHeight() / 11) * 6) + tH){
                    numTiles = pFactory.getNumTiles(Constants.WHITE_TILE); 
                    selected = "white";
                } else if(y >= (int)((getHeight() / 11) * 7) && y <= (int)((getHeight() / 11) * 7) + tH){
                    numTiles = pFactory.getNumTiles(Constants.YELLOW_TILE);
                    selected = "white";
                }
            }
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
