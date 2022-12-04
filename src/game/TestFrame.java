package game;

import javax.swing.*;

import datastructures.LinkedList;
import logic.Player;
import logic.TileObject;
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
    private static Player p1, p2, p3, p4, currentPlayer;
    private LinkedList<TileObject> lid;
    private static CardLayout cl;
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
    public static Player getNextPlayer()
    {
    	currentPlayer = playerQueue.dequeue();
    	playerQueue.enqueue(currentPlayer);
    	return currentPlayer;
    }
    public static String getPlayerName()
    {
    	if(currentPlayer.equals(p1))
    		return Constants.PLAYER_1_PANEL;
    	else if(currentPlayer.equals(p2))
    		return Constants.PLAYER_2_PANEL;
    	else if(currentPlayer.equals(p3))
    		return Constants.PLAYER_3_PANEL;
    	else if(currentPlayer.equals(p4))
    		return Constants.PLAYER_4_PANEL;
    	return "";
    }
    public static Player getCurrentPlayer()
    {
    	return currentPlayer;
    }
    private void setUpPanels() {

        // Add panels to HashMap
        panels.put(Constants.RULE_PANEL, new RulebookPanel(cl));
        panels.put(Constants.START_PANEL, new StartScreenPanel(cl));
        panels.put(Constants.GAME_PANEL, new GamePanel(cl));
        panels.put(Constants.PLAYER_1_PANEL, p1.getPanel());
        panels.put(Constants.PLAYER_2_PANEL, p2.getPanel());
        panels.put(Constants.PLAYER_3_PANEL, p3.getPanel());
        panels.put(Constants.PLAYER_4_PANEL, p4.getPanel());
        panels.put(Constants.INSTRUCTIONS_PANEL, new InstructionsPanel(cl));
        panels.put(Constants.MAIN_PANEL, new MainPanel(cl));
        panels.put(Constants.END_PANEL, new WinnerPanel(cl, null));

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
    public static void nextPlayer(){
        Player temp = playerQueue.dequeue();
        if(temp.getName().equals("Player 1")){
            cl.show(Constants.PANEL_CONT, Constants.PLAYER_1_PANEL);
        } else if(temp.getName().equals("Player 2")){
            cl.show(Constants.PANEL_CONT, Constants.PLAYER_2_PANEL);
        } else if(temp.getName().equals("Player 3")){
            cl.show(Constants.PANEL_CONT, Constants.PLAYER_3_PANEL);
        } else if(temp.getName().equals("Player 4")){
            cl.show(Constants.PANEL_CONT, Constants.PLAYER_4_PANEL);
        }
        playerQueue.enqueue(temp);
    }

}
