package datastructures;

public class Stack<T> {
    private Node<T> head;
    private Node<T> tail;

    public Stack() {
        head = null;
        tail = null;
    }

    public void push(T t) {
        if(head == null) {
            head = new Node<T>(t, null, null);
            tail = head;
            return;
        }
        tail = new Node<T>(t, null, tail);
    }

    public T pop(){
        T data = tail.getItem();
        tail = tail.getPrevious();
        return data;
    }

    public T peek() {
        return tail.getItem();
    }

    public boolean isEmpty() {
        return tail == null;
    }
}