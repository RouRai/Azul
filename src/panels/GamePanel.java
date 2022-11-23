package panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.Constants;
import game.Coordinates;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton returnStart;
    private Coordinates boardOne, boardTwo, boardThree, boardFour;
    private BufferedImage boardImage;

    public GamePanel(CardLayout cl) {
        this.cl = cl;
        boardImage = Constants.getImage("AzulBoard");
        setUpButtons();
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
        boardOne = new Coordinates(0, 0);
        boardTwo = new Coordinates(, ABORT)
    }


}