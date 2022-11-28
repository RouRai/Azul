package logic;

import datastructures.LinkedList;

// Author: Rounak Rai (WIP)
public class Player {

    private int currentScore; // The player's current score
    private boolean hasOneTile;
    private Wall wall; // Their Wall
    private PatternLine patternLine; // Their PatternLine
    private FloorLine floorLine; // Their Floor

    public Player(LinkedList<TileObject> lid) {
        floorLine = new FloorLine(lid);
        patternLine = new PatternLine(floorLine);
        wall = new Wall(this, floorLine);
    }

    public int getScore() {
        return currentScore;
    }

    public void setScore(int s) {
        currentScore = s;
    }

    public boolean rowCompleted() {
        return wall.completedRows() > 0;
    }

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
