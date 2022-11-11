package game;
public class GameLogic {
    private final int xAdd;
    private final int yAdd;
    private final int xLim;
    private Player player1, player2, player3, player4;
    private final int yLim;
    public GameLogic() {
        xAdd = 0; // change later
        yAdd = 0; // change later
        xLim = 0;
        yLim = 0;
    }
    public Coordinates getScoreBlockPos(Coordinates ZSP, int points, int penalty) { // take consideration of penalty
        if (points == 0) { // just straight up return if score is 0
            return ZSP;
        }
        int x = ZSP.getX();
        int y = ZSP.getY();
        y += yAdd;
        for (int i = 0; i < points - penalty; i++) {
            x += xAdd; // adds x coordinates
            if (x > xLim) { // if x out of bounds
                ZSP.setX(x); // reset x
                y += yAdd; //add y, go down a little
            }
        }
        Coordinates ret = new Coordinates(x, y);
        return ret;
    }
    public void childSacrifice() {
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            this.childSacrifice();
            System.out.println("-1 child");
        }
    }
}