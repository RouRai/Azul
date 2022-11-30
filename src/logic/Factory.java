package logic;

import java.util.ArrayList;

import datastructures.LinkedList;
import java.util.*;
// Author: Rounak Rai
public class Factory {

    private ArrayList<TileObject> tiles; // The current tiles in the factory
    private int size; // The expected size of the factory (usually four, but exceptions made for FactoryFloor)

    public Factory(int s) {
        tiles = new ArrayList<>();
        size = s;
    }

    // Checks if the Factory is empty
    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    // Checks if the Factory is full
    public boolean isFull() {
        return getSize() == size;
    }

    // Returns the Factory's size
    public int getSize() {
        return tiles.size();
    }

    // Adds a tile to the Factory
    public void addTile(TileObject t) {
        tiles.add(t);
    }

    // Adds a LinkedList of tiles to the Factory
    public void addTiles(LinkedList<TileObject> t) {
        for(TileObject tile : t) {
            addTile(tile);
        }
    }

    // Returns the tiles currently in the Factory
    public ArrayList<TileObject> getTiles() {
        return tiles;
    }

    // Removes all the tiles of a single type from the Factory
    private void removeType(String type) {
        for(TileObject t : tiles) {
            if(t.getType().equals(type)) {
                tiles.remove(t);
            }
        }
    }

    // Returns the remaining TileObjects in the form of a LinkedList to the player
    public ArrayList<TileObject> getRemaning(String type) {
        removeType(type);
        return tiles;
    }
}
