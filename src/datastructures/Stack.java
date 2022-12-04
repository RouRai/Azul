package datastructures;

public class Stack<T> {

    private LinkedList<T> linkedList;

    public Stack() {
        linkedList = new LinkedList<>();
    }

    public void push(T t) {
        linkedList.add(t);
    }

    public T pop(){
        return linkedList.removeLast(linkedList.getLast().getItem());
    }

    public T peek() {
        return linkedList.getLast().getItem();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return linkedList.getSize();
    }

    public void printString() {
        for(T s : linkedList) {
            System.out.println(s);
        }
    }
}