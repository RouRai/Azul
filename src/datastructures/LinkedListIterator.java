package datastructures;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

    private Node<T> current;

    public LinkedListIterator(LinkedList<T> obj){
        current = obj.getFirst();

    }

    // Checks if there are any more Nodes to iterate to
    public boolean hasNext() {
        return current != null; 
    }

    // Iterates to next Node in the Box
    @Override
    public T next() {
        if(hasNext()){
            T data = current.getItem();
            current = current.getNext();
            return data;
        }
        return null;

        }
}