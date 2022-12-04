package logic;

import datastructures.LinkedList;
import java.util.*;
// Author: Rounak Rai
public class Row {
    
    private ArrayList<TileObject> tiles; // Tiles currently in the row
    private int size; // The maximum size of the row that is possible
    private String type; // The type of the tile that is currently in the row

    public Row(int s) {
        size = s;
        tiles = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public ArrayList<TileObject> getTiles() {
        return tiles;
    }

    // Returns if the row is full or not
    public boolean isFull() {
        return tiles.size() == size;
    }

    // Adds the desired amount tiles of the row's type
    public void addTiles(int n) {
        for(int i = 0; i < n; i++) {
            addTile();
        }
    }

    // Adds a single tile to the Row
    public void addTile() {
        tiles.add(new TileObject(type));
    }

    // Returns the current type of tile in the Row
    public String getType() {
        return type;
    }

    // Changes the current type of tile in the Row. If the row has tiles of a different type, it will throw an exception
    public void setType(String t) throws Exception {
        if(!tiles.isEmpty()){
            throw new Exception("You cannot set the type of the row as it currently contains tiles.");
        }
        type = t;
    }

    // Discards the tiles in the row.
    public void discardTiles() {
        tiles = new ArrayList<>();
        type = null;
    }
}
