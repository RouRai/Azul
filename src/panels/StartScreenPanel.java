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

        // Draw logo
        //int startWidth = getWidth() / 2-getWidth() / 8;
        //int startHeight = getHeight() / 8;
        //int endWidth = getWidth() / 2 + getWidth() / 8;
        //int endHeight = getHeight() / 8 + getHeight() / 16;
        //g.drawImage(logo, startWidth, startHeight, endWidth, endHeight, null);
    }
    
}   
