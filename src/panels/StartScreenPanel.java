package panels;

import javax.swing.*;

import game.Constants;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class StartScreenPanel extends JPanel{

    private BufferedImage background;
    public StartScreenPanel() {
        background = Constants.getImage("AzulStartScreenBackground");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

    }
    
}   
