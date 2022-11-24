package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;

import game.Constants;

public class Player2Panel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton returnStart;
    private BufferedImage background, gameBoard, factory, blackT, blueT, oneT, redT, yellowT, whiteT, greyTint;
    private JButton Logs;

    public Player2Panel(CardLayout cl) {
        this.cl = cl;
        background = Constants.getImage("Background");
        setUpButtons();
        background = Constants.getImage("Background");
        gameBoard = Constants.getImage("AzulBoard");
        factory = Constants.getImage("Factory");
        blackT = Constants.getImage("AzulTileBlack");
        blueT = Constants.getImage("AzulTileBlue");
        oneT = Constants.getImage("AzulTileOne");
        redT = Constants.getImage("AzulTileRed");
        yellowT = Constants.getImage("AzulTileYellow");
        whiteT = Constants.getImage("AzulTileWhite");
        greyTint = Constants.getImage("GreyFIlter");

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(gameBoard, 0, 0, (int)(getWidth() / 3), (int)(getWidth() / 3), null);
        g.drawImage(greyTint, 0, 0, (int)(getWidth() / 3), (int)(getWidth() / 3), null);
        Logs.setBounds((int)(getWidth() / 1.3), (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
    }

    private void setUpButtons() {
        // Instantiates JButtons
        returnStart = new JButton("Return to Start Screen");
        Logs = new JButton("Logs");
        // Adds JButtons to the panels
        add(returnStart, BorderLayout.LINE_END);
        super.add(Logs);
        // Adds action listeners to the JButtons
        returnStart.addActionListener(this);
        Logs.addActionListener(this);
    }

    // Runs this method when something happens to one of the JButtons
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(returnStart)){
            cl.show(Constants.PANEL_CONT, Constants.START_PANEL);
        }
        
    }


}