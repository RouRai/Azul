package tiles;

public class TileObject implements Comparable<TileObject> {
    private int groupInt;

    public TileObject(int gInt){
        groupInt = gInt;
    }

    public int getGroupInt(){
        return groupInt;
    }

    @Override
    public int compareTo(TileObject o) {
        if (getGroupInt() < o.getGroupInt()) {
            return -1;
        } else if (getGroupInt() > o.getGroupInt()) {
            return 1;
        }
        return 0;
    }
}
