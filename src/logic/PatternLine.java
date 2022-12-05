package logic;

// Author: Rounak Rai
public class PatternLine {
    
    private Row[] patternLines; // The area where the player docks their tiles
    private FloorLine floor; // The floorline

    public PatternLine(FloorLine f) {
        floor = f;
        setUpPatternLines();
    }

    /*
     * Adds a tile to the given row in the parameters. If that row does not exist, or if the row doesn't
     * have a type, the Exception below is thrown. If the row is full, the excess tiles are added to the 
     * FloorLine
     */
    private void addToRow(int row) throws Exception {
        if(row >= patternLines.length || row < 0 || patternLines[row].getType() == null) {
            throw new Exception("You cannot add to the row, as either the row has no type or the row exceeds bounds");
        }
        if(rowIsFull(row)){
            floor.addTile(new TileObject(patternLines[row].getType()));
            return;
        }
    }

    // Does what addToRow does but given a certain count. If one must be added, use one as the parameter.
    public void addToRow(int count, int row) throws Exception {

        for(int i = 0; i < count; i++) {
            if(!rowIsFull(row)){
                getRow(row).addTile();
            } else{
                floor.addTile(getRow(row).getTiles().get(0));
            }
        }
    }

    // Discards the excess tiles after moving tiles to the wall (WIP)
    public void discardExcess() {
        for(Row r : patternLines) {
            r.discardTiles();
        }
    }

    // Checks if the given row is full or not. If the row doesn't exist, the Exception below is thrown.
    public boolean rowIsFull(int row) throws Exception {
        /*if(row >= patternLines.length || row < 0) {
            throw new Exception("You cannot check if row is full, as the row exceeds bounds");
        }*/
        return getRow(row).isFull();
    }

    // Returns the desired row. If the row doesn't exist, the Exception below is thrown.
    public Row getRow(int row) throws Exception {
        /*if(row > patternLines.length || row <= 0) {
            throw new Exception("You cannot get to the row, as the row exceeds bounds");
        }*/
        return patternLines[row];
    }

    // Sets the type of a specific row.
    public void setRowType(int row, String t) throws Exception {
        /*if(row > patternLines.length || row < 0) {
            throw new Exception("You cannot set the type of the row, as the row exceeds bounds");
        }*/
        getRow(row).setType(t);
        System.out.println("set row " + row +" to type " + t);
    }

    // Initializes the patternLines array
    private void setUpPatternLines() {
        patternLines = new Row[]
            {new Row(1), 
            new Row(2), 
            new Row(3), 
            new Row(4), 
            new Row(5)};
    }
}
