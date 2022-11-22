package datastructures;

public class Node<T> {
    
    private Node<T> next, previous;
    private T item;


    public Node (T item, Node<T> next, Node<T> previous) {
        this.item = item;
        this.next = next;
        this.previous = previous;
    }

    // Set Methods

    public void setNext(Node<T> n) {
        next = n;
    }

    public void setPrevious (Node<T> n) {
        previous = n;
    }

    public void setItem (T o) {
        item = o;
    }

    // Get Methods

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public T getItem() {
        return item;
    }
}
