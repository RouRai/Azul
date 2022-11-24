package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import game.Constants;
import game.Coordinates;
import game.Factory;

public class GamePanel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton returnStart;
    private BufferedImage background, factory, logo;
    private int factoryWidth, factoryHeight;
    private HashMap<String, Factory> factoryMap;
    private Coordinates factoryOne, factoryTwo, factoryThree, factoryFour, factoryFive, factorySix, factorySeven, factoryEight, factoryNine;

    public GamePanel(CardLayout cl) {
        this.cl = cl;
        background = Constants.getImage("Background");
        factory = Constants.getImage("Factory");
        logo = Constants.getImage("AzulLogo");
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
        // 2       8           Which line each factory is drawn
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

    }

}