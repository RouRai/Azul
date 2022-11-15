public class Box<T> {

    Node head;
    Node tail;

    public Box () {
        head = null;
        
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
}