package tiles;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.Constants;

import java.io.IOException;

public class TileObject implements Comparable<TileObject>{
    private int groupInt;
    private String type;
    private JButton tileButton;
    private ImageIcon icon;

    public TileObject(int gInt, String name) {
        groupInt = gInt;
        type = name;
        icon = formIcon(name);
        tileButton = new JButton(icon);
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
    private ImageIcon formIcon (String name) { 
        try{
            return new ImageIcon(Constants.IMG_DIRECTORY + name + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getType() {
        return type;
    }

    public JButton getButton() {
        return tileButton;
    }

    public ImageIcon getIcon() {
        return icon;
    }

}
