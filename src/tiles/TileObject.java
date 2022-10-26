package tiles;

import game.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileObject implements Comparable<TileObject> {
    private int groupInt;
    private BufferedImage icon;
    private String type;

    public TileObject(int gInt, String name, String type) throws IOException {
        groupInt = gInt;
        formIcon("AzulTileBlack");
        this.type = type;
    }

    public int getGroupInt(){
        return groupInt;
    }

    @Override
    // Useful for sorting tiles for whatever reason is necessary
    public int compareTo(TileObject o) {
        if (getGroupInt() < o.getGroupInt()) {
            return -1;
        } else if (getGroupInt() > o.getGroupInt()) {
            return 1;
        }
        return 0;
    }

    // Use this to make Icons, input the name of the file excluding the .jpg or whatever
    public void formIcon (String name) throws IOException { icon = ImageIO.read(getClass().getResource(Constants.IMG_DIRECTORY + name + ".jpg")); }

    public BufferedImage getIcon() {
        return icon;
    }

    public String getType() {return type;}
}
