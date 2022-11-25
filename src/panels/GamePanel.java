package panels;

import javax.swing.ImageIcon;
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
    private ImageIcon icon;

    public GamePanel(CardLayout cl) {
        this.cl = cl;
        setUpImages();
        setCoordinates();
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

        // Paints the factory floor
        tileWidth = getWidth()/16;
        tileHeight = getHeight()/10;

        factory1Button.setBounds(factoryOne.getX(), factoryOne.getY(), factoryWidth, factoryHeight);
        factory2Button.setBounds(factoryTwo.getX(), factoryTwo.getY(), factoryWidth, factoryHeight);
        factory3Button.setBounds(factoryThree.getX(), factoryThree.getY(), factoryWidth, factoryHeight);
        factory4Button.setBounds(factoryFour.getX(), factoryFour.getY(), factoryWidth, factoryHeight);
        factory5Button.setBounds(factoryFive.getX(), factoryFive.getY(), factoryWidth, factoryHeight);
        factory6Button.setBounds(factorySix.getX(), factorySix.getY(), factoryWidth, factoryHeight);
        factory7Button.setBounds(factorySeven.getX(), factorySeven.getY(), factoryWidth, factoryHeight);
        factory8Button.setBounds(factoryEight.getX(), factoryEight.getY(), factoryWidth, factoryHeight);
        factory9Button.setBounds(factoryNine.getX(), factoryNine.getY(), factoryWidth, factoryHeight);

        add(factory1Button);
        add(factory2Button);
        add(factory3Button);
        add(factory4Button);
        add(factory5Button);
        add(factory6Button);
        add(factory7Button);
        add(factory8Button);
        add(factory9Button);

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
        icon = new ImageIcon(Constants.IMG_DIRECTORY + "Factory.jpg");
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
        buttonFactory = new HashMap<>();

        factory1Button = new JButton(icon);
        factory2Button = new JButton(icon);
        factory3Button = new JButton(icon);
        factory4Button = new JButton(icon);
        factory5Button = new JButton(icon);
        factory6Button = new JButton(icon);
        factory7Button = new JButton(icon);
        factory8Button = new JButton(icon);
        factory9Button = new JButton(icon);

        buttonFactory.put(factory1Button, factoryMap.get(Constants.FACTORY_ONE));
        buttonFactory.put(factory2Button, factoryMap.get(Constants.FACTORY_TWO));
        buttonFactory.put(factory3Button, factoryMap.get(Constants.FACTORY_THREE));
        buttonFactory.put(factory4Button, factoryMap.get(Constants.FACTORY_FOUR));
        buttonFactory.put(factory5Button, factoryMap.get(Constants.FACTORY_FIVE));
        buttonFactory.put(factory6Button, factoryMap.get(Constants.FACTORY_SIX));
        buttonFactory.put(factory7Button, factoryMap.get(Constants.FACTORY_SEVEN));
        buttonFactory.put(factory8Button, factoryMap.get(Constants.FACTORY_EIGHT));
        buttonFactory.put(factory9Button, factoryMap.get(Constants.FACTORY_NINE));

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