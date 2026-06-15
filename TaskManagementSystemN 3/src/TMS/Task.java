package TMS;

public class Task {
    private int id;
    private String title;
    private Priority priority;  // Enum: LOW, MEDIUM, HIGH
    private boolean completed;

    // Constructor
    public Task(int id, String title, Priority priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    // Enum for priority
    public enum Priority { LOW, MEDIUM, HIGH }

    // Getters & Setters (generate these via IDE or write manually)
    public int getId() { return id; }
    public String getTitle() { return title; }
    public Priority getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    // Optional: For debugging
    @Override
    public String toString() {
        return String.format("Task [ID=%d, Title=%s, Priority=%s, Completed=%b]",
                id, title, priority, completed);
    }
}
