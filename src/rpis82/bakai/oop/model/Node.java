package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Space;
public class Node {
        Node next;
        Node previous;
        Space value;

        public Node(Node next, Node previous, Space value) {
            this.next = next;
            this.previous = previous;
            this.value = value;
        }
}
