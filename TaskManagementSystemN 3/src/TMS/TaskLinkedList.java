package TMS;

public class TaskLinkedList {
    private Node head;
    private int size;

    private static class Node {
        private Task task;
        private Node next;

        public Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }
    public TaskLinkedList() {
        head = null;
        size = 0;
    }
    public void add(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    public boolean remove(int id) {
        if (head == null) return false;

        if (head.task.getId() == id) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.task.getId() == id) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public Task findById(int id) {
        Node current = head;
        while (current != null) {
            if (current.task.getId() == id) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public TaskLinkedList findByTitle(String title) {
        TaskLinkedList result = new TaskLinkedList();
        Node current = head;
        while (current != null) {
            if (current.task.getTitle().equalsIgnoreCase(title)) {
                result.add(current.task);
            }
            current = current.next;
        }
        return result;
    }

    public TaskLinkedList getTasksByPriority(Task.Priority priority) {
        TaskLinkedList result = new TaskLinkedList();
        Node current = head;
        while (current != null) {
            if (current.task.getPriority() == priority) {
                result.add(current.task);
            }
            current = current.next;
        }
        return result;
    }

    public boolean markTaskCompleted(int id) {
        Task task = findById(id);
        if (task != null) {
            task.setCompleted(true);
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void displayAllTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public Task getTaskAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.task;
    }
}
