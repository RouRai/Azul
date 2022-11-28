package logic;

import game.Constants;

// Author: Rounak Rai (WIP)
public class Wall {
    
    private String[][] board; // Currently has nothing placed in it
    private String[][] indicated; // The part of the board with the images on them
    private Player player;

    public Wall(Player p) {
        board = new String[5][5];
        buildIndicated();
        player = p;
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

    private void addToScore(int row, int col) {
        player.setScore(player.getScore() + 1 + getRowScore(row) + getColScore(col)); // Immediate +1 score for moving tile to wall
    }

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
        {Constants.RED_TILE, Constants.BLUE_TILE, Constants.BLACK_TILE, Constants.WHITE_TILE, Constants.BLUE_TILE}};
    }
}
