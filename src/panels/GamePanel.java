package panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.Constants;
import game.CoordinatePair;
import game.Coordinates;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton returnStart;
    private CoordinatePair boardOne, boardTwo, boardThree, boardFour;
    private BufferedImage background, boardImage;

    public GamePanel(CardLayout cl) {
        this.cl = cl;
        boardImage = Constants.getImage("AzulBoard");
        background = Constants.getImage("Background");
        setBoardCoordinates();
        setUpButtons();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paints background
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        
        // Paints all the boards at their respective locations
        g.drawImage(boardImage, boardOne.getStartCoordinates().getX(), boardOne.getStartCoordinates().getY(), boardOne.getEndCoordinates().getX(),boardOne.getEndCoordinates().getY(), null);
        g.drawImage(boardImage, boardTwo.getStartCoordinates().getX(), boardTwo.getStartCoordinates().getY(), boardTwo.getEndCoordinates().getX(),boardTwo.getEndCoordinates().getY(), null);
        g.drawImage(boardImage, boardThree.getStartCoordinates().getX(), boardThree.getStartCoordinates().getY(), boardThree.getEndCoordinates().getX(),boardThree.getEndCoordinates().getY(), null);
        g.drawImage(boardImage, boardFour.getStartCoordinates().getX(), boardFour.getStartCoordinates().getY(), boardFour.getEndCoordinates().getX(),boardFour.getEndCoordinates().getY(), null);
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

    private void setBoardCoordinates() {
        // Sets up start and end coordinates for the Player 1 Board (top left)
        boardOne = new CoordinatePair(new Coordinates(0, 0), new Coordinates(boardImage.getWidth(), boardImage.getHeight()));
        
        // Sets up start and end coordinates for the Player 2 Board (top right)
        boardTwo = new CoordinatePair(new Coordinates(getWidth()-boardImage.getWidth(), 0), new Coordinates(getWidth(), boardImage.getHeight()));

        // Sets up start and end coordinates for the Player 3 Board (bottom right)
        boardThree = new CoordinatePair(new Coordinates(getWidth()-boardImage.getWidth(), getHeight()-boardImage.getHeight()), new Coordinates(getWidth(), getHeight()));

        // Sets up start and end coordinates for the Player 4 Board (bottom left)
        boardFour = new CoordinatePair(new Coordinates(0, getHeight()-boardImage.getHeight()), new Coordinates(boardImage.getWidth(), getHeight()));
    }


}