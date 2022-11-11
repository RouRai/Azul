
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import game.Constants;
public class GamePanel extends JPanel
{
    private BufferedImage background;
    public GamePanel
    {
        background = ImageIO.read(GamePanel.class.getResource(IMG_DIRECTORY+"Azul"))
    }
    private void paintComponent(Graphics g)
    {
        
    }
}