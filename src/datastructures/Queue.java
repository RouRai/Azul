package datastructures;

public class Queue<T> {

    private Node<T> head, tail; 
    
    public Queue() {
        head = null;
        tail = null;
    }

    // Adds an item to the end of the queue
    public void enqueue(T t) {
        if(head == null) {
            head = new Node<T>(t, null, null);
            tail = head;
            return;
        }
        tail = new Node<T>(t, null, tail);
    }

    // Removes an item from the front of the queue
    public T dequeue() {
        T data = head.getItem();
        head = head.getNext();
        return data;
    }

    // Returns the first item in the queue
    public T peek() {
        return head.getItem();
    }

    // Returns whether or not the queue is empty
    public boolean isEmpty() {
        return head == null;
    }
}