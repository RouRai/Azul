package game;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class Constants {

    // Screen dimensions
    private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WIDTH = (int) screenSize.getWidth();
    public static int HEIGHT = (int) screenSize.getHeight();

    // Panel Constants
    public static final JPanel PANEL_CONT = new JPanel();
    
    public static final String RULE_PANEL = "rulePanel";
    public static final String START_PANEL = "startPanel";
    public static final String GAME_PANEL = "gamePanel";
    //public static final String PLAYER_PANEL = "playerPanel";
    public static final String INSTRUCTIONS_PANEL = "instructionsPanel";
    public static final String END_PANEL = "endPanel";
    public static final String MAIN_PANEL = "mainPanel";
    public static final String PLAYER_1_PANEL = "player1Panel";
    public static final String PLAYER_2_PANEL = "player2Panel";
    public static final String PLAYER_3_PANEL = "player3Panel";
    public static final String PLAYER_4_PANEL = "player4Panel";
    // Tile Integer Constants
    public static final int BLACK_TILE_ID = 2;
    public static final int BLUE_TILE_ID = 3;
    public static final int ONE_TILE_ID = 1;
    public static final int RED_TILE_ID = 4;
    public static final int WHITE_TILE_ID = 5;
    public static final int YELLOW_TILE_ID = 6;

    // Factory Constants
    public static final byte FACTORY_ONE = 0;
    public static final byte FACTORY_TWO = 1;
    public static final byte FACTORY_THREE = 2;
    public static final byte FACTORY_FOUR = 3;
    public static final byte FACTORY_FIVE = 4;
    public static final byte FACTORY_SIX = 5;
    public static final byte FACTORY_SEVEN = 6;
    public static final byte FACTORY_EIGHT = 7;
    public static final byte FACTORY_NINE = 8;
    public static final byte FACTORY_FLOOR = 9;

    // Tile String Constants (Names that can be used to get their images)
    public static final String BLACK_TILE = "AzulTileBlack";
    public static final String BLUE_TILE = "AzulTileBlue";
    public static final String RED_TILE = "AzulTileRed";
    public static final String WHITE_TILE = "AzulTileWhite";
    public static final String YELLOW_TILE = "AzulTileYellow";
    public static final String ONE_TILE = "AzulTileOne";

    // Image Directory
    public static final String IMG_DIRECTORY = "/images/";

    // FloorLine Constants
    public static final String PENALTY_ONE = "penaltyOne";
    public static final String PENALTY_TWO = "penaltyTwo";
    public static final String PENALTY_THREE = "penaltyThree";

    // PatternLine Constants
    public static final int ROW_ONE = 0;
    public static final int ROW_TWO = 1;
    public static final int ROW_THREE = 2;
    public static final int ROW_FOUR = 3;
    public static final int ROW_FIVE = 4;

    // Gets Image from image folder
    public static BufferedImage getImage(String name) {
        try{
            return ImageIO.read(Constants.class.getResource(Constants.IMG_DIRECTORY + name + ".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}