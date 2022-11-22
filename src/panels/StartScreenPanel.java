package panels;

import javax.swing.*;

import game.Constants;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class StartScreenPanel extends JPanel{

    private BufferedImage background;
    private JButton ruleButton;
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

        ruleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(Constants.PANEL_CONT, Constants.RULE_PANEL);
            }
        });

        add(ruleButton, BorderLayout.LINE_END);
    }
    
}   
