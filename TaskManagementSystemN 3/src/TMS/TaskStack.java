package TMS;

public class TaskStack {
    private Node top;
    private int size;

    private static class Node {
        private Task task;
        private Node next;

        public Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    public TaskStack() {
        top = null;
        size = 0;
    }

    public void push(Task task) {
        Node newNode = new Node(task);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public Task pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Task task = top.task;
        top = top.next;
        size--;
        return task;
    }

    public Task peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.task;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void displayStack() {
        Node current = top;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }
}