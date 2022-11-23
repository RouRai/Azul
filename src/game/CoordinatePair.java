package game;

public class CoordinatePair {
    
    private Coordinates start, end;

    public CoordinatePair(Coordinates s, Coordinates e) {
        start = s;
        end = e;
    }

    public Coordinates getStartCoordinates() {
        return start;
    }

    public Coordinates getEndCoordinates() {
        return end;
    }
}
