package datastructures;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{
    
    private Node<T> first, current;
    private int size;

    public LinkedList() {
        first = null;
        current = null;
    }

    // Adds a Node to the Box
    public void add(T o) {
        if(first == null) {
            first = new Node<T>(o, null, null);
            current = first;
            return;
        }
        current = new Node<T>(o, null, current);
        size++;
    }

    // Removes first instance of the T in the Box
    public void remove (T o) {
        for(Node<T> n = first; n.getNext()!= null; n = n.getNext()) {
            if(n.getItem().equals(o)){
                n.getNext().setPrevious(n.getPrevious());
                return;
            }
        }
    }

    // Removes all instances of the T in the Box
    public void removeAll (T o) {
        for(Node<T> n = first; n.getNext()!= null; n = n.getNext()) {
            if(n.getItem().equals(o)){
                n.getNext().setPrevious(n.getPrevious());
            }
        }
    }

    // Removes the last instance of T in the Box
    public void removeLast(T o) {
        Node<T> tail = new Node<T>(current.getItem(), null, current.getPrevious());
        for(Node<T> n = tail; n.getPrevious() != null; n = n.getPrevious()) {
            if(n.getItem().equals(o)) {
                n.getPrevious().setNext(n.getNext());
                return;
            }
        }
    }

    // Returns formmated String of contets of Box
    public String toString() {
        if(first == null){
            return first + " ";
        }

        String s = "["; 

        for(Node<T> n = first; n.getNext() != null; n = n.getNext()){
            s += n.getItem() + ", ";
        }

        return s.substring(0, s.length()-2) + "]";
    }

    // Gets the first Node in the box, useful when implementing the Iterator
    public Node<T> getFirst() {
        return first;
    }

    // Allows us to traverse the data structure
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }

    // Returns whether or not the box is empty
    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() {
        return size;
    }
}