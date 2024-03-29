package game;

import java.awt.*;

public class Constants {
    // Screen dimensions
    private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = (int) screenSize.getWidth();
    public static final int HEIGHT = (int) screenSize.getHeight();

    // Panel Constants

    // Tile Integer Constants
    public static final int BLACK_TILE_ID = 2;
    public static final int BLUE_TILE_ID = 3;
    public static final int ONE_TILE_ID = 1;
    public static final int RED_TILE_ID = 4;
    public static final int WHITE_TILE_ID = 5;
    public static final int YELLOW_TILE_ID = 6;

    // Tile String Constants
    public static final String BLACK_TILE = "blackTile";
    public static final String BLUE_TILE = "blueTile";
    public static final String RED_TILE = "redTile";
    public static final String WHITE_TILE = "whiteTile";
    public static final String YELLOW_TILE = "yellowTile";
    public static final String ONE_TILE = "oneTile";

    // Strings
    public static final String IMG_DIRECTORY = "/images/";

}