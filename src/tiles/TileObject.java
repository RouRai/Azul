package tiles;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.Constants;

import java.io.IOException;

public class TileObject implements Comparable<TileObject>{
    private int groupInt;
    private String type;
    private JButton tileButton;

    public TileObject(int gInt, String name) {
        groupInt = gInt;
        type = name;
        tileButton = new JButton(formIcon(name));
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
    public ImageIcon formIcon (String name) { 
        // Figure out way to declare icons
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

}
