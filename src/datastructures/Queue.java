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
        return ll.remove(ll.getFirst().getItem());
    }

    public T peek() {
        return ll.getFirst().getItem();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return ll.getSize();
    }
}