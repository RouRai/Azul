package game;

import datastructures.Box;
import tiles.TileObject;

public class Factory {
    
    private Box<TileObject> tiles;

    public Factory(Box<TileObject> t) {
        tiles = t;
    }

    public Box<TileObject> getTiles() {
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
