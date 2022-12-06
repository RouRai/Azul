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
    private boolean placeTile, chooseFactory, chooseTile, scoring1, scoring2;
    private Player player;
    private Factory pFactory;
    private int tW, tH, numTiles;
    private String continuePlay, choosePieces, chooseAction;
    private Coordinates chooseBlackTile, chooseRedTile, chooseBlueTile, chooseWhiteTile, chooseYellowTile;
    public PlayerPanel(CardLayout cl) {
        this.cl = cl;
        //p = new Player("Player 1", );
        scoring1 = false;
        scoring2 = false;
        background = Constants.getImage("Background");
        setUpButtons();
        setUpImages();
        //setUpCoordinates();
        //endTurn = true;
        chooseTile = false;
        //choseTile = false;
        //placedTiles = false;
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
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2.setStroke(new BasicStroke(15));
        g2.setFont(new Font("Italics", Font.ITALIC, 30));
        if(!chooseTile && chooseFactory)
        {
        	g2.setFont(new Font("Italics", Font.ITALIC, 20));
            drawPrompt(choosePieces, g2);
            g2.setFont(new Font("Italics", Font.ITALIC, 30));
        }
        else if(chooseTile && !placeTile)
        {
        	drawPrompt(chooseAction, g2);
        }
        else{
            drawPrompt(continuePlay, g2);
        }
        if(chooseTile)
        {
        	drawHighlight(selected, g2);
        }
        System.out.println(placeTile);
        drawScore(g2);
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), 0, (int)((getWidth() / 3) * 2), getHeight()));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), (int)((getHeight() / 6)), (int)((getWidth())), (int)((getHeight() / 6))));
        g2.draw(new Line2D.Float((int)((getWidth() / 3) * 2), (int)((getHeight() / 4) * 3), (int)((getWidth())), (int)((getHeight() / 4) * 3)));
        g2.drawString(player.getName(), (int)((getWidth() / 5) * 4), getHeight() / 10);
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
    public void drawHighlight(String c, Graphics2D g)
    {
    	g.setColor(Color.YELLOW);
    	int h;
    	switch(c)
    	{
    	case "Red": h = chooseRedTile.getY();break;
    	case "Blue": h = chooseBlueTile.getY();break;
    	case "White": h = chooseWhiteTile.getY();break;
    	case "Yellow": h = chooseYellowTile.getY();break;
    	case "Black" : h = chooseBlackTile.getY();break;
        default: h = 99999;
    	}
        if(!(h == 99999))
    	    g.drawRect(chooseBlackTile.getX(), h, tW, tH);
        }
    public void drawPrompt(String s, Graphics2D g)
    {
    	g.drawString(s, getWidth()/6, getHeight()/10);
    }
    private void drawPlayerBoard(Graphics2D g2){
    	Wall wall = player.getWall();
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
        /*datastructures.LinkedList[] rows = new datastructures.LinkedList[5];
        try {
            rows = new datastructures.LinkedList[]{temp.getRow(0).getTiles(), temp.getRow(1).getTiles(), temp.getRow(2).getTiles(), temp.getRow(3).getTiles(), temp.getRow(4).getTiles()};
        } catch (Exception e) {
            System.out.println("there is a row that is out of bounds");
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
    public void drawPyramid(Graphics2D g) throws Exception{
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
        
        /*g.drawImage(gameBoard, (int)(getWidth() / 85) , (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW + (getWidth() / 200), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 2 + (getWidth() / 150), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 3 + (getWidth() / 100), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 4 + (getWidth() / 95), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 5 + (getWidth() / 80), (int)(getHeight() / 1.87567879789), tW, tH, null);
        g.drawImage(gameBoard, (int)(getWidth() / 85) + tW * 6 + (getWidth() / 50), (int)(getHeight() / 1.87567879789), tW, tH, null);*/

    }

    private void drawScore(Graphics2D g){
        //WORK NEEDED
        g.setColor(Color.WHITE);
        g.drawString("Score :" + player.getScore(), getWidth() / 100, getHeight() / 10);
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
            MainPanel.setPlayerCameFrom(player);
        }
        else if(!placeTile && chooseTile)
        {
        	if(e.getSource().equals(row1))
        	{
        		try {
                    if(!(player.getPatternLine().getRow(1).getTiles().size() > 0) || player.getPatternLine().getRow(1).getType().equals("AzulTile" + selected)){
                        placeTiles(row1);
                        System.out.println("Added to row 1");
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        	}
        	else if(e.getSource().equals(row2))
        	{
        		try {
                    if(!(player.getPatternLine().getRow(2).getTiles().size() > 0) || player.getPatternLine().getRow(2).getType().equals("AzulTile" + selected)){
                        placeTiles(row2);
                        System.out.println("Added to row 2");
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        	}
        	else if(e.getSource().equals(row3))
        	{
        		try {
                    if(!(player.getPatternLine().getRow(3).getTiles().size() > 0) || player.getPatternLine().getRow(3).getType().equals("AzulTile" + selected)){
                        placeTiles(row3);
                        System.out.println("Added to row 3");
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        	}
        	else if(e.getSource().equals(row4))
        	{
        		try {
                    if(!(player.getPatternLine().getRow(4).getTiles().size() > 0) || player.getPatternLine().getRow(4).getType().equals("AzulTile" + selected)){
                        placeTiles(row4);
                        System.out.println("Added to row 4");
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        	}
        	else if(e.getSource().equals(row5))
        	{
        		try {
                    if(!(player.getPatternLine().getRow(5).getTiles().size() > 0) || player.getPatternLine().getRow(5).getType().equals("AzulTile" + selected)){
                        placeTiles(row5);
                        System.out.println("Added to row 5");
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if(e.getSource().equals(penalty)){
                placeTiles(penalty);
                
            }
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
    	
        if(!(row == 0)){
        try
    	{
    		player.getPatternLine().setRowType(row, "AzulTile"+selected);
    		player.getPatternLine().addToRow(numTiles, row);
            if(!pFactory.equals(GamePanel.geFactoryFloor())){
            	GamePanel.geFactoryFloor().addTiles(pFactory.getRemaning("AzulTile" + selected));
                /*Iterator<TileObject> iter = pFactory.getTiles().iterator();
                while(iter.hasNext()){
                    pFactory.getTiles().remove(iter.next());
                }*/
                pFactory.getTiles().clear();;
            } else{
                //GamePanel.geFactoryFloor().getRemaning("AzulTile" + selected);
                /*Iterator<TileObject> iter = pFactory.getTiles().iterator();
                TileObject t;
                while(iter.hasNext()){
                    t = iter.next();
                    if(t.getType().equals("AzulTile" + selected)){
                        pFactory.getTiles().removeAll(t);
                    }
                }
                if(pFactory.getTiles().getFirst().getItem().getType().equals("AzulTile" + selected)){
                    pFactory.getTiles().removeAll(pFactory.getTiles().getFirst().getItem());
                }
                if(pFactory.getTiles().getLast().getItem().getType().equals("AzulTile" + selected)){
                    pFactory.getTiles().removeAll(pFactory.getTiles().getLast().getItem());
                }
                for(TileObject t : pFactory.getTiles()){
                    if(t.getType().equals("AzulTile" + selected));
                        pFactory.getTiles().re
                }*/
                /*for(int i = pFactory.getTiles().size() - 1; i >= 0; i++){
                    if(pFactory.getTiles().get(i).getType().equals("AzulTile" + selected)){

                        pFactory.getTiles().remove(i);
                    }
                }*/
                GamePanel.geFactoryFloor().getRemaning("AzulTile" + selected);
            }
            pFactory = null;
            numTiles = 0;
            selected = "";
            placeTile = true;
    	}
    	catch(Exception e1)
    	{
    		e1.printStackTrace();
    	}
    } else{
        if(!pFactory.equals(GamePanel.geFactoryFloor())){
        /*TileObject t;
        Iterator<TileObject> iter = pFactory.getTiles().iterator();
        //GamePanel.geFactoryFloor().addTiles(pFactory.getRemaning("AzulTile" + selected));
        while(iter.hasNext()){
                t = iter.next();
                if(t.getType().equals("AzulTile" + selected)){
                   player.getFloorLine().addTile(t);
                }
        }*/
        for(TileObject t : pFactory.getTiles()){
            if(t.getType().equals("AzulTile" + selected)){
                player.getFloorLine().addTile(t);
            }
        }
        GamePanel.geFactoryFloor().addTiles(pFactory.getRemaning("AzulTile" + selected));
        //iter = pFactory.getTiles().iterator();
        /*while(iter.hasNext()){
                pFactory.getTiles().remove(iter.next());
        }
        pFactory.get*/
        pFactory.getTiles().clear();
        } else{
            /*if(!pFactory.equals(GamePanel.geFactoryFloor())){
                for(int i = pFactory.getTiles().size(); i>= 0; i--){
                    if(pFactory.getTiles().get(i).getType().equals("AzulTile" + selected)){
                        player.getFloorLine().addTile(pFactory.getTiles().get(i));
                        pFactory.getTiles().remove(pFactory.getTiles().get(i));
                    }
                }
                GamePanel.geFactoryFloor().getRemaning("AzulTile" + selected);
            } else{
                for(int i = pFactory.getTiles().size(); i>= 0; i--){
                    if(pFactory.getTiles().get(i).getType().equals("AzulTile" + selected)){
                        player.getFloorLine().addTile(pFactory.getTiles().get(i));
                        pFactory.getTiles().remove(pFactory.getTiles().get(i));
                    }
            
                GamePanel.geFactoryFloor().getRemaning("AzulTile" + selected);
            }*/
            for(int i = GamePanel.geFactoryFloor().getTiles().size() - 1; i>= 0; i--){
                if(GamePanel.geFactoryFloor().getTiles().get(i).getType().equals("AzulTile" + selected)){
                    player.getFloorLine().addTile(GamePanel.geFactoryFloor().getTiles().get(i));
                    //GamePanel.geFactoryFloor().getTiles().remove(pFactory.getTiles().get(i));
                }
            }
            GamePanel.geFactoryFloor().getRemaning("AzulTile" + selected);
        }
        pFactory = null;
        numTiles = 0;
        selected = "";
        placeTile = true;
    }
    }
    public void chose(){
        chooseTile =! chooseTile;
    }
    public void reset(){
        chooseFactory = false;
        chooseTile = false;
        placeTile = false;
        //endTurn = true;
        chooseTile = false;
    }
    private void checkScoring(){
        try {
            if(player.getPatternLine().getRow(1).isFull()){
                player.getWall().addToRow(player.getPatternLine().getRow(1).getType(), 0);
                player.getPatternLine().getRow(1).discardTiles();
            } else if(player.getPatternLine().getRow(2).isFull()){
                player.getWall().addToRow(player.getPatternLine().getRow(2).getType(), 1);
                player.getPatternLine().getRow(2).discardTiles();
            } else if(player.getPatternLine().getRow(3).isFull()){
                player.getWall().addToRow(player.getPatternLine().getRow(3).getType(), 2);
                player.getPatternLine().getRow(3).discardTiles();
            } else if(player.getPatternLine().getRow(4).isFull()){
                player.getWall().addToRow(player.getPatternLine().getRow(4).getType(), 3);
                player.getPatternLine().getRow(4).discardTiles();
            } else if(player.getPatternLine().getRow(5).isFull()){
                player.getWall().addToRow(player.getPatternLine().getRow(5).getType(), 4);
                player.getPatternLine().getRow(5).discardTiles();
    } } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    changeScoring1();
}
    public void changeScoring1(){
        scoring1 = !scoring1;
    }
    public void changeChooseFactory(){
        chooseFactory = !chooseFactory;
    }
    private void checkState(){
        if(scoring1){
            checkScoring();
        } else if(!chooseFactory){
            cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
            GamePanel.setPlayerCameFrom(player);
        }
        /*else if(chooseTile && placeTile){
            //chooseTile = !chooseTile;
            //chooseTile = !chooseTile;
            //placeTile = !placeTile;
            //pFactory.removeType("AzulTile"+selected);
        }*/
        else if(!chooseTile){
            return;
        }
        else if(!placeTile){
            return;
        }
        else{
            TestFrame.getNextPlayer();
            cl.show(Constants.PANEL_CONT, TestFrame.getPlayerName());
            if(GamePanel.isEmpty()){
                TestFrame.getCurrentPlayer().getPanel().changeScoring1();
            } else {
                reset();
            }
        }
    }
    public void changeChoseTile(){
        chooseTile = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    	int x = e.getX();
        int y = e.getY();
        if(e.getButton() == e.BUTTON1 )
        {
        	if(!chooseTile){
        		if(x > chooseBlackTile.getX() && x < chooseBlackTile.getX() + tW){
        			if(y > chooseBlackTile.getY() && y < chooseBlackTile.getY() + tH && pFactory.getNumTiles(Constants.BLACK_TILE) != 0){
        				numTiles = pFactory.getNumTiles(Constants.BLACK_TILE);
        				selected = "Black";
        				chooseTile = true;
        			} 
        			else if(y > chooseRedTile.getY() && y < chooseRedTile.getY() + tH && pFactory.getNumTiles(Constants.RED_TILE) != 0){
        				numTiles = pFactory.getNumTiles(Constants.RED_TILE);
        				selected = "Red";
        				chooseTile = true;
        			} 
        			else if(y > chooseBlueTile.getY() && y < chooseBlueTile.getY() + tH && pFactory.getNumTiles(Constants.BLUE_TILE) != 0){
        				numTiles = pFactory.getNumTiles(Constants.BLUE_TILE);
        				selected = "Blue";
        				chooseTile = true;
        			} 
        			else if(y > chooseWhiteTile.getY() && y < chooseWhiteTile.getY() + tH && pFactory.getNumTiles(Constants.WHITE_TILE) != 0){
        				numTiles = pFactory.getNumTiles(Constants.WHITE_TILE); 
        				selected = "White";
        				chooseTile = true;
        			} 
        			else if(y > chooseYellowTile.getY() && y < chooseYellowTile.getY() + tH && pFactory.getNumTiles(Constants.YELLOW_TILE) != 0){
        				numTiles = pFactory.getNumTiles(Constants.YELLOW_TILE);
        				selected = "Yellow";
        				chooseTile = true;
        			}
        			
        		}
            }
        	repaint();
        }
        
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
