package panels;
import java.awt.*;
import javax.swing.*;

public class TestFrame extends JFrame{
    private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int WIDTH = (int) screenSize.getWidth();
    private static final int HEIGHT = (int) screenSize.getHeight();
    public TestFrame(String str){
        super(str);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        add(new RulebookPanel());
        setVisible(true);
    }
}
