package game;
import java.awt.*;
import javax.swing.*;
import panels.*;
public class PlayerFrame extends JFrame{
    public PlayerFrame(String name) {
        super(name);
        add(new PlayerPanel(null));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);
    }
}
