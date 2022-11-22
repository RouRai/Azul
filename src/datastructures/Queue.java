package datastructures;

public class Queue<T> {
    private Node<T> head;
    private Node<T> tail; 
    
    public Queue() {
        head = null;
        tail = null;
    }

    public void enqueue(T t) {
        if(head == null) {
            head = new Node<T>(t, null, null);
            tail = head;
            return;
        }
        tail = new Node<T>(t, null, tail);
    }

    public T dequeue() {
        T data = head.getItem();
        head = head.getNext();
        return data;
    }

    public T peek() {
        return head.getItem();
    }

    public boolean isEmpty() {
        return head == null;
    }
}