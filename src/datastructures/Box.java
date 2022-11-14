public class Box<T> {

    Node head;
    Node tail;

    public Box () {
        head = null;
        
    }

    public void add (Object o) {
        Node n = new Node(o);
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