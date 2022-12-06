package logic;

import java.util.ArrayList;

import datastructures.LinkedList;
import java.util.*;
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
    public void addTiles(ArrayList<TileObject> t) {
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
        /*TileObject t;
        Iterator<TileObject> iter = tiles.iterator();
        while(iter.hasNext()){
            t = iter.next();
            if(t.getType().equals(type)){
                tiles.remove(t);
            }
        }*/
        /*for(TileObject f :tiles){
            if(f.getType().equals(type)){
                tiles.remove(f);
            }
        }*/
        for(int i = tiles.size() - 1; i >= 0; i--){
            if(tiles.get(i).getType().equals(type)){
                tiles.remove(i);
            }
        }
        /*if(tiles.getFirst().getItem().getType().equals(type)){
            tiles.remove(tiles.getFirst().getItem());
        }
        if(tiles.getLast().getItem().getType().equals(type)){
            tiles.remove(tiles.getLast().getItem());
        }*/
    }

    // Returns the remaining TileObjects in the form of a LinkedList to the player
    public ArrayList<TileObject> getRemaning(String type) {
        removeType(type);
        return tiles;
    }
    public int getNumTiles(String str){
        int cnt = 0;
        //System.out.println(getSize() + ": size");
        /*Iterator<TileObject> iter = tiles.iterator();
        while(iter.hasNext()){
            if(iter.next().getType().equals(str)){
                cnt++;
            }
        }*/
        for(TileObject t: tiles){
            if(t.getType().equalsIgnoreCase(str)){
                cnt++;
            }
        }
        return cnt;
    }
}
