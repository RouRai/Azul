package game;
import java.util.*;
import tiles.TileObject;
public class bagClass {
    private ArrayList<TileObject>bag;
    public bagClass(){
        bag = new ArrayList<>();
    }
    public void fillBag(){
        for(byte i = 0; i < 20; i++) {
            bag.add(new TileObject(Constants.BLACK_TILE_ID, Constants.BLACK_TILE));
            bag.add(new TileObject(Constants.BLUE_TILE_ID, Constants.BLUE_TILE));
            bag.add(new TileObject(Constants.RED_TILE_ID, Constants.RED_TILE));
            bag.add(new TileObject(Constants.YELLOW_TILE_ID, Constants.YELLOW_TILE));
            bag.add(new TileObject(Constants.WHITE_TILE_ID, Constants.WHITE_TILE));
        }
        Collections.shuffle(bag);
    }
    public ArrayList<TileObject> gTileObjects(){
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
