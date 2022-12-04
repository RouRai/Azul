package datastructures;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{
    
    private Node<T> first, current;
    private int size;

    public LinkedList() {
        first = null;
        current = null;
        size = 0;
    }

    // Adds a Node to the Box
    public void add(T o) {
        Node<T> newNode = new Node<T>(o, null, null);
        if(first == null) {
            first = current = newNode;
            size++;
            return;
        }
        current.setNext(newNode);
        newNode.setPrevious(current);
        current = newNode;
        size++;
    }

    // Removes first instance of the T in the Box
    public void remove (T o) {
        for(Node<T> n = getFirst(); n.getNext() != null; n = n.getNext()) {
            if(n.getItem().equals(first.getItem())) {
                first = first.getNext();
                size--;
                return;
            } else if(n.getItem().equals(current.getItem())){
                current = current.getPrevious();
                size--;
                return;
            } else if(n.getItem().equals(o)){
                n.getPrevious().setNext(n.getNext());
                size--;
                return;
            }
        } 
    }

    // Removes all instances of the T in the Box
    public void removeAll (T o) {
        for(Node<T> n = first; n.getNext()!= null; n = n.getNext()) {
            if(n.getItem().equals(o)){
                n.getNext().setPrevious(n.getPrevious());
                size--;
            }
        }
    }

    // Removes the last instance of T in the Box
    public void removeLast(T o) {
        Node<T> tail = new Node<T>(current.getItem(), null, current.getPrevious());
        for(Node<T> n = tail; n.getPrevious() != null; n = n.getPrevious()) {
            if(n.getItem().equals(o)) {
                n.getPrevious().setNext(n.getNext());
                size--;
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