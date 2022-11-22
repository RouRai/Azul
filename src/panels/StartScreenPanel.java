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

    }

    private void setUpButtons() {
        ruleButton = new JButton("Rules");
        gameButton = new JButton("Start");

        ruleButton.addActionListener(this);
        gameButton.addActionListener(this);

        add(ruleButton, BorderLayout.LINE_END);
        add(gameButton, BorderLayout.LINE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(gameButton)){
            cl.show(Constants.PANEL_CONT, Constants.GAME_PANEL);
        } else if (e.getSource().equals(ruleButton)) {
            cl.show(Constants.PANEL_CONT, Constants.RULE_PANEL);
        }
        
    }

    
}   
