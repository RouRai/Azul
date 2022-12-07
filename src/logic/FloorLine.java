package logic;

import java.util.HashMap;
import java.util.*;
import datastructures.LinkedList;
import game.Constants;

public class FloorLine {
    
    private HashMap<String, ArrayList<TileObject>> tiles; // Associates Penalty with tiles in that penalty
    private int totalPenalty; // The total penalty of everything added up
    private ArrayList<TileObject> lid; // Excess tiles that can't be placed in the FloorLine are placed in the lid

    public FloorLine(ArrayList<TileObject> lid) {
        tiles = new HashMap<>();
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
    public void addTiles(ArrayList<TileObject> t) {
        for(TileObject obj : t) {
            addTile(obj);
        }
    }

    // Returns the tiles associated with a specific penalty
    public ArrayList<TileObject> getTileList(String penalty) {
        if(tiles.containsKey(penalty)) {
            return tiles.get(penalty);
        }
        return null;
    }

    // Returns the whole penalty HashMap
    public HashMap<String, ArrayList<TileObject>> getHashMap() {
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
        if(tiles.get(Constants.PENALTY_ONE).size() < 2) {
            return Constants.PENALTY_ONE;
        }
        if(tiles.get(Constants.PENALTY_TWO).size() < 3) {
            return Constants.PENALTY_TWO;
        }
        if(tiles.get(Constants.PENALTY_THREE).size() < 2) {
            return Constants.PENALTY_THREE;
        }
        return null;
    }

    // Sets up the tile hashmap with the penalty and linked list
    public void setUpHashMap() {
        tiles = new HashMap<>();
        tiles.put(Constants.PENALTY_ONE, new ArrayList<TileObject>());
        tiles.put(Constants.PENALTY_TWO, new ArrayList<TileObject>());
        tiles.put(Constants.PENALTY_THREE, new ArrayList<TileObject>());
    }

    // Discards all the tiles in the floor and sets the penalty to 0
    public void discardFloor() {
        totalPenalty = 0;
        tiles = new HashMap<>();
    }
}
