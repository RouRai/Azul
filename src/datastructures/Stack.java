package datastructures;

public class Stack<T> {

    private Node<T> head, tail;

    public Stack() {
        head = null;
        tail = null;
    }

    // Adds an item to the top of the stack
    public void push(T t) {
        if(head == null) {
            head = new Node<T>(t, null, null);
            tail = head;
            return;
        }
        tail = new Node<T>(t, null, tail);
    }

    // Removes the top item in the stack
    public T pop(){
        T data = tail.getItem();
        tail = tail.getPrevious();
        return data;
    }

    // Returns the top item in the stack
    public T peek() {
        return tail.getItem();
    }


    // Returns if the stack is empty or not
    public boolean isEmpty() {
        return tail == null;
    }
}