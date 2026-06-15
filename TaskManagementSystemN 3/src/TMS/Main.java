package TMS;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TaskManger manager = new TaskManger();

    public static void main(String[] args) {
        System.out.println("=== Task Management System ===");

        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice (1-7): ");

            switch (choice) {
                case 1 -> addTask();
                case 2 -> processTask();
                case 3 -> viewTasks();
                case 4 -> searchTasks();
                case 5 -> undoTask();
                case 6 -> manager.displayCompletedTasks();
                case 7 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Add New Task");
        System.out.println("2. Process Next Task");
        System.out.println("3. View Tasks");
        System.out.println("4. Search Tasks");
        System.out.println("5. Undo Last Task");
        System.out.println("6. View Completed Tasks");
        System.out.println("7. Exit");
    }

    private static void addTask() {
        System.out.println("\n--- Add New Task ---");
        int id = getIntInput("Enter task ID: ");

        // Check for duplicate ID
        if (manager.findTaskById(id) != null) {
            System.out.println("Error: Task ID already exists!");
            return;
        }

        String title = getStringInput("Enter task title: ");

        System.out.println("Select priority:");
        System.out.println("1. LOW\n2. MEDIUM\n3. HIGH");
        int priorityChoice = getIntInput("Choice (1-3): ");

        Task.Priority priority;
        switch (priorityChoice) {
            case 1 -> priority = Task.Priority.LOW;
            case 2 -> priority = Task.Priority.MEDIUM;
            case 3 -> priority = Task.Priority.HIGH;
            default -> {
                System.out.println("Invalid choice. Setting to MEDIUM.");
                priority = Task.Priority.MEDIUM;
            }
        }

        manager.addTask(new Task(id, title, priority));
        System.out.println("Task added successfully!");
    }

    private static void processTask() {
        System.out.println("\n--- Process Next Task ---");
        Task task = manager.processNextTask();
        if (task != null) {
            System.out.println("Processed: " + task);
            System.out.println(("Task has been moved to completed tasks"));
        } else {
            System.out.println("No tasks available to process!");
        }
    }

    private static void viewTasks() {
        System.out.println("\n--- View Tasks ---");
        System.out.println("1. All Tasks");
        System.out.println("2. High Priority");
        System.out.println("3. Medium Priority");
        System.out.println("4. Low Priority");

        int choice = getIntInput("Select view option (1-4): ");
        switch (choice) {
            case 1 -> {
                System.out.println("\nAll Tasks:");
                manager.displayAllTasks();
            }
            case 2 -> {
                System.out.println("\nHigh Priority Tasks:");
                manager.getHighPriorityTasks().displayAllTasks();
            }
            case 3 -> {
                System.out.println("\nMedium Priority Tasks:");
                manager.getMediumPriorityTasks().displayAllTasks();
            }
            case 4 -> {
                System.out.println("\nLow Priority Tasks:");
                manager.getLowPriorityTasks().displayAllTasks();
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    private static void searchTasks() {
        System.out.println("\n--- Search Tasks ---");
        System.out.println("1. By ID");
        System.out.println("2. By Title");

        int choice = getIntInput("Select search option (1-2): ");
        switch (choice) {
            case 1 -> {
                int id = getIntInput("Enter task ID: ");
                Task task = manager.findTaskById(id);
                if (task != null) {
                    System.out.println("Found: " + task);
                } else {
                    System.out.println("Task not found!");
                }
            }
            case 2 -> {
                String title = getStringInput("Enter title to search: ");
                TaskLinkedList results = manager.findTasksByTitle(title);
                if (results.size() > 0) {
                    System.out.println("Found " + results.size() + " task(s):");
                    results.displayAllTasks();
                } else {
                    System.out.println("No tasks found with that title!");
                }
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    private static void undoTask() {
        System.out.println("\n--- Undo Last Task ---");
        if (manager.undoLastTask()) {
            System.out.println("Last task undone successfully!");
        } else {
            System.out.println("No tasks to undo!");
        }
    }

    private static void processAllTasks() {
        System.out.println("\n--- Processing All Tasks ---");
        int processedCount = 0;

        Task task;
        while ((task = manager.processNextTask()) != null) {
            System.out.println("Processed: " + task);
            processedCount++;
        }

        if (processedCount == 0) {
            System.out.println("No tasks available to process!");
        } else {
            System.out.println("Processed " + processedCount + " task(s)");
        }
    }

    // Helper methods for input validation
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}