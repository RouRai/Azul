package panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import datastructures.Box;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import game.Constants;
import game.Coordinates;
import game.Factory;
import tiles.TileObject;

public class GamePanel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton returnStart;
    private BufferedImage background, factory, logo;
    private int factoryWidth, factoryHeight;
    private HashMap<Byte, Factory> factoryMap;
    private Coordinates factoryOne, factoryTwo, factoryThree, factoryFour, factoryFive, factorySix, factorySeven, factoryEight, factoryNine;

    public GamePanel(CardLayout cl) {
        this.cl = cl;
        background = Constants.getImage("Background");
        factory = Constants.getImage("Factory");
        logo = Constants.getImage("AzulLogo");
        setFactoryMap();
        setUpButtons();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paints background
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        // Draws the logo at the center at the top
        g.drawImage(logo, getWidth()/2-getWidth()/10, 0, getWidth()/5, getHeight()/5, this);
        
        // Paints all the factories (excluding factory floor) at their respective locations
        factoryWidth = (int)(getWidth()/7.5);
        factoryHeight = getHeight()/5;

        setCoordinates();
        
        // 1       9
        // 2       8           Which line each factory is drawn on
        // 3 4 5 6 7
        g.drawImage(factory, factoryOne.getX(), factoryOne.getY(), factoryWidth, factoryHeight, this);
        g.drawImage(factory, factoryTwo.getX(), factoryTwo.getY(), factoryWidth, factoryHeight, this);
        g.drawImage(factory, factoryThree.getX(), factoryThree.getY(), factoryWidth, factoryHeight, this);
        g.drawImage(factory, factoryFour.getX(), factoryFour.getY(), factoryWidth, factoryHeight, this);
        g.drawImage(factory, factoryFive.getX(), factoryFive.getY(), factoryWidth, factoryHeight, this);
        g.drawImage(factory, factorySix.getX(), factorySix.getY(), factoryWidth, factoryHeight, this);
        g.drawImage(factory, factorySeven.getX(), factorySeven.getY(), factoryWidth, factoryHeight, this);
        g.drawImage(factory, factoryEight.getX(), factoryEight.getY(), factoryWidth, factoryHeight, this);
        g.drawImage(factory, factoryNine.getX(), factoryNine.getY(), factoryWidth, factoryHeight, this);
        
    } 

    private void setUpButtons() {
        // Instantiates JButtons
        returnStart = new JButton("Return to Start Screen");

        // Adds JButtons to the panels
        add(returnStart, BorderLayout.LINE_END);

        // Adds action listeners to the JButtons
        returnStart.addActionListener(this);
    }

    // Runs this method when something happens to one of the JButtons
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(returnStart)){
            cl.show(Constants.PANEL_CONT, Constants.START_PANEL);
        }
        
    }

    private void setCoordinates() {
        factoryOne = new Coordinates(0, 0);
        factoryTwo = new Coordinates(0, getHeight()/2-factoryHeight/2);
        factoryThree = new Coordinates(0, getHeight()-factoryHeight);
        factoryFour = new Coordinates(getWidth()/4-factoryWidth/2, getHeight()-factoryHeight);
        factoryFive = new Coordinates(getWidth()/2-factoryWidth/2, getHeight()-factoryHeight);
        factorySix = new Coordinates((3 * getWidth()/4) - factoryWidth/2, getHeight()-factoryHeight);
        factorySeven = new Coordinates(getWidth()-factoryWidth, getHeight()-factoryHeight);
        factoryEight = new Coordinates(getWidth()-factoryWidth, getHeight()/2-factoryHeight/2);
        factoryNine = new Coordinates(getWidth()-factoryWidth, 0);
    }

    private void setFactoryMap() {
        factoryMap = new HashMap<>();

        // Adds each of the factories to the map
        factoryMap.put(Constants.FACTORY_ONE, new Factory());
        factoryMap.put(Constants.FACTORY_TWO, new Factory());
        factoryMap.put(Constants.FACTORY_THREE, new Factory());
        factoryMap.put(Constants.FACTORY_FOUR, new Factory());
        factoryMap.put(Constants.FACTORY_FIVE, new Factory());
        factoryMap.put(Constants.FACTORY_SIX, new Factory());
        factoryMap.put(Constants.FACTORY_SEVEN, new Factory());
        factoryMap.put(Constants.FACTORY_EIGHT, new Factory());
        factoryMap.put(Constants.FACTORY_NINE, new Factory());

        setFactoryTiles();
    }

    private void setFactoryTiles() {
        ArrayList<TileObject> possibleTiles = new ArrayList<>();
        
        for(byte i = 0; i < 20; i++) {
            possibleTiles.add(new TileObject(Constants.BLACK_TILE_ID, Constants.BLACK_TILE));
            possibleTiles.add(new TileObject(Constants.BLUE_TILE_ID, Constants.BLUE_TILE));
            possibleTiles.add(new TileObject(Constants.RED_TILE_ID, Constants.RED_TILE));
            possibleTiles.add(new TileObject(Constants.YELLOW_TILE_ID, Constants.YELLOW_TILE));
            possibleTiles.add(new TileObject(Constants.WHITE_TILE_ID, Constants.WHITE_TILE));
        }

        Collections.shuffle(possibleTiles);

        for(byte i = 0; i < 36; i++) {
            byte remain = (byte) (i % 9);
            factoryMap.get(remain).addTile(possibleTiles.get(i));
        }
    }

}