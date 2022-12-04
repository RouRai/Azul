package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private boolean choseTile, placeTile, chooseFactory, chooseTile;
    private Player player;
    private Factory pFactory;
    private int stW, tW, tH, stH, numTiles;
    private String continuePlay, choosePieces, chooseAction;
    private Coordinates chooseBlackTile, chooseRedTile, chooseBlueTile, chooseWhiteTile, chooseYellowTile;
    public PlayerPanel(CardLayout cl) {
        this.cl = cl;
        reset();
        selected = "";
        //p = new Player("Player 1", );
        background = Constants.getImage("Background");
        setUpButtons();
        setUpImages();
        chooseFactory = true;
        continuePlay = "Click on the Continue Button to Proceed";
        choosePieces = "Click on a Tile Color on the Right to Choose";
        chooseAction = "Select a Pattern Line to Place Your Tiles";
        addMouseListener(this);
    }

    public void setPlayer(Player pl){
        player = pl;
    }
    private void setWidthHeight()
    {
    	stW = (int)((getWidth() / 3) / 21.25);
        stH = (int)(((getHeight() / 1.6) / 21.25));
        tW = (int)((getWidth() / 3) / 11.35);
        tH = (int)((getHeight() / 1.6) / 11.35);
    }
    private void setUpCoordinates()
    {
    	chooseBlackTile = new Coordinates((int)((getWidth() / 7) * 5), (int)((getHeight() / 11) * 3));
    	chooseRedTile = new Coordinates((int)((getWidth() / 7) * 5), (int)((getHeight() / 11) * 4));
    	chooseBlueTile = new Coordinates((int)((getWidth() / 7) * 5), (int)((getHeight() / 11) * 5));
    	chooseWhiteTile = new Coordinates((int)((getWidth() / 7) * 5), (int)((getHeight() / 11) * 6));
    	chooseYellowTile = new Coordinates((int)((getWidth() / 7) * 5), (int)((getHeight() / 11) * 7));
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
    	setWidthHeight();
    	setUpCoordinates();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Italics", Font.ITALIC, 40));
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2.setStroke(new BasicStroke(15));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), 0, (int)((getWidth() / 3) * 2), getHeight()));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), (int)((getHeight() / 6)), (int)((getWidth())), (int)((getHeight() / 6))));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), (int)((getHeight() / 4) * 3), (int)((getWidth())), (int)((getHeight() / 4) * 3)));
        g2.drawString(player.getName(), (int)((getWidth() / 5) * 4), getHeight() / 10);
        drawChosen(g2);
        g2.drawImage(blackT, chooseBlackTile.getX(),  chooseBlackTile.getY(), tW, tH, null);
        g2.drawImage(redT, chooseRedTile.getX(),  chooseRedTile.getY(), tW, tH, null);
        g2.drawImage(blueT, chooseBlueTile.getX(),  chooseBlueTile.getY(), tW, tH, null);
        g2.drawImage(whiteT, chooseWhiteTile.getX(),  chooseWhiteTile.getY(), tW, tH, null);
        g2.drawImage(yellowT, chooseYellowTile.getX(),  chooseYellowTile.getY(), tW, tH, null);
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
        drawPenalty(g2);
        drawPlayerBoard(g2);
        try {
            drawPyramid(g2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        drawPenalty(g2);
        if(pFactory != null){
            drawFactoryTiles(g2);
        if(chooseTile)
        	drawPrompt(choosePieces, g2);
        else
        	drawPrompt(continuePlay, g2);
        }
    }
    public void drawChosen(Graphics2D g){
        g.setColor(Color.WHITE);
        if(selected.equals("Black")){
            g.fillRect((int)((getWidth() / 7) * 5) - getWidth() / 50, (int)((getHeight() / 11) * 3) - getHeight() / 50, tW + getWidth() / 30, tH + getHeight() / 30);
        } else if(selected.equals("Blue")){
            g.fillRect((int)((getWidth() / 7) * 5) - getWidth() / 50,  (int)((getHeight() / 11) * 5) - getHeight() / 50, tW + getWidth() / 30, tH + getHeight() / 30);
        } else if(selected.equals("Red")){
            g.fillRect((int)((getWidth() / 7) * 5) - getWidth() / 50,  (int)((getHeight() / 11) * 4)- getHeight() / 50, tW + getWidth() / 30, tH + getHeight() / 30);
        } else if(selected.equals("White")){
            g.fillRect((int)((getWidth() / 7) * 5) - getWidth() / 50,  (int)((getHeight() / 11) * 6) - getHeight() / 50, tW + getWidth() / 30, tH + getHeight() / 30);
        } else if(selected.equals("Yellow")){
            g.fillRect((int)((getWidth() / 7) * 5) - getWidth() / 50,  (int)((getHeight() / 11) * 7) - getHeight() / 50, tW + getWidth() / 30, tH + getHeight() / 30);
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
    public void drawPrompt(String s, Graphics2D g)
    {
    	g.drawString(s, getWidth()/100, getHeight()/28);
    }
    private void drawPlayerBoard(Graphics2D g2){
        Wall wall = player.getWall();
        /*LinkedList[] rows = new datastructures.LinkedList[5];
        try {
            rows = new datastructures.LinkedList[]{temp.getRow(0).getTiles(), temp.getRow(1).getTiles(), temp.getRow(2).getTiles(), temp.getRow(3).getTiles(), temp.getRow(4).getTiles()};
        } catch (Exception e) {
            System.out.println("there is a row that is out of bounds");
        }*/
        String[][] mat = wall.getBoard();
        for(int r = 0; r < mat.length; r++){
            for(int c = 0; c < mat.length; c++){
                if(mat[r][c] != null){
                    try {
                        g2.drawImage(Constants.getImage(wall.getIndicatedTile(r, c)), (int)((getWidth() / 5.85) + (tW * c)), (int)((getHeight() / 4.55) + (r * tH)), null);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        //however you get the element, i have to do so i cant figure this stuff out... rows[0].remove()!= null ? blueT : null
        /*g2.drawImage(blueT , (int)(getWidth() / 5.8), (int)(getHeight() / 4.55), tW, tH, null); //blue tile in col 1
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
        g2.drawImage(yellowT, (int)((getWidth() / 5.85) + (4 * tW)), (int)((getHeight() / 4.55) + (3 * tH)), tW, tH, null);//yellow tile in col 5*/
    }
    public void drawPyramid(Graphics2D g) throws Exception {
        Row line;
        //PatternLine temp = player.getPatternLine();
    	for(int rows = 1; rows < 6; rows++)
    	{
			try {
				line = player.getPatternLine().getRow(rows);
				int i = 0;
                int j = line.getTiles().size();
	    		while(i < j)
	    		{
                    System.out.println(line.getType() + " : row " + rows);
					g.drawImage(Constants.getImage(line.getType()), (int)(getWidth() / 7.75)- tW*i, (int)(getHeight() / 4.55)+ tH* (rows - 1), tW, tH, null);
                    //g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * (j), (int)(getHeight() / 4.55)+tH * (i - 1), tW, tH, null);
					i++;
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(-1);
			}
    	}
    }
    public void drawPenalty(Graphics2D g) {
        HashMap<String, ArrayList<TileObject>> temp = player.getFloorLine().getHashMap();
        BufferedImage tem;
        Iterator<TileObject> iter;
        if(temp.get(Constants.PENALTY_ONE).size() > 0){
            iter = temp.get(Constants.PENALTY_ONE).iterator();
            tem = Constants.getImage(iter.next().getType());
            g.drawImage(tem, (int)(getWidth() / 85) , (int)(getHeight() / 1.87567879789), tW, tH, null);
            if(temp.get(Constants.PENALTY_ONE).size() > 1){
                tem = Constants.getImage(iter.next().getType());
                g.drawImage(tem, (int)(getWidth() / 85) + tW + (getWidth() / 200), (int)(getHeight() / 1.87567879789), tW, tH, null);
            }
        }
        if(temp.get(Constants.PENALTY_TWO).size() > 0){
            iter = temp.get(Constants.PENALTY_TWO).iterator();
            tem = Constants.getImage(iter.next().getType());
            g.drawImage(tem, (int)(getWidth() / 85) + tW * 2 + (getWidth() / 150), (int)(getHeight() / 1.87567879789), tW, tH, null);
            if(temp.get(Constants.PENALTY_TWO).size() > 1){
                tem = Constants.getImage(iter.next().getType());
                g.drawImage(tem, (int)(getWidth() / 85) + tW * 3 + (getWidth() / 100), (int)(getHeight() / 1.87567879789), tW, tH, null);
                if(temp.get(Constants.PENALTY_TWO).size() > 2){
                    tem = Constants.getImage(iter.next().getType());
                    g.drawImage(tem, (int)(getWidth() / 85) + tW * 4 + (getWidth() / 95), (int)(getHeight() / 1.87567879789), tW, tH, null);
                }
            }
        }
        if(temp.get(Constants.PENALTY_THREE).size() > 0){
            iter = temp.get(Constants.PENALTY_THREE).iterator();
            tem = Constants.getImage(iter.next().getType());
            g.drawImage(tem, (int)(getWidth() / 85) + tW * 5 + (getWidth() / 80), (int)(getHeight() / 1.87567879789), tW, tH, null);
            if(temp.get(Constants.PENALTY_THREE).size() > 1){
                tem = Constants.getImage(iter.next().getType());
                g.drawImage(tem, (int)(getWidth() / 85) + tW * 6 + (getWidth() / 50), (int)(getHeight() / 1.87567879789), tW, tH, null);
            }
        }
        //g.drawImage(gameBoard, (int)(getWidth() / 85) , (int)(getHeight() / 1.87567879789), tW, tH, null);
        //g.drawImage(gameBoard, (int)(getWidth() / 85) + tW + (getWidth() / 200), (int)(getHeight() / 1.87567879789), tW, tH, null);
        //g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 2 + (getWidth() / 150), (int)(getHeight() / 1.87567879789), tW, tH, null);
        //g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 3 + (getWidth() / 100), (int)(getHeight() / 1.87567879789), tW, tH, null);
        //g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 4 + (getWidth() / 95), (int)(getHeight() / 1.87567879789), tW, tH, null);
        //g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 5 + (getWidth() / 80), (int)(getHeight() / 1.87567879789), tW, tH, null);
        //g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 6 + (getWidth() / 50), (int)(getHeight() / 1.87567879789), tW, tH, null);

    }

    private void drawScore(Graphics2D g){
        //WORK NEEDED
        g.setColor(Color.WHITE);
        g.drawString("Score :" + player.getScore(), getWidth() / 100, getHeight() / 8);
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
        } 
        else if(e.getSource().equals(expandButton)){
            cl.show(Constants.PANEL_CONT, Constants.MAIN_PANEL);
        } else if(chooseTile && !placeTile){
            if(e.getSource().equals(row1)){
                placeTiles(row1);
                System.out.println("Added to row 1");
            }else if(e.getSource().equals(row2)){
                placeTiles(row2);
                System.out.println("Added to row 2");
            }else if(e.getSource().equals(row3)){
                placeTiles(row3);
                System.out.println("Added to row 3");
            }else if(e.getSource().equals(row4)){
                placeTiles(row4);
                System.out.println("Added to row 4");
            }else if(e.getSource().equals(row5)){
                placeTiles(row5);
                System.out.println("Added to row 5");
            }
            placeTile = true;
            }
        repaint();
    }
    public void placeTiles(JButton x)
    {
    	int row = 0;
    	if(x.equals(row1))
    		row = 1;
    	else if(x.equals(row2))
    		row = 2;
    	else if(x.equals(row3))
    		row = 3;
    	else if(x.equals(row4))
    		row = 4;
    	else if(x.equals(row5))
    		row = 5;
    	try
    	{
    		player.getPatternLine().setRowType(row, "AzulTile" + selected);
    		player.getPatternLine().addToRow(numTiles, row);
    	}
    	catch(Exception e1)
    	{
    		e1.printStackTrace();
    	}
    }
    public void chose(){
        choseTile =! choseTile;
    }
    private void reset(){
        choseTile = false;
        placeTile = false;
        chooseFactory = false;
        chooseTile = false;
    }
    private void checkState(){
        if(chooseFactory){
            cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
            GamePanel.setPlayerCameFrom(player);
            chooseFactory = !chooseFactory;
        }
        else if(!chooseTile){
            return;
        }
        else if(!placeTile){
            return;
        }
        else if(!choseTile){
           return;
        }
    }
    public void changeChoseTile(){
        choseTile = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse pressed");
    	int x = e.getX();
        int y = e.getY();
        	if(!chooseTile){
        		if(x >= (int)((getWidth() / 7) * 5) && x <= (int)((getWidth() / 7) * 5) + tW){
        			if(y >= (int)((getHeight() / 11) * 3) && y <= (int)((getHeight() / 11) * 3) + tH){
        				numTiles = pFactory.getNumTiles(Constants.BLACK_TILE);
                        System.out.println("Got " + numTiles + " black");
        				selected = "Black";
        			} 
        			else if(y >= (int)((getHeight() / 11) * 4) && y <= (int)((getHeight() / 11) * 4) + tH){
        				numTiles = pFactory.getNumTiles(Constants.RED_TILE);
                        System.out.println("Got " + numTiles + " red");
        				selected = "Red";
        			} 
        			else if(y >= (int)((getHeight() / 11) * 5) && y <= (int)((getHeight() / 11) * 5) + tH){
        				numTiles = pFactory.getNumTiles(Constants.BLUE_TILE);
                        System.out.println("Got " + numTiles + " blue");
        				selected = "Blue";
        			} 
        			else if(y >= (int)((getHeight() / 11) * 6) && y <= (int)((getHeight() / 11) * 6) + tH){
        				numTiles = pFactory.getNumTiles(Constants.WHITE_TILE); 
                        System.out.println("Got " + numTiles + " white");
        				selected = "White";
        			} 
        			else if(y >= (int)((getHeight() / 11) * 7) && y <= (int)((getHeight() / 11) * 7) + tH){
        				numTiles = pFactory.getNumTiles(Constants.YELLOW_TILE);
                        System.out.println("Got " + numTiles + " yellow");
        				selected = "Yellow";
        		}
            }
            if(!selected.equals("")){
                chooseTile = true;
                System.out.println("Chose a tile");
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
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
