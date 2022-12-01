package panels;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//import datastructures.LinkedList;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import logic.*;
import game.*;
//import logic.*;
import datastructures.*;
public class GamePanel extends JPanel implements ActionListener, MouseListener{

    private CardLayout cl;
    private JButton returnStart;
    private BufferedImage background, factory, logo, redTile, yellowTile, whiteTile, blueTile, blackTile, oneTile;
    private int factoryWidth, factoryHeight, tileWidth, tileHeight;
    private HashMap<Byte, Factory> factoryMap;
    private Coordinates factoryOne, factoryTwo, factoryThree, factoryFour, factoryFive, factorySix, factorySeven, factoryEight, factoryNine;
    private Coordinates tileOne, tileTwo, tileThree, tileFour, tileFive, tileSix;
    //private Factory factory1, factory2, factory3, factory4, factory5, factory6, factory7, factory8, factory9;
    private FactoryFloor floor;
    private ImageIcon factoryIcon;
    private static Player temp;
    private bagClass bag;

    public GamePanel(CardLayout cl) {
        this.cl = cl;
        System.out.println("hi");
        setUpImages();
        setUpButtons();
        setFactoryMap();
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Italics", Font.ITALIC, 40));
        // Paints background
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        // Draws the logo at the center at the top
        g.drawImage(logo, getWidth()/2-getWidth()/10, 0, getWidth()/5, getHeight()/5, this); 
        
        // Paints all the factories (excluding factory floor) at their respective locations
        factoryWidth = (int)(getWidth()/7.5); 
        factoryHeight = (int)(getHeight()/5);
        
        tileWidth = getWidth()/16;
        tileHeight = getHeight()/10;

        setCoordinates();

        //drawFactoryFloor(g);

        // 1       9
        // 2       8           Which factory is drawn at which location
        // 3 4 5 6 7
        drawFactories(g);

        // Paints the factory floor
        tileWidth = getWidth()/16;
        tileHeight = getHeight()/10;

        drawFactoryFloor(g);

        g.drawString("" + floor.getNumTiles(Constants.BLUE_TILE), tileOne.getX() + getWidth() / 45, tileOne.getY() + getHeight() / 7);
        g.drawString("" + floor.getNumTiles(Constants.RED_TILE), tileTwo.getX() + getWidth() / 45, tileTwo.getY() + getHeight() / 7);
        g.drawString("" + floor.getNumTiles(Constants.YELLOW_TILE), tileThree.getX() + getWidth() / 45, tileThree.getY() + getHeight() / 7);
        g.drawString("" + floor.getNumTiles(Constants.BLACK_TILE), tileFour.getX() + getWidth() / 45, tileFour.getY() + getHeight() / 7);
        g.drawString("" + floor.getNumTiles(Constants.WHITE_TILE), tileFive.getX() + getWidth() / 45, tileFive.getY() + getHeight() / 7);

        paintTiles(factoryMap.get(Constants.FACTORY_ONE), factoryOne, g);
        paintTiles(factoryMap.get(Constants.FACTORY_TWO), factoryTwo, g);
        paintTiles(factoryMap.get(Constants.FACTORY_THREE), factoryThree, g);
        paintTiles(factoryMap.get(Constants.FACTORY_FOUR), factoryFour, g);
        paintTiles(factoryMap.get(Constants.FACTORY_FIVE), factoryFive, g);
        paintTiles(factoryMap.get(Constants.FACTORY_SIX), factorySix, g);
        paintTiles(factoryMap.get(Constants.FACTORY_SEVEN), factorySeven, g);
        paintTiles(factoryMap.get(Constants.FACTORY_EIGHT), factoryEight, g);
        paintTiles(factoryMap.get(Constants.FACTORY_NINE), factoryNine, g);
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
    }

    private void setUpButtons() {
        // Instantiates JButtons
        returnStart = new JButton("Return to Player Screen");

        // Adds JButtons to the panels
        add(returnStart, BorderLayout.LINE_END);

        // Adds action listeners to the JButtons
        returnStart.addActionListener(this);
    }

