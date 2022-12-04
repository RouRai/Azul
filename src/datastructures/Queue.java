package datastructures;

public class Queue<T> {
    LinkedList<T> ll;
    
    public Queue() {
        ll = new LinkedList<>();
    }

    public void enqueue(T t) {
        ll.add(t);
    }

    public T dequeue() {
        if(isEmpty()) {
            return null;
        }
        return ll.remove(ll.getFirst().getItem());
    }

    public T peek() {
        if(isEmpty()) {
            return null;
        }
        return ll.getFirst().getItem();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return ll.getSize();
    }
}