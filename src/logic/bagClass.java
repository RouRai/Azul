package logic;
import java.util.*;

import game.Constants;

public class bagClass {

    private LinkedList<TileObject>bag;

    public bagClass(){
        bag = new LinkedList<>();
    }

    public void fillBag(){
        for(byte i = 0; i < 20; i++) {
            bag.add(new TileObject(Constants.BLACK_TILE));
            bag.add(new TileObject(Constants.BLUE_TILE));
            bag.add(new TileObject(Constants.RED_TILE));
            bag.add(new TileObject(Constants.YELLOW_TILE));
            bag.add(new TileObject(Constants.WHITE_TILE));
        }
        Collections.shuffle(bag);
    }

    public LinkedList<TileObject> gTileObjects(){
        return bag;
    }

    public TileObject get(){
        TileObject t = bag.get(0);
        bag.remove(0);
        return t;
    }

    public void add(TileObject t){
        bag.add(t);
    }
}