package logic;

import datastructures.LinkedList;

// Author: Rounak Rai (WIP)
public class Player {

    private int currentScore; // The player's current score
    private boolean hasOneTile; // If they are the holder of the Player 1 Tile
    private Wall wall; // Their Wall
    private PatternLine patternLine; // Their PatternLine
    private FloorLine floorLine; // Their Floor
    private String name; // The players name

    public Player(String name, LinkedList<TileObject> lid) {
        floorLine = new FloorLine(lid);
        patternLine = new PatternLine(floorLine);
        wall = new Wall(this, floorLine);
        this.name = name;
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
}
