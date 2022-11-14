public class Box<Object> {

    Node head;
    private Node current;

    public Box<Object> () {
        current = head;
    }

    public void add (Object o) {
        Node n = new Node(o);
        current.next = n;
        current = current.next;
    }

    public Object remove (Object o) {
        for (Node n = head; n.next != null; n = n.next) {
            if (n.item.equals(o)) {
                return n.item;
            }
        }
        return null;
    }

    private class Node {
        Object item;
        Node next;

        public Node (Object o) {
            item = o;
        }

    }
}