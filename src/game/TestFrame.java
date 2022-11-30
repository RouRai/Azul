package game;

import javax.swing.*;


import panels.*;
import datastructures.*;
import datastructures.Queue;
import logic.TileObject;
import logic.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TestFrame extends JFrame{
    private static Queue<Player> playerQueue;
    private static Player p1, p2, p3, p4;
    private LinkedList<TileObject> lid;
    private CardLayout cl;
    private HashMap<String,JPanel> panels;

    public TestFrame(String str){
        super(str);
        pack();
        cl = new CardLayout();
        panels = new HashMap<>();
        setQueue();
        setUpPanels();
        add(Constants.PANEL_CONT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);
    }
    private void setQueue(){
        playerQueue = new Queue<>();
        lid = new LinkedList<>();
        ArrayList<Player> temp = new ArrayList<>();
        p1 = new Player("Player 1", lid, new PlayerPanel(cl));
        p2 = new Player("Player 2", lid, new PlayerPanel(cl));
        p3 = new Player("Player 3", lid, new PlayerPanel(cl));
        p4 = new Player("Player 4", lid, new PlayerPanel(cl));
        playerQueue.enqueue(p1);
        playerQueue.enqueue(p2);
        playerQueue.enqueue(p3);
        playerQueue.enqueue(p4);
    }
    public static Queue<Player> getQueue(){
        return playerQueue;
    }
    public static Player getP1(){
        return p1;
    }
    public static Player getP2(){
        return p2;
    }
    public static Player getP3(){
        return p3;
    }
    public static Player getP4(){
        return p4;
    }
    private void setUpPanels() {

        // Add panels to HashMap
        panels.put(Constants.RULE_PANEL, new RulebookPanel(cl));
        panels.put(Constants.START_PANEL, new StartScreenPanel(cl));
        panels.put(Constants.GAME_PANEL, new GamePanel(cl));
        panels.put(Constants.PLAYER_1_PANEL, playerQueue.dequeue().getPanel());
        playerQueue.enqueue(p1);
        panels.put(Constants.PLAYER_2_PANEL, playerQueue.dequeue().getPanel());
        playerQueue.enqueue(p2);
        panels.put(Constants.PLAYER_3_PANEL, playerQueue.dequeue().getPanel());
        playerQueue.enqueue(p3);
        panels.put(Constants.PLAYER_4_PANEL, playerQueue.dequeue().getPanel());
        playerQueue.enqueue(p4);
        panels.put(Constants.INSTRUCTIONS_PANEL, new InstructionsPanel(cl));
        panels.put(Constants.END_PANEL, new WinnerPanel(cl, null));
        panels.put(Constants.MAIN_PANEL, new MainPanel(cl));

        // Set up layout 
        Constants.PANEL_CONT.setLayout(cl);

        // Add panels to panelCont
        Constants.PANEL_CONT.add(panels.get(Constants.START_PANEL), Constants.START_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.RULE_PANEL), Constants.RULE_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.GAME_PANEL), Constants.GAME_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.INSTRUCTIONS_PANEL), Constants.INSTRUCTIONS_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.END_PANEL), Constants.END_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.PLAYER_1_PANEL), Constants.PLAYER_1_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.PLAYER_2_PANEL), Constants.PLAYER_2_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.PLAYER_3_PANEL), Constants.PLAYER_3_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.PLAYER_4_PANEL), Constants.PLAYER_4_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.MAIN_PANEL), Constants.MAIN_PANEL);

        // Show startScreen at beginning
        cl.show(Constants.PANEL_CONT, Constants.START_PANEL);


    }

}
