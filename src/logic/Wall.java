package logic;

import java.util.ArrayList;

import datastructures.LinkedList;
import game.Constants;
import java.util.*;
public class Wall {
    
    private String[][] board; // Currently has nothing placed in it
    private String[][] indicated; // The part of the board with the images on them
    private Player player;
    private FloorLine floorLine;

    public Wall(Player p, FloorLine f) {
        board = new String[5][5];
        buildIndicated();
        player = p;
        floorLine = f;
    }

    // Returns the indicated tiles board
    public String[][] getIndicated() {
        return indicated;
    }

    // Returns a row from the indicated tiles board
    public String[] getIndicatedRow(int row) throws Exception {
        if(row < 0 || row >= board.length) {
            throw new Exception("You cannot get the indicated row as the row is out of bounds");
        }
        return indicated[row];
    }

    // Returns a tile from the indicated tiles board
    public String getIndicatedTile(int row, int col) throws Exception {
        if(row < 0 || row >= board.length) {
            throw new Exception("You cannot get the indicated row as the row is out of bounds");
        }
        if(col < 0 || col >= board[row].length) {
            throw new Exception("You cannot get the indicated column as the row is out of bounds");
        }
        return indicated[row][col];
    }

    // Returns the board that the player must place tiles upon
    public String[][] getBoard() {
        return board;
    }

    // It will add a tile to the wall given a type and the row in which to add it to
    public void addToRow(String type, int row) throws Exception {
        int index;
        for(index = 0; index < getIndicatedRow(row).length; index++) {
            if(getIndicatedTile(row, index).equals(type)) {
                board[row][index] = type;
                addToScore(row, index);
                return;
            }
        }
    }

    // Adds points to the score given a row and a column
    private void addToScore(int row, int col) {
        // if(getRowScore(row) == 1 && getColScore(col) == 1) { // Executed if there are no adjacent tiles
        //     player.setScore(player.getScore() + 1);
        //     //floorLine.discardFloor();
        //     return;
        // }
        player.setScore(player.getScore() + getRowScore(row) + getColScore(col) - 1); // Immediate +1 score for moving tile to wall
        //floorLine.discardFloor();
    }

    // Gets the score of a consecutive row from a given row
    private int getRowScore(int row) {
        int s = 0;
        int c;
        for(c = 0; c < board[row].length; c++) {
            if(board[row][c] == null) {
                continue;
            }
            break;
        }
        for(int col = c; col < board[row].length; col++) {
            if(board[row][col] == null) {
                break;
            }
            s++;
        }
        return s;
    }
    
    // Gets the score of a consecutive column from a given column
    private int getColScore(int col) {
        int s = 0;
        int r;
        for(r = 0; r < board.length; r++) {
            if(board[r][col] == null) {
                continue;
            }
            break;
        }
        for(int row = r; row < board.length; row++) {
            if(board[row][col] == null) {
                break;
            }
            s++;
        }
        return s;
    }

    // Checks if the row is full
    public boolean rowIsFull(int row) throws Exception {
        if(row < 0 || row >= board.length) {
            throw new Exception("You cannot get the indicated row as the row is out of bounds");
        }
        for(String s : getIndicatedRow(row)) {
            if(s == null) {
                return false;
            }
        }
        return true;
    }

    // Builds the indicated board
    private void buildIndicated() {
        indicated = new String[][] 
        {{Constants.BLUE_TILE, Constants.YELLOW_TILE, Constants.RED_TILE, Constants.BLACK_TILE, Constants.WHITE_TILE},
        {Constants.WHITE_TILE, Constants.BLUE_TILE, Constants.YELLOW_TILE, Constants.RED_TILE, Constants.BLACK_TILE},
        {Constants.BLACK_TILE, Constants.WHITE_TILE, Constants.BLUE_TILE, Constants.YELLOW_TILE, Constants.RED_TILE},
        {Constants.RED_TILE, Constants.BLACK_TILE, Constants.WHITE_TILE, Constants.BLUE_TILE, Constants.YELLOW_TILE},
        {Constants.YELLOW_TILE, Constants.RED_TILE, Constants.BLACK_TILE, Constants.WHITE_TILE, Constants.BLUE_TILE}};
    }

    // Returns the amount of completed rows in the wall
    public int completedRows() {
        int complete = 0;
        for(int r = 0; r < board.length; r++) {
            boolean isntNull = true;
            for(int c = 0; c < board[r].length; c++) {
                if(board[r][c] == null) {
                    isntNull = false;
                    break;
                }
            }
            if(isntNull) {
                complete++;
            } 
        }
        return complete;
    }

    // Returns the score given from the additional scoring (including the penalty)
    public int additionalScoring() {
        int addScore = 0;

        for(int i = 0; i < board.length; i++) {
            if(rowCompleted(i)) {
                addScore += 2;
            }
        }

        for(int i = 0; i < board[0].length; i++) {
            if(columnCompleted(i)) {
                addScore += 7;
            }
        }

        for(String s : getTypes()) {
            if(hasAllOne(s)){
                addScore += 10;
            }
        }

        return addScore;
    }

    // Returns if the board has all 5 tiles of one type
    private boolean hasAllOne(String type) {
        int count = 0;
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++){
                if(!(board[r][c] == null)){
                if(board[r][c].equals(type)) {
                    count++;
                }
            }
        }
        }
        return count == 5;
    }

    // Returns if a column is complete
    private boolean columnCompleted(int col) {
        for(int r = 0; r < board[0].length; r++) {
            if(!(board[r][col] == null)){
            if(board[r][col] == null) {
                return false;
            }
        }
    }
        return true;
    }

    // Returns if a row is complete
    private boolean rowCompleted(int row) {
        for(int i = 0; i < board[row].length; i++) {
            if(!(board[row][i] == null)){
            if(board[row][i] == null) {
                return false;
            }
        }
    }
        return true;
    }

    // Returns the different types of tiles in the form of a LinkedList
    private LinkedList<String> getTypes() {
        LinkedList<String> types = new LinkedList<>();

        types.add(Constants.BLACK_TILE);
        types.add(Constants.BLUE_TILE);
        types.add(Constants.RED_TILE);
        types.add(Constants.YELLOW_TILE);
        types.add(Constants.WHITE_TILE);
        
        return types;
    }
    public boolean contains(int r, String tile){
        for(int i = 0; i < 5; i++){
            if(!(board[r][i] == null)){
                if(board[r][i].equals(tile)){
                    return true;
                }
            }
        }
        return false;
    }
}
