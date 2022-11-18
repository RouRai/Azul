
package panel;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;


public class GamePanel extends JPanel
{
    private BufferedImage background;
    public GamePanel()
    {
    	try
    	{
    		background = ImageIO.read(GamePanel.class.getResource("/images/Background.jpg"));
    		
    	}
    	catch(Exception E)
    	{
    		System.out.println(-1);
    	}
    }
    public void paintComponent(Graphics g)
    {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}
