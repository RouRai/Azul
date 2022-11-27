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

    public void addTile(TileObject t) {
        if(decidePenalty() != null) {
            tiles.get(decidePenalty()).add(t);
            return;
        }
        lid.add(t);
    }

    public void addTiles(LinkedList<TileObject> t) {
        for(TileObject obj : t) {
            addTile(obj);
        }
    }

    public LinkedList<TileObject> getTileList(String penalty) {
        if(tiles.containsKey(penalty)) {
            return tiles.get(penalty);
        }
        return null;
    }

    public HashMap<String, LinkedList<TileObject>> getHashMap() {
        return tiles;
    }

    public boolean isFull() {
        return decidePenalty() == null;
    }

    public int getPenalty() {
        return totalPenalty;
    }

    public void setPenalty(int b) {
        totalPenalty = b;
    } 

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

    private void setUpHashMap() {
        tiles.put(Constants.PENALTY_ONE, new LinkedList<TileObject>());
        tiles.put(Constants.PENALTY_TWO, new LinkedList<TileObject>());
        tiles.put(Constants.PENALTY_THREE, new LinkedList<TileObject>());
    }

}
