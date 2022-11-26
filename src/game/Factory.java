package game;

import java.util.ArrayList;

import datastructures.Box;
import tiles.TileObject;

public class Factory {
    
    private ArrayList<TileObject> tiles;

    public Factory() {
        tiles = new ArrayList<TileObject>();
    }

    public void setTiles(ArrayList<TileObject> tiles) {
        this.tiles = tiles;
    }

    public void addTile(TileObject t) {
        tiles.add(t);
    }

    public ArrayList<TileObject> getTiles() {
        return tiles;
    }

    public String toString() {
        String s = "[";

        for(TileObject tile : tiles){
            s += tile.getType() + ", ";
        } 

        return s.substring(0, s.length()-2) + "]";
    }
}
