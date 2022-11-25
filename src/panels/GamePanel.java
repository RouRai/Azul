package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
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
    private BufferedImage background, factory, logo, redTile, yellowTile, whiteTile, blueTile, blackTile;
    private int factoryWidth, factoryHeight, tileWidth, tileHeight;
    private HashMap<Byte, Factory> factoryMap;
    private HashMap<JButton, Factory> buttonFactory;
    private Coordinates factoryOne, factoryTwo, factoryThree, factoryFour, factoryFive, factorySix, factorySeven, factoryEight, factoryNine;
    private JButton factory1Button, factory2Button, factory3Button, factory4Button, factory5Button, factory6Button, factory7Button, factory8Button, factory9Button;
    private Factory factory1, factory2, factory3, factory4, factory5, factory6, factory7, factory8, factory9;

    public GamePanel(CardLayout cl) {
        this.cl = cl;
        setUpImages();
        setFactoryMap();
        setUpButtons();
        setFactoryButtons();
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

        // Paints the factory floor
        tileWidth = getWidth()/16;
        tileHeight = getHeight()/10;

        g.drawImage(blueTile, getWidth()/2-tileWidth/2, getHeight()/2-tileHeight/2, tileWidth, tileHeight, this);
        g.drawImage(redTile, getWidth()/4-tileWidth/2, getHeight()/2-tileHeight/2, tileWidth, tileHeight, this);
        g.drawImage(yellowTile, getWidth()/3, getHeight()/2-tileHeight/2, tileWidth, tileHeight, returnStart);
        g.drawImage(blackTile, 2*getWidth()/3, getHeight()/2-tileHeight/2, tileWidth, tileHeight, returnStart);
        g.drawImage(whiteTile, getWidth()/2 + tileWidth, getHeight()/2-tileHeight/2, tileWidth, tileHeight, this);
        
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

    private void setCoordinates() {
        factoryOne = new Coordinates(0, 0);
        factoryTwo = new Coordinates(0, getHeight()/2-factoryHeight/2);
        factoryThree = new Coordinates(0, getHeight()-factoryHeight);
        factoryFour = new Coordinates(getWidth()/4-factoryWidth/2, getHeight()-factoryHeight);
        factoryFive = new Coordinates(getWidth()/2-factoryWidth/2, getHeight()-factoryHeight);
        factorySix = new Coordinates((3 * getWidth()/4)-factoryWidth/2, getHeight()-factoryHeight);
        factorySeven = new Coordinates(getWidth()-factoryWidth, getHeight()-factoryHeight);
        factoryEight = new Coordinates(getWidth()-factoryWidth, getHeight()/2-factoryHeight/2);
        factoryNine = new Coordinates(getWidth()-factoryWidth, 0);
    }
    private void setFactoryButtons() {
        factory1Button = new JButton("Button1");
        factory2Button = new JButton("Button2");
        factory3Button = new JButton("Button3");
        factory4Button = new JButton("Button4");
        factory5Button = new JButton("Button5");
        factory6Button = new JButton("Button6");
        factory7Button = new JButton("Button7");
        factory8Button = new JButton("Button8");
        factory9Button = new JButton("Button9");
        factory1 = new Factory();
        factory2 = new Factory();
        factory3 = new Factory();
        factory4 = new Factory();
        factory5 = new Factory();
        factory6 = new Factory();
        factory7 = new Factory();
        factory8 = new Factory();
        factory9 = new Factory();
        buttonFactory = new HashMap<>();
        buttonFactory.put(factory1Button, factory1);
        buttonFactory.put(factory2Button, factory2);
        buttonFactory.put(factory3Button, factory3);
        buttonFactory.put(factory4Button, factory4);
        buttonFactory.put(factory5Button, factory5);
        buttonFactory.put(factory6Button, factory6);
        buttonFactory.put(factory7Button, factory7);
        buttonFactory.put(factory8Button, factory8);
        buttonFactory.put(factory9Button, factory9);
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