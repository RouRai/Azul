package game;
import panels.*;
import java.awt.*;
import javax.swing.*;
public class WinnerFrame extends JFrame{
    public WinnerFrame(String urmom) {
        super(urmom);
        add(new WinnerPanel(null, null));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);
    }
 }
