package game;

import datastructures.Box;
import tiles.TileObject;

public class Factory {
    
    private Box<TileObject> tiles;

    public Factory(Box<TileObject> t) {
        tiles = t;
    }

}
