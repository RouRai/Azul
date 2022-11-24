package panels;
import java.awt.*;
import javax.swing.*;
import game.Constants;
public class MainTempFrame extends JFrame{
    public MainTempFrame(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);
        add(new MainPanel(null));
    }
}
