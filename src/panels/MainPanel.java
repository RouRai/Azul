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
import java.util.Iterator;
import java.util.HashMap;
import datastructures.*;
public class MainPanel extends JPanel implements ActionListener{
    private CardLayout cl;
    private BufferedImage background, factory, logo, redTile, yellowTile, whiteTile, blueTile, blackTile, oneTile, gameBoard;
    //private ArrayList<Player> players;
    private static Player temp;
    private int bW, bH, tW, tH;
    private JButton backToPlayerScreen;
    //private datastructures.LinkedList<TileObject> lid1, lid2, lid3, lid4;

    public MainPanel(CardLayout cl){
        this.cl = cl;
        //players = new ArrayList<>();
        /*lid1 = new datastructures.LinkedList<>();
        lid2 = new datastructures.LinkedList<>();
        lid3 = new datastructures.LinkedList<>();
        lid4 = new datastructures.LinkedList<>();*/
        /*players.add(TestFrame.getP1());
        players.add(TestFrame.getP2());
        players.add(TestFrame.getP3());
        players.add(TestFrame.getP4());*/
        setUpImages();
        setUpButtons();
    }
    public void paintComponent(Graphics g){
        bW = (int)(getWidth() / 4);
        tW = (int)(bW / 11.35);
        bH = (int)(getHeight() / 4);
        tH = (int)(bH / 8.7);
        //stW = (int)(bW / (573 / 25));
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        drawBoard(g);
        g.setFont(new Font("Italics", Font.ITALIC, 40));
        drawNames(g);
        g.drawImage(logo, getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2, null);
        drawp1Score(g);
        drawp2Score(g);
        drawp3Score(g);
        drawp4Score(g);
        drawCurrentPlayer(g);
        try {
            drawPyramidP1(g);
            drawPyramidP2(g);
            drawPyramidP3(g);
            drawPyramidP4(g);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //drawPenaltyP1(g);
    }
    public void drawPenaltyP1(Graphics g) {
        HashMap<String, ArrayList<TileObject>> temp = TestFrame.getP1().getFloorLine().getHashMap();
        BufferedImage tem;
        Iterator<TileObject> iter;
        if(temp.get(Constants.PENALTY_ONE).size() > 0){
            iter = temp.get(Constants.PENALTY_ONE).iterator();
            tem = Constants.getImage(iter.next().getType());
            g.drawImage(tem, (int)(getWidth() / 85) , (int)(getHeight() / 1.87567879789), tW, tH, null);
            if(temp.get(Constants.PENALTY_ONE).size() > 1){
                tem = Constants.getImage(iter.next().getType());
                g.drawImage(tem, (int)(getWidth() / 85) + tW + (getWidth() / 200), (int)(getHeight() / 3), tW, tH, null);
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
        g.drawImage(blueTile, (int)(getWidth() / 105), (int)(getHeight() / 5), tW, tH, null);
        g.drawImage(blueTile, (int)(getWidth() / 100) + tW , (int)(getHeight() / 5), tW, tH, null);
        g.drawImage(blueTile, (int)(getWidth() / 100) + 2 * tW + getWidth() / 300 , (int)(getHeight() / 5), tW, tH, null);
    }
    private void drawPyramidP4(Graphics g) throws Exception {
        Row line;
        //PatternLine temp = player.getPatternLine();
    	for(int rows = 1; rows < 6; rows++)
    	{
			try {
				line = TestFrame.getP4().getPatternLine().getRow(rows);
				int i = 0;
                int j = line.getTiles().size();
	    		while(i < j)
	    		{
                    System.out.println(line.getType() + " : row " + rows);
                    //g.drawImage(Constants.getImage(line.getType()), (int)(getWidth() / 1.18) - tW * i, getHeight() / 100 + tH * (rows - 1), tW, tH, null);
                    //g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * (j), (int)(getHeight() / 4.55)+tH * (i - 1), tW, tH, null);
                    g.drawImage(Constants.getImage(line.getType()), (int)(getWidth() / 10.3) - tW * i, (int)(getHeight() / 1.31) + tH * (rows - 1), tW, tH, null);
					i++;
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(-1);
			}
    	}
        //g.drawImage(blueTile, (int)(getWidth() / 1.18), (int)(getHeight() / 1.31), tW, tH, null);
    }
    private void drawPyramidP3(Graphics g) throws Exception {
        Row line;
        //PatternLine temp = player.getPatternLine();
    	for(int rows = 1; rows < 6; rows++)
    	{
			try {
				line = TestFrame.getP3().getPatternLine().getRow(rows);
				int i = 0;
                int j = line.getTiles().size();
	    		while(i < j)
	    		{
                    System.out.println(line.getType() + " : row " + rows);
                    //g.drawImage(Constants.getImage(line.getType()), (int)(getWidth() / 1.18) - tW * i, getHeight() / 100 + tH * (rows - 1), tW, tH, null);
                    //g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * (j), (int)(getHeight() / 4.55)+tH * (i - 1), tW, tH, null);
                    g.drawImage(Constants.getImage(line.getType()), (int)(getWidth() / 1.18) - tW * i, (int)(getHeight() / 1.31) + tH * (rows - 1), tW, tH, null);
					i++;
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(-1);
			}
    	}
        //g.drawImage(blueTile, (int)(getWidth() / 1.18), (int)(getHeight() / 1.31), tW, tH, null);
    }
    private void drawPyramidP2(Graphics g) throws Exception {
        Row line;
        //PatternLine temp = player.getPatternLine();
    	for(int rows = 1; rows < 6; rows++)
    	{
			try {
				line = TestFrame.getP2().getPatternLine().getRow(rows);
				int i = 0;
                int j = line.getTiles().size();
	    		while(i < j)
	    		{
                    System.out.println(line.getType() + " : row " + rows);
                    g.drawImage(Constants.getImage(line.getType()), (int)(getWidth() / 1.18) - tW * i, getHeight() / 100 + tH * (rows - 1), tW, tH, null);
                    //g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * (j), (int)(getHeight() / 4.55)+tH * (i - 1), tW, tH, null);
					i++;
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(-1);
			}
    	}
        //g.drawImage(blueTile, (int)(getWidth() / 1.18), getHeight() / 100, tW, tH, null);
    }
    private void drawPyramidP1(Graphics g) throws Exception {
        Row line;
        //PatternLine temp = player.getPatternLine();
    	for(int rows = 1; rows < 6; rows++)
    	{
			try {
				line = TestFrame.getP1().getPatternLine().getRow(rows);
				int i = 0;
                int j = line.getTiles().size();
	    		while(i < j)
	    		{
                    System.out.println(line.getType() + " : row " + rows);
					//g.drawImage(Constants.getImage(line.getType()), (int)(getWidth() / 7.75)- tW * i, (int)(getHeight() / 4.55)+ tH* (rows - 1), tW, tH, null);
                    g.drawImage(Constants.getImage(line.getType()), (int)(getWidth() / 10.3) - tW * i, getHeight() / 100 + tH * (rows - 1), tW, tH, null);
                    //g.drawImage(oneT, (int)(getWidth() / 7.75) - tW * (j), (int)(getHeight() / 4.55)+tH * (i - 1), tW, tH, null);
					i++;
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(-1);
			}
    	}
        //g.drawImage(blueTile, (int)(getWidth() / 10.3), getHeight() / 100, tW, tH, null);
    }
    private void drawCurrentPlayer(Graphics g){
        g.setColor(Color.YELLOW);
        if(temp.getName().equals("Player 1")){
            g.drawRect(0, 0, bW, bH);
        } else if(temp.getName().equals("Player 2")){
            g.drawRect(getWidth() - bW, 0, bW, bH);
        } else if(temp.getName().equals("Player 3")){
            g.drawRect(getWidth() - bW, getHeight() - bH, bW, bH);
        }else if(temp.getName().equals("Player 4")){
            g.drawRect(0, getHeight() - bH, bW, bH);
        } 
    }
    private void drawp1Score(Graphics g){
        //int s = players.get(0).getScore();
        g.setColor(Color.WHITE);
        g.drawString("Score: " + TestFrame.getP1().getScore(), getWidth() / 40, (int)(1.2 * bH ));
    }
    private void drawp2Score(Graphics g){
        //int s = players.get(1).getScore();
        g.setColor(Color.WHITE);
        //g.drawString("Score: " + TestFrame.getP2().getScore(), getWidth() / 40, (int)(1.5 * bH ));
        g.drawString("Score: " + TestFrame.getP2().getScore(), (int)(getWidth() / 1.15), (int)(1.2 * bH ));
    
    }
    private void drawp3Score(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Score: " + TestFrame.getP3().getScore(), (int)(getWidth() / 1.15), (int)(2.9 * bH ));
    }
    private void drawp4Score(Graphics g){
        //int s = players.get(3).getScore();
        g.setColor(Color.WHITE);
        //g.drawString("Score: " + TestFrame.getP4().getScore(), s, s);
        g.drawString("Score: " + TestFrame.getP4().getScore(), getWidth() / 40, (int)(2.9 * bH ));
    }
    private void drawNames(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Player 1", (int)(getWidth() / 3.8), getHeight() / 20);
        g.drawString("Player 2", (int)(getWidth() / 1.55), getHeight() / 20);
        g.drawString("Player 3", (int)(getWidth() / 1.55), (int)(getHeight() / 1.05));
        g.drawString("Player 4", (int)(getWidth() / 3.8), (int)(getHeight() / 1.05));
    }
    private void drawBoard(Graphics g){
        g.drawImage(gameBoard, 0, 0, bW, bH, null);
        g.drawImage(gameBoard, 0, getHeight() - bH, bW, bH, null);
        g.drawImage(gameBoard, getWidth() - bW, 0, bW, bH, null);
        g.drawImage(gameBoard, getWidth() - bW, getHeight() - bH, bW, bH, null);
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
            if(temp.getName().equals("Player 1")){
                cl.show(Constants.PANEL_CONT, Constants.PLAYER_1_PANEL);
            } else if(temp.getName().equals("Player 2")){
                cl.show(Constants.PANEL_CONT, Constants.PLAYER_2_PANEL);
            } else if(temp.getName().equals("Player 3")){
                cl.show(Constants.PANEL_CONT, Constants.PLAYER_3_PANEL);
            }else if(temp.getName().equals("Player 4")){
                cl.show(Constants.PANEL_CONT, Constants.PLAYER_4_PANEL);
            } 
        }
        repaint();
    }
    public static void setPlayerCameFrom(Player p){
        temp = p;
    }
}
