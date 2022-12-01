package datastructures;

public class Testing {
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();

        ll.add("hi");
        ll.add("there");
        ll.add("fellow");

        for(String s : ll) {
            System.out.println(s);
        }

        for(Node<String> n = ll.getFirst(); n != null; n = n.getNext()) {
            System.out.println(n.getItem());
        }
    }
}
