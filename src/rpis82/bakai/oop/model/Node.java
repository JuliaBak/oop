package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Space;
public class Node {
        Node following;
        Node previous;
        Space value;

        public Node(Node following, Node previous, Space space) {
            this.following = following;
            this.previous = previous;
            this.value = space;
        }
}
