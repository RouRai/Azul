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
    private JButton factory1Button, factory2Button, factory3Button, factory4Button, factory5Button, factory6Button, factory7Button, factory8Button, factory9Button;
    private Factory factory1, factory2, factory3, factory4, factory5, factory6, factory7, factory8, factory9;
    private ImageIcon factoryIcon;

    public GamePanel(CardLayout cl) {
        this.cl = cl;
        setUpImages();
        setUpButtons();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

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

        g.drawString("20", tileOne.getX() + getWidth() / 45, tileOne.getY() + getHeight() / 7);
        g.drawString("20", tileTwo.getX() + getWidth() / 45, tileTwo.getY() + getHeight() / 7);
        g.drawString("20", tileThree.getX() + getWidth() / 45, tileThree.getY() + getHeight() / 7);
        g.drawString("20", tileFour.getX() + getWidth() / 45, tileFour.getY() + getHeight() / 7);
        g.drawString("20", tileFive.getX() + getWidth() / 45, tileFive.getY() + getHeight() / 7);

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
            cl.show(Constants.PANEL_CONT, Constants.PLAYER_PANEL);
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
    }

    // Sets up both of the maps
    private void setFactoryMap() {
        factoryMap = new HashMap<>();

        // Adds each of the factories to the map
        factoryMap.put(Constants.FACTORY_ONE, new Factory(4));
        factoryMap.put(Constants.FACTORY_TWO, new Factory(4));
        factoryMap.put(Constants.FACTORY_THREE, new Factory(4));
        factoryMap.put(Constants.FACTORY_FOUR, new Factory(4));
        factoryMap.put(Constants.FACTORY_FIVE, new Factory(4));
        factoryMap.put(Constants.FACTORY_SIX, new Factory(4));
        factoryMap.put(Constants.FACTORY_SEVEN, new Factory(4));
        factoryMap.put(Constants.FACTORY_EIGHT, new Factory(4));
        factoryMap.put(Constants.FACTORY_NINE, new Factory(4));

        setFactoryTiles();
    }

    // Sets up the tiles in the factories
    private void setFactoryTiles() {
        ArrayList<TileObject> possibleTiles = new ArrayList<>();
        
        for(byte i = 0; i < 20; i++) {
            possibleTiles.add(new TileObject(Constants.BLACK_TILE));
            possibleTiles.add(new TileObject(Constants.BLUE_TILE));
            possibleTiles.add(new TileObject(Constants.RED_TILE));
            possibleTiles.add(new TileObject(Constants.YELLOW_TILE));
            possibleTiles.add(new TileObject(Constants.WHITE_TILE));
        }

        Collections.shuffle(possibleTiles);

        for(byte i = 0; i < 36; i++) {
            byte remain = (byte) (i % 9);
            factoryMap.get(remain).addTile(possibleTiles.get(i));
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
        drawSingleFactoryFloor(g, tileSix, oneTile);
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

        LinkedList<TileObject> tiles = f.getTiles();
        TileObject tile = tiles.getFirst().getItem();

        g.drawImage(Constants.getImage(tile.getType()), xVal + factoryWidth/2 - tileWidth/2, yVal+tileHeight/2, tileWidth/2, tileHeight/2, null);

        g.drawImage(Constants.getImage(tile.getType()), xVal + factoryWidth/2 - tileWidth/2, yVal+tileHeight/2, tileWidth/2, tileHeight/2, null);
        
        g.drawImage(Constants.getImage(tile.getType()), xVal + factoryWidth/2 - tileWidth/2, yVal+tileHeight, tileWidth/2, tileHeight/2, null);
        
        g.drawImage(Constants.getImage(tile.getType()), xVal + factoryWidth/2, yVal+tileHeight/2, tileWidth/2, tileHeight/2, null);
        
        g.drawImage(Constants.getImage(tile.getType()), xVal + factoryWidth/2, yVal+tileHeight, tileWidth/2, tileHeight/2, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x < (int)(getWidth()/7.5)){
            if(y < (int)(getHeight()/5)){
                
            } else if (y < getHeight()/2-factoryHeight/2 + (int)(getHeight()/5)){

            } else {

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

}