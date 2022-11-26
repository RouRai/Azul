package datastructures;

import java.util.Iterator;

public class BoxIterator<T> implements Iterator<T> {
    private boolean gotFirst;
    private Node<T> current;

    public BoxIterator(Box<T> obj){
        current = obj.getFirst();
        gotFirst = false;
    }

    // Checks if there are any more Nodes to iterate to
    public boolean hasNext() {
        return current != null; 
    }

    // Iterates to next Node in the Box
    @Override
    public T next() {
        if(!gotFirst){
            current = current.getNext();
            gotFirst = true;
        }
        return current.getItem();
    }
}