package game;

import javax.swing.*;


import panels.*;

import java.awt.*;
import java.util.HashMap;
public class MainFrame extends JFrame{
    private CardLayout cl;
    public MainFrame(String str){
        //DO NOT USE THIS CLASS
        super(str);
        cl = new CardLayout();
        add(new MainPanel(cl));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);
    }
}