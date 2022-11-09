package game;

import java.awt.*;

public class Constants {
    // Screen dimensions
    private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = (int) screenSize.getWidth();
    public static final int HEIGHT = (int) screenSize.getHeight();

    // Tile Integer Constants
    public static final int BLACK_TILE_ID = 1;

    // Tile String Constants
    public static final String BLACK_TILE_NAME = "blackTile";

    // Strings
    public static final String IMG_DIRECTORY = "/images/";

}