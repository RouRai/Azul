package game;
import javax.swing.*;
import java.awt.*;
public class Azul extends JFrame{
    public Azul(String name){
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        add(new GamePanel());
        add(new StartScreenPanel());
        setVisible(true);
    }
    
    private void setUp()
    {
        
    }
    
}
