package game;

public class PointCalculator {

    private final int xAddP, yAddP, xLimP, xAddT, yAddT, xLimT;

    public PointCalculator() {
        xAddP = 0; // change later what to add to score block per increment
        yAddP = 0; // change later what to addd when y vla changes
        xLimP = 0; // absoulte righterst it cna go
        xAddT = 0; // change later what to add for each tile pos
        yAddT = 0; // change later what do add when tile changes y 
        xLimT = 0; // absolute righetsr it can aogfawsf
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
        return new Coordinates(xTemp, yTemp);
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
}