package data_structures;

import java.util.ArrayList;
import java.util.List;

/**
 * stores string in a tree structures
 * O(n) for adding and retrieving
 */
public class Trie {
    Node root;

    public Trie() {
        root = new Node(' ');
    }

    /**
     * adds the string to the tree
     * @param str non empty
     */
    public void add(String str) {
        Node curr = root;
        for (char c : str.toCharArray()) {
            Node next = curr.getNext(c);
            if (next == null) {
                next = new Node(c);
                curr.next.add(next);
            }
            curr = next;
        }
    }

    /**
     * @param str non empty
     * @return the node with prefix or null if prefix not found
     */
    public Node get(String str) {
        Node curr = root;
        for (char c : str.toCharArray()) {
            curr = curr.getNext(c);
            if (curr == null) {
                return null;
            }
        }
        return curr;
    }

}

class Node {
    List<Node> next;
    char c;

    public Node(char c) {
        this.c = c;
        this.next = new ArrayList<>();
    }

    public Node getNext(char c) {
        for (Node nextNode : next) {
            if (nextNode.c == c) {
                return nextNode;
            }
        }
        return null;
    }
}
