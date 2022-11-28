import java.util.Iterator;

public class Box<T> implements Iterator{

    private Node head;
    private Node current;

    public Box () {
        head = null;
        current = null;
    }

    public void add (Object o) {
        Node n = new Node(o);
        if (head == null) {
            head = n;
        }
        current.next = n;
        current = current.next;
        
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