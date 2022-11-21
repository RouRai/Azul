package panels;
import javax.swing.*;

import game.Constants;

public class TestFrame extends JFrame{

    

    public TestFrame(String str){
        super(str);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        add(new StartScreenPanel());
        setVisible(true);
    }


}