    // Runs this method when something happens to one of the JButtons
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(returnStart)){
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
    }

    // Sets up all the coordinates in this panel
    private void setCoordinates() {
        // Sets up factory coordinates
        factoryOne = new Coordinates(0, 0);
        factoryTwo = new Coordinates(0, getHeight()/2-factoryHeight/2);
        factoryThree = new Coordinates(0, getHeight()-factoryHeight);
        factoryFour = new Coordinates(getWidth()/4-factoryWidth/2, getHeight()-factoryHeight);
        factoryFive = new Coordinates(getWidth()/2-factoryWidth/2, getHeight()-factoryHeight);
        factorySix = new Coordinates((3 * getWidth()/4)-factoryWidth/2, getHeight()-factoryHeight);
        factorySeven = new Coordinates(getWidth()-factoryWidth, getHeight()-factoryHeight);
        factoryEight = new Coordinates(getWidth()-factoryWidth, getHeight()/2-factoryHeight/2);
        factoryNine = new Coordinates(getWidth()-factoryWidth, 0);

        // Sets up tile coordinates
        tileOne = new Coordinates(getWidth()/2-tileWidth/2, getHeight()/2-tileHeight/2);
        tileTwo = new Coordinates(getWidth()/4-tileWidth/2, getHeight()/2-tileHeight/2);
        tileThree = new Coordinates(getWidth()/3, getHeight()/2-tileHeight/2);
        tileFour = new Coordinates(2 * getWidth()/3, getHeight()/2-tileHeight/2);
        tileFive = new Coordinates(getWidth()/2 + tileWidth, getHeight()/2-tileHeight/2);
        tileSix = new Coordinates((getWidth() / 2) - (tileWidth / 2), getHeight() / 4);
    }

    // Sets up both of the maps
    private void setFactoryMap() {
        factoryMap = new HashMap<>();
        floor = new FactoryFloor();
        // Adds each of the factories to the map
        factoryMap.put((byte)0, new Factory(4));
        factoryMap.put((byte)1, new Factory(4));
        factoryMap.put((byte)2, new Factory(4));
        factoryMap.put((byte)3, new Factory(4));
        factoryMap.put((byte)4, new Factory(4));
        factoryMap.put((byte)5, new Factory(4));
        factoryMap.put((byte)6, new Factory(4));
        factoryMap.put((byte)7, new Factory(4));
        factoryMap.put((byte)8, new Factory(4));

        setFactoryTiles();
    }

    // Sets up the tiles in the factories
    private void setFactoryTiles() {
        bag = new bagClass();
        bag.fillBag();
        floor.addTile(new TileObject(Constants.ONE_TILE));
        floor.setHasOneTile(true);
        for(byte i = 0; i < 36; i++) {
            byte remain = (byte) (i % 9);
            factoryMap.get(remain).addTile(bag.get());
            System.out.println("Added tile to factory" + remain);
        }
    }

    // Draw factories 
    private void drawFactories(Graphics g) {
        drawFactory(g, factoryOne);
        drawFactory(g, factoryTwo);
        drawFactory(g, factoryThree);
        drawFactory(g, factoryFour);
        drawFactory(g, factoryFive);
        drawFactory(g, factorySix);
        drawFactory(g, factorySeven);
        drawFactory(g, factoryEight);
        drawFactory(g, factoryNine);
    }

    private void drawFactory(Graphics g, Coordinates coordinates) {
        g.drawImage(factory, coordinates.getX(), coordinates.getY(), factoryWidth, factoryHeight, null);
    }

    // Draws the factory floor
    private void drawFactoryFloor(Graphics g) {
        //drawSingleFactoryFloor(g, tileSix, oneTile);
        if(floor.hasOneTile()){
            drawSingleFactoryFloor(g, tileSix, oneTile);
        }
        drawSingleFactoryFloor(g, tileOne, blueTile);
        drawSingleFactoryFloor(g, tileTwo, redTile);
        drawSingleFactoryFloor(g, tileThree, yellowTile);
        drawSingleFactoryFloor(g, tileFour, blackTile);
        drawSingleFactoryFloor(g, tileFive, whiteTile);
    }

    private void drawSingleFactoryFloor(Graphics g, Coordinates coordinates, BufferedImage img) {
        g.drawImage(img, coordinates.getX(), coordinates.getY(), tileWidth, tileHeight, null);
    }

    // Paint hte tiles from a factory and its coordinates
    private void paintTiles(Factory f, Coordinates c, Graphics g) {
        int xVal = c.getX();
        int yVal = c.getY();

        ArrayList<TileObject> tiles = f.getTiles();
        //TileObject tile = tiles.getFirst().getItem();
       // Iterator<TileObject> tiles = ti.iterator();
        g.drawImage(Constants.getImage(tiles.get(0).getType()), xVal + factoryWidth/2 - tileWidth/2, yVal+tileHeight/2, tileWidth/2, tileHeight/2, null);
        
        g.drawImage(Constants.getImage((tiles.get(1)).getType()), xVal + factoryWidth/2 - tileWidth/2, yVal+tileHeight, tileWidth/2, tileHeight/2, null);
        
        g.drawImage(Constants.getImage(tiles.get(2).getType()), xVal + factoryWidth/2, yVal+tileHeight/2, tileWidth/2, tileHeight/2, null);
        
        g.drawImage(Constants.getImage(tiles.get(3).getType()), xVal + factoryWidth/2, yVal+tileHeight, tileWidth/2, tileHeight/2, null);
    }

    public void mouseClicked(MouseEvent e) 
    {
        int x = e.getX();
        int y = e.getY();
            Factory chosen = null;
            if(x < factoryWidth)
            {
                if(y > 0 && y<factoryHeight && factoryMap.get((byte)0) != null)
                    chosen = factoryMap.get((byte)0);//factory1;
                else if (y > factoryTwo.getY() && y<factoryTwo.getY()+factoryHeight && factoryMap.get((byte)1) != null)
            	    chosen = factoryMap.get((byte)1);//factory2;
                else if(y > factoryThree.getY()&& y < factoryThree.getY()+factoryHeight && factoryMap.get((byte)2) != null)
            	    chosen = factoryMap.get((byte)2);//factory3;
            }
            else if (x>getWidth()-factoryWidth)
            {
        	    if(y > 0 && y<factoryHeight && factoryMap.get((byte)8) != null)
                    chosen = factoryMap.get((byte)8);//factory9;
                else if (y > factoryTwo.getY() && y<factoryTwo.getY()+factoryHeight && factoryMap.get((byte)7) != null)
            	    chosen = factoryMap.get((byte)7);//factory8;
                else if(y > factoryThree.getY()&& y < factoryThree.getY()+factoryHeight && factoryMap.get((byte)6) != null)
            	    chosen = factoryMap.get((byte)6);//factory7;
            }
            else if(y > factoryThree.getY())
            {
        	    if(x>factoryFour.getX() && x<factoryFour.getX()+factoryWidth && factoryMap.get((byte)3) != null)
        		    chosen = factoryMap.get((byte)3);//factory4;
        	    else if(x>factoryFive.getX() && x<factoryFour.getX()+factoryWidth && factoryMap.get((byte)4) != null)
        		    chosen = factoryMap.get((byte)4);//factory5;
        	    else if(x>factorySix.getX() && x<factorySix.getX()+factoryWidth && factoryMap.get((byte)5) != null)
        		    chosen = factoryMap.get((byte)5); //factory6;
            } else if((floor.getSize() > 1 && floor.hasOneTile()) || (floor.getSize() > 0 && !floor.hasOneTile())){
                chosen = floor;
            }
            //temp.getPanel().addFactory(chosen);
            //method to put chosen factory into player panel
            if(chosen != null){
                temp.getPanel().addFactory(chosen);
                temp.getPanel().changeChoseTile();
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
    public static void setPlayerCameFrom(Player p){
        temp = p;
    }
}
