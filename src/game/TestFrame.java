package game;

import javax.swing.*;


import panels.*;

import java.awt.*;
import java.util.HashMap;

public class TestFrame extends JFrame{

    private CardLayout cl;
    private HashMap<String,JPanel> panels;

    public TestFrame(String str){
        super(str);
        pack();
        cl = new CardLayout();
        panels = new HashMap<>();
        setUpPanels();
        add(Constants.PANEL_CONT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);
    }

    private void setUpPanels() {

        // Add panels to HashMap
        panels.put(Constants.RULE_PANEL, new RulebookPanel(cl));
        panels.put(Constants.START_PANEL, new StartScreenPanel(cl));
        panels.put(Constants.GAME_PANEL, new GamePanel(cl));
        panels.put(Constants.P1_PANEL, new Player1Panel(cl));
        panels.put(Constants.INSTRUCTIONS_PANEL, new InstructionsPanel(cl));
        panels.put(Constants.END_PANEL, new WinnerPanel(cl, null));

        // Set up layout 
        Constants.PANEL_CONT.setLayout(cl);

        // Add panels to panelCont
        Constants.PANEL_CONT.add(panels.get(Constants.START_PANEL), Constants.START_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.RULE_PANEL), Constants.RULE_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.GAME_PANEL), Constants.GAME_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.INSTRUCTIONS_PANEL), Constants.INSTRUCTIONS_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.END_PANEL), Constants.END_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.P1_PANEL), Constants.P1_PANEL);
        // Show startScreen at beginning
        cl.show(Constants.PANEL_CONT, Constants.START_PANEL);

    }

}
