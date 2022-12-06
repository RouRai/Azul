
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
    public T remove (T o) {
        for(Node<T> n = getFirst(); n != null; n = n.getNext()) {
            if(n.getItem().equals(first.getItem()) && first.getItem().equals(o)) {
                first = first.getNext();
                size--;
                return o;
            } else if(n == current && n.getItem().equals(o)){
                current.getPrevious().setNext(null);
                size--;
                return o;
            } else if(n.getItem().equals(o)){
                n.getPrevious().setNext(n.getNext());
                size--;
                return o;
            }
        } 
        return null;
    }

    // Removes all instances of the T in the Box
    public void removeAll (T o) {
        for(Node<T> n = getFirst(); n != null; n = n.getNext()) {
            if(n.getItem().equals(first.getItem()) && first.getItem().equals(o)) {
                first = first.getNext();
                first.setPrevious(null);
                size--;
            } else if(n == current && n.getItem().equals(o)){
                current = current.getPrevious();
                current.setNext(null);
                size--;
            } else if(n.getItem().equals(o)){
                n.getPrevious().setNext(n.getNext());
                size--;
            }
        } 
        removeLast(o);
        size++;
    }

    // Removes the last instance of T in the Box
    public T removeLast(T o) {
        for(Node<T> n = getLast(); n != null; n = n.getPrevious()) {
            if(n.getItem().equals(first.getItem()) && first.getItem().equals(o)) {
                first = first.getNext();
                size--;
                return o;
            } else if(n == current && n.getItem().equals(o)){
                current.getPrevious().setNext(null);
                current = current.getPrevious();
                size--;
                return o;
            } else if(n.getItem().equals(o)){
                n.getPrevious().setNext(n.getNext());
                size--;
                return o;
            }
        } 
        return null;
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

    public Node<T> getLast() {
        return current;
    }

    // Allows us to traverse the data structure
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }

    // Returns whether or not the box is empty
    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return size;
    }
}