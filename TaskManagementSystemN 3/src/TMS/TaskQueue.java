package TMS;

public class TaskQueue {
    private Node front;
    private Node rear;
    private int size;

    private static class Node {
        private Task task;
        private Node next;

        public Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    public TaskQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(Task task) {
        Node newNode = new Node(task);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    public Task dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Task task = front.task;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return task;
    }

    public Task peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.task;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }


    public void displayQueue() {
        Node current = front;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }
}