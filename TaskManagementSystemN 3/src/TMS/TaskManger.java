package TMS;

public class TaskManger {
    private TaskLinkedList allTasks = new TaskLinkedList();
    private TaskStack undoStack = new TaskStack();
    private TaskStack completedTasks = new TaskStack();
    private TaskQueue processingQueue = new TaskQueue();
    private TaskLinkedList highPriorityTasks = new TaskLinkedList();
    private TaskLinkedList mediumPriorityTasks = new TaskLinkedList();
    private TaskLinkedList lowPriorityTasks = new TaskLinkedList();



    // === 3a. Manage by Priority ===
    public void addTask(Task task) {
        allTasks.add(task);
        undoStack.push(task);
        processingQueue.enqueue(task);

        switch (task.getPriority()) {
            case HIGH:
                highPriorityTasks.add(task);
                break;
            case MEDIUM:
                mediumPriorityTasks.add(task);
                break;
            case LOW:
                lowPriorityTasks.add(task);
                break;
        }
    }

    // === 3b. Process Tasks (FIFO) ===
    // In TaskManager.java
    public Task processNextTask() {
        Task task = processingQueue.dequeue();
        if (task == null) {
            return null;

        }

        task.setCompleted(true);
        completedTasks.push(task);

        // Remove from priority lists
        switch(task.getPriority()) {
            case HIGH: highPriorityTasks.remove(task.getId()); break;
            case MEDIUM: mediumPriorityTasks.remove(task.getId()); break;
            case LOW: lowPriorityTasks.remove(task.getId()); break;
        }

        return task;
    }

    public void displayCompletedTasks() {
        System.out.println("=== COMPLETED TASKS ===");
        if (completedTasks.isEmpty()) {
            System.out.println("No completed tasks yet");
        } else {
            // Temporary stack to preserve order
            TaskStack tempStack = new TaskStack();

            // Pop from completedTasks and push to tempStack (reversing order)
            while (!completedTasks.isEmpty()) {
                Task task = completedTasks.pop();
                System.out.println(task);
                tempStack.push(task);
            }

            // Restore original stack
            while (!tempStack.isEmpty()) {
                completedTasks.push(tempStack.pop());
            }
        }
    }

    // === 3c. Undo Last Operation ===
    public boolean undoLastTask() {
        Task lastTask = undoStack.pop();
        if (lastTask == null) return false;
        return allTasks.remove(lastTask.getId());
    }



    // === 4a. Search Algorithms ===
    public Task findTaskById(int id) {
        return allTasks.findById(id);
    }

    public TaskLinkedList findTasksByTitle(String title) {
        return allTasks.findByTitle(title);
    }

    // === 4b. Priority Views ===
    public TaskLinkedList getHighPriorityTasks() { return highPriorityTasks; }
    public TaskLinkedList getMediumPriorityTasks() { return mediumPriorityTasks; }
    public TaskLinkedList getLowPriorityTasks() { return lowPriorityTasks; }

    // === Utility Methods ===
    public void displayAllTasks() {
        System.out.println("=== ALL TASKS ===");
        allTasks.displayAllTasks();
    }
}


