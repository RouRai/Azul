package game;

import datastructures.LinkedList;
import tiles.TileObject;

public class Factory {
    
    private LinkedList<TileObject> tiles;

    public Factory() {
        tiles = new LinkedList<TileObject>();
    }

    public void setTiles(LinkedList<TileObject> tiles) {
        this.tiles = tiles;
    }

    public void addTile(TileObject t) {
        tiles.add(t);
    }

    public LinkedList<TileObject> getTiles() {
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
