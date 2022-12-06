package logic;

public class FactoryFloor extends Factory{
    
    private boolean hasOneTile; // Value deciding if the FactoryFloor contains the OneTile

    public FactoryFloor() {
        super(100);
    }

    // Sets whether or not the FactoryFloor currently contains the OneTile
    public void setHasOneTile(boolean b) {
        hasOneTile = b;
    }

    // Returns whether or not the FactoryFloor currently contains the OneTile
    public boolean hasOneTile() {
        return hasOneTile;
    }
    
    public boolean isEmpty() {
        return super.isEmpty();
    }

    
    // Everything else is inherited from the Factory class, and works the same
}
