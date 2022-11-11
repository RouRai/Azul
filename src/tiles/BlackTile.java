package tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import game.Constants;
import images.*;

public class BlackTile extends TileObject {
    private BufferedImage icon;

    public BlackTile() throws IOException {
        super(Constants.BLACK_TILE_ID, "AzulTileBlack", Constants.BLACK_TILE);
    }

}
