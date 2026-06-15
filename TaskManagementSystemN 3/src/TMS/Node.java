package TMS;

public class Node {
    private Task task;
    private Node next;

    // Constructor
    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}
