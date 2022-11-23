package panels;

import javax.swing.*;

import game.Constants;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class StartScreenPanel extends JPanel implements ActionListener{

    private BufferedImage background;
    private JButton ruleButton, gameButton;
    private CardLayout cl;

    public StartScreenPanel(CardLayout cl) {
        this.cl = cl;
        background = Constants.getImage("AzulStartScreenBackground");
        setUpButtons();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        gameButton.setBounds((int)(getWidth() / 2.3), (int)(getHeight() / 1.2), getWidth() / 8, getHeight() / 14);
        ruleButton.setBounds((int)(getWidth() / 1.2), (int)(getHeight() / 2), getWidth() / 10, getHeight() / 20);

    }

    // Sets up the various buttons
    private void setUpButtons() {
        // Instantiates them
        ruleButton = new JButton("Rules");
        gameButton = new JButton("Start");
        super.add(ruleButton);
        super.add(gameButton);
        // Adds action listeners to them so they can record actions
        ruleButton.addActionListener(this);
        gameButton.addActionListener(this);

        // Adds buttons to the panels
    }


    // When something happens to button, it does one of the following actions depending on the source
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(gameButton)){
            cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
        } else if (e.getSource().equals(ruleButton)) {
            cl.show(Constants.PANEL_CONT, Constants.RULE_PANEL);
        }
    }
    

    
}  