package game;
public class PointCalculator {
    private final int xAddP;
    private final int yAddP;
    private final int xLimP;
    private final int yLimP;
    private final int xAddT;
    private final int yAddT;
    private final int xLimT;
    private final int yLimT;
    public PointCalculator() {
        xAddP = 0; // change later
        yAddP = 0; // change later
        xLimP = 0;
        yLimP = 0;
        xAddT = 0; // change later
        yAddT = 0; // change later
        xLimT = 0;
        yLimT = 0;
    }
    public Coordinates getScoreBlockPos(Coordinates ZSP, Player player) {
        if (player.getActualPoints() == 0) { // just straight up return if score is 0
            return ZSP;
        }
        int x = ZSP.getX();
        int y = ZSP.getY();
        int xTemp = x;
        int yTemp = y;
        y += yAddP;
        for (int i = 0; i < player.getActualPoints() % 100; i++) {
            xTemp += xAddP; // adds x coordinates
            if (x > xLimP) { // if x out of bounds
                ZSP.setX(x); // reset x
                yTemp += yAddP; //add y, go down a little
            }
        }
        Coordinates ret = new Coordinates(xTemp, yTemp);
        return ret;
    }
    public Coordinates[][] getTilePos(Player player, Coordinates start) {
        int[][] playerBoard = player.getGameBoard();
        int x = start.getX();
        int y = start.getY();
        int xTemp = x;
        int yTemp = y;
        Coordinates[][] ret = new Coordinates[playerBoard.length][playerBoard[0].length];
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard[i].length; i++) {
                Coordinates temp = null;
                xTemp += xAddT; // adds x coordinates
                if (x > xLimT) { // if x out of bounds
                    start.setX(x); // reset x
                    yTemp += yAddT; //add y, go down a little
                }
                if (playerBoard[i][j] == 0) {
                    temp = new Coordinates(xTemp, yTemp);
                }
                ret[i][j] = temp;
            }
        }
        return ret;
    }
    public void childSacrifice() {
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            this.childSacrifice();
            System.out.println("-1 child");
            
        }
    }
}