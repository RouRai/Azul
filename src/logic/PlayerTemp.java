package logic;

import java.util.ArrayList;

import datastructures.LinkedList;
import panels.PlayerPanel;
import java.util.*;
public class PlayerTemp implements Comparable<Player>{

    private int currentScore; // The player's current score
    private boolean hasOneTile; // If they are the holder of the Player 1 Tile
    private Wall wall; // Their Wall
    private PatternLine patternLine; // Their PatternLine
    private FloorLine floorLine; // Their Floor
    private String name; // The players name
    public PlayerTemp(String name, ArrayList<TileObject> lid, PlayerPanel p) {
        try {
            floorLine = new FloorLine(lid);
            patternLine = new PatternLine(floorLine);
            this.name = name;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getName() {
        return name;
    }
    public void setScoreOR(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getScore() {
        return currentScore;
    }
    
    public void setScore(int s) {
        currentScore = s;
        if(currentScore < 0) {
            currentScore = 0;
        }
    }

    // Returns if row was completed
    public boolean rowCompleted() {
        return wall.completedRows() > 0;
    }

    // Returns how many rows were completed
    public int getRowsCompleted() {
        return wall.completedRows();
    }

    public Wall getWall() {
        return wall;
    }

    public PatternLine getPatternLine() {
        return patternLine;
    }

    public FloorLine getFloorLine() {
        return floorLine;
    }

    public void setHasOneTile(boolean b) {
        hasOneTile = b;
    }

    public boolean hasOneTile() {
        return hasOneTile;
    }

    @Override
    public int compareTo(Player o) {
        if(getScore() < o.getScore()) return -1;
        if(getScore() > o.getScore()) return 1;
        return 0;
    }
    public String toString(){
        String ret = "";
        HashMap<String, ArrayList<TileObject>> thing = floorLine.getHashMap();
        ret += thing;
        Row[] idk = new Row[5];
            try {
                idk = new Row[]{patternLine.getRow(1), patternLine.getRow(2), patternLine.getRow(3), patternLine.getRow(4), patternLine.getRow(5), patternLine.getRow(6)};
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        ret += "\n";
        ret += "\n";
        for (int i = 0; i < idk.length; i++) {
            Iterator<TileObject> temp = ((Iterable<TileObject>) idk[i]).iterator();
            while (temp.hasNext()) {
                ret += temp.next();
            }
            ret += "\n";
        }


        return ret;
    }
}
