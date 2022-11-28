package logic;

import java.util.HashMap;

import datastructures.LinkedList;
import game.Constants;

// Author: Rounak Rai
public class FloorLine {
    
    private HashMap<String, LinkedList<TileObject>> tiles; // Associates Penalty with tiles in that penalty
    private int totalPenalty; // The total penalty of everything added up
    private LinkedList<TileObject> lid; // Excess tiles that can't be placed in the FloorLine are placed in the lid

    public FloorLine(LinkedList<TileObject> lid) {
        this.lid = lid;
        setUpHashMap();
    }

    // Adds a tile to the penalty line
    public void addTile(TileObject t) {
        if(decidePenalty() != null) {
            totalPenalty += decidePenaltyInt(decidePenalty());
            tiles.get(decidePenalty()).add(t);
            return;
        }
        lid.add(t);
    }

    // Decides how much to add to the totalPenalty
    private int decidePenaltyInt(String s) {
        if(s.equals(Constants.PENALTY_ONE)) {
            return 1;
        }
        if(s.equals(Constants.PENALTY_TWO)) {
            return 2;
        }
        if(s.equals(Constants.PENALTY_THREE)) {
            return 3;
        }
        return 0;
    }

    // Adds multiple tiles to the floorline in the form of a LinkedList
    public void addTiles(LinkedList<TileObject> t) {
        for(TileObject obj : t) {
            addTile(obj);
        }
    }

    // Returns the tiles associated with a specific penalty
    public LinkedList<TileObject> getTileList(String penalty) {
        if(tiles.containsKey(penalty)) {
            return tiles.get(penalty);
        }
        return null;
    }

    // Returns the whole penalty HashMap
    public HashMap<String, LinkedList<TileObject>> getHashMap() {
        return tiles;
    }

    // Returns if the floorLine is full
    public boolean isFull() {
        return decidePenalty() == null;
    }

    // Returns the total penalty
    public int getPenalty() {
        return totalPenalty;
    }

    // Sets the penalty
    public void setPenalty(int b) {
        totalPenalty = b;
    } 

    // Adds to the penalty given an integer
    public void addPenalty(int b) {
        setPenalty(b + getPenalty());
    }

    // Decides the penalty for the next tile to be added to the floor line
    private String decidePenalty() {
        if(tiles.get(Constants.PENALTY_ONE).getSize() < 2) {
            return Constants.PENALTY_ONE;
        }
        if(tiles.get(Constants.PENALTY_TWO).getSize() < 3) {
            return Constants.PENALTY_TWO;
        }
        if(tiles.get(Constants.PENALTY_THREE).getSize() < 2) {
            return Constants.PENALTY_THREE;
        }
        return null;
    }

    // Sets up the tile hashmap with the penalty and linked list
    private void setUpHashMap() {
        tiles.put(Constants.PENALTY_ONE, new LinkedList<TileObject>());
        tiles.put(Constants.PENALTY_TWO, new LinkedList<TileObject>());
        tiles.put(Constants.PENALTY_THREE, new LinkedList<TileObject>());
    }

    // Discards all the tiles in the floor and sets the penalty to 0
    public void discardFloor() {
        totalPenalty = 0;
        tiles = new HashMap<>();
    }
}
