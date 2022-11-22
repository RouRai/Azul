package tiles;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.IOException;

public class TileObject implements Comparable<TileObject>{
    private int groupInt;
    private ImageIcon icon;
    private String type;
    private JButton tileButton;

    public TileObject(int gInt, String name, String t) throws IOException {
        groupInt = gInt;
        formIcon(name);
        type = t;
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
    public void formIcon (String name) throws IOException { 
        // Figure out way to declare icons
        icon = new ImageIcon(name);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }

    public JButton getButton() {
        return tileButton;
    }

}
