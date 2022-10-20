package tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import game.Constants;
import images.*;

public class BlackTile extends TileObject {
    private BufferedImage icon;

    public BlackTile() throws IOException {
        super(Constants.BLACK_TILE_ID);
        formIcon("AzulTileBlack");
    }

    public void formIcon (String name) throws IOException {
        icon = ImageIO.read(getClass().getResource(Constants.IMG_DIRECTORY + name + ".jpg"));
    }

    public BufferedImage getIcon() {
        return icon;
    }
}
