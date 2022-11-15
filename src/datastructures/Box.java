import java.util.Iterator;

public class Box<T> implements Iterator{

    private Node head;
    private Node tail;
    private Node current;

    public Box () {
        head = null;
        tail = null;
        current = null;
    }

    public void add (Object o) {
        Node n = new Node(o);
        tail.next = n;
        tail = tail.next;
    }


    public Object remove (Object o) {
        for (Node n = head; n.next != null; n = n.next) {
            if (n.item.equals(o)) {
                Object obj = n.item;
                n.previous.next = n.next;
                return obj;
            }
        }
        return null;
    }

    private class Node {
        Object item;
        Node next;
        Node previous;

        public Node (Object o) {
            item = o;
        }

    }

    @Override
    public boolean hasNext() {
        return !(current.next == null);
    }

    @Override
    public Object next() {
        Object o = current.item;
        current = current.next;
        return o;
    }
}