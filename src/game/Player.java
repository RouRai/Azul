package game;

public class Player {
    
    private int points, penalty;
    private boolean isFirst;
    private int[][] gameBoard, penaltyBoard, pyramidThing, filledBoard;

    public Player() {
        points = 0;
        isFirst = false;
        penalty = 0;
        gameBoard = new int[5][5];
        penaltyBoard = new int[][]{{0, 0}, {0, 0, 0}, {0, 0}};
        pyramidThing = new int[][]{{0}, {0, 0}, {0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        filledBoard = new int[][]{{Constants.BLUE_TILE_ID, 6, 4, 2, 5}, {5, 3, 6, 4, 2}, {2, 5, 3, 6, 4}, {4, 2, 5, 3, 6}, {6, 4, 2, 5, 3}};
    }

    // Changes amount of points the player has
    public void changePoints(int chg) {
        points += chg;
    } 

    // Returns total points
    public int getTotalPoints(int i, int j) {
        int points = 1;
        for (int iu = i - 1; iu >= 0; iu--) { // up
            if (gameBoard[iu][j] == 0) {
                continue;
            }
            points++;
        }

        for (int id = i + 1; id < gameBoard.length; id++) { // down
            if (gameBoard[id][j] == 0) {
                continue;
            }
            points++;
        }

        for (int ju = j - 1; ju >= 0; ju--) { // left
            if (gameBoard[i][ju] == 0) {
                continue;
            }
            points++;
        }

        for (int jd = j + 1; jd < gameBoard[i].length; jd++) { // right
            if (gameBoard[i][j] == 0) {
                continue;
            }
            points++;
        }

        return points;
        
    } // need to add special occasions
    public int getActualPoints() {
        return points - getPenalty();
    }
    public void addBonusPoints() {
        for (int i = 0; i < gameBoard.length; i++) {
            points += isRowFilled(gameBoard, i) ? 2 : 0;
        }
        for (int i = 0; i < gameBoard[i].length; i++) {
            points += isColFilled(gameBoard, i) ? 7 : 0;
        }
        boolean[] temp = colorsFilled();
        for (boolean a: temp) {
            points += a ? 10 : 0;
        }
    }
    public void switchFirst() {
        isFirst = !isFirst;
    }
    public boolean getFirst() {
        return isFirst;
    }
    public int getPenalty() {
        int ret = 0;
        for (int i = 0; i < penaltyBoard.length; i++) {
            for (int j = 0; j < penaltyBoard[j].length; j++) {
                ret += penaltyBoard[i][j] * (i + 1); // penalty board only has 0 or 1. Adds the value in penalty board times the row number so the multiplier can kick in
            }
        }
        return ret;
    }
    public void resetPenalty() {
        for (int i = 0; i < penaltyBoard.length; i++) {
            for (int j = 0; j < penaltyBoard[j].length; j++) {
                penaltyBoard[i][j] = 0;
            }
        }
    }
    public void addPenalty() {
        if (getPenalty() == 14) {
            return;
        }
        for (int i = 0; i < penaltyBoard.length; i++) {
            for (int j = 0; j < penaltyBoard[i].length; j++) {
                if (penaltyBoard[i][j] == 0) {
                    penaltyBoard[i][j] = 1; // searches for nearest 0. makes it turn into 1
                    return;
                }
            }
        }

    }
    public void addTile(int r, int tile) {
        int c = getRowTileIdx(tile, r);
        gameBoard[r][c] = tile;
    }
    public void resetBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }
    public int[][] getGameBoard() {
        return gameBoard;
    }
    public void setGameBoard(int[][] inp) {
        gameBoard = inp;
    }
    public void addTilesToPyramid(int tile, int num, int row) { // not finished
        for (int i = getFirstEmpty(pyramidThing, row); i < pyramidThing[row].length; i++) {
            num--;
            pyramidThing[row][i] = tile;
            if (num  == 0) {
                break;
            }
        }
        if (num > 0) {
            //add to penalty 
            for (int i = 0; i < num; i++) {
                addToPenalty(tile);
            }
        }
        if (isRowFilled(pyramidThing, row)) {
            clearRow(pyramidThing, row);
            gameBoard[row][getRowTileIdx(tile, row)] = tile;
        }

    }
    public static boolean isRowFilled(int[][] mat, int row) {
        for (int i = 0; i < mat[row].length; i++) {
            if (mat[row][i] == 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean isColFilled(int[][] mat, int row) {
        for (int i = 0; i < mat.length; i++) {
            if (mat[row][i] == 0) {
                return false;
            }
        }
        return true;
    }
    public static void clearRow(int[][] mat, int row) {
        for (int i = 0; i < mat[row].length; i++) {
            mat[row][i] = 0;
        }
    }
    public int getRowTileIdx(int tile, int row) {
        int ret = 0;
        for (int i = 0; i < filledBoard[row].length; i++) {
            if (filledBoard[row][i] == tile) {
                ret = i;
            }
        }
        return ret;
    }
    public int getFirstEmpty(int[][] mat, int r) {
        for (int i = 0; i < mat[r].length; i++) {
            if (mat[r][i] == 0) {
                return i;
            }
        }
        return -1;
    }
    public void addToPenalty(int tile) {
        if (getFirstEmpty(penaltyBoard, 0) != -1) {
            penaltyBoard[0][getFirstEmpty(penaltyBoard, 0)] = tile;
        } else if (getFirstEmpty(penaltyBoard, 1) != -1) {
            penaltyBoard[1][getFirstEmpty(penaltyBoard, 1)] = tile;
        } else if (getFirstEmpty(penaltyBoard, 2) != -1) {
            penaltyBoard[2][getFirstEmpty(penaltyBoard, 2)] = tile;
        }
    }
    public String toString() {
        String ret = "";
        for (int i = 0; i < penaltyBoard.length; i++) {
            for (int j = 0; j < penaltyBoard[i].length; j++) {
                ret += penaltyBoard[i][j] + " ";
            }
            ret += "\n";
        }
        ret += "\n\n";
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                ret += gameBoard[i][j] + " ";
            }
            ret += "\n";
        }
        ret += "\n\n";
        for (int i = 0; i < pyramidThing.length; i++) {
            for (int j = 0; j < pyramidThing[i].length; j++) {
                ret += pyramidThing[i][j] + " ";
            }
            ret += "\n";
        }
        return ret;
    }
    public boolean[] colorsFilled() {
        int[] colorCnt = new int[5];
        for (int i = 0; i < filledBoard.length; i++) {
            for (int j = 0; j < filledBoard[i].length; j++) {
                if (filledBoard[i][j] == 3) {
                    colorCnt[0]++;
                }
                if (filledBoard[i][j] == 6) {
                    colorCnt[1]++;
                }
                if (filledBoard[i][j] == 4) {
                    colorCnt[2]++;
                }
                if (filledBoard[i][j] == 2) {
                    colorCnt[3]++;
                }
                if (filledBoard[i][j] == 5) {
                    colorCnt[4]++;
                }
            }
        }
        return new boolean[]{colorCnt[0] == 5, colorCnt[1] == 5, colorCnt[2] == 5, colorCnt[3] == 5, colorCnt[4] == 5};
    }
}