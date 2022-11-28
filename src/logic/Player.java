package logic;

import datastructures.LinkedList;

// Author: Rounak Rai (WIP)
public class Player {

    private int currentScore; // The player's current score
    private int completedRows; // The amount of rows the player has completed on the wall
    private Wall wall; // Their Wall
    private PatternLine patternLine; // Their PatternLine
    private FloorLine floorLine; // Their Floor

    public Player(LinkedList<TileObject> lid) {
        floorLine = new FloorLine(lid);
        patternLine = new PatternLine(floorLine);
        wall = new Wall(this);
    }

    public int getScore() {
        return currentScore;
    }

    public void setScore(int s) {
        currentScore = s;
    }

    public int getRowsCompleted() {
        return completedRows;
    }

    public void setCompletedRows(int r) {
        completedRows = r;
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
}
