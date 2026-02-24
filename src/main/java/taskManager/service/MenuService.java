package taskManager.service;

import taskManager.exception.TaskNotFound;
import taskManager.model.Task;
import taskManager.model.Transaction;
import taskManager.util.Priority;
import taskManager.util.Summary;
import taskManager.util.TransType;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private final InputService in = new InputService();
    private final TaskService ts = new TaskService();
    private final ReportingService rs = new ReportingService();
    private final BudgetService bs = new BudgetService();
    private final AuthService as = new AuthService();

    public void mainMenu() {

        boolean loggedIn = false;
        boolean authMenu = true;
        while (authMenu) {
            System.out.println("\n=== TASK MANAGER ===\nYou need to log in or register before continuing");
            System.out.println("1. Log in\n2. Register\n0. Exit");
            int choice = in.readInt("Select an option");
            switch (choice) {
                case 1 -> { if (login()) {
                    System.out.println("Log in successful");
                    loggedIn = true;
                    authMenu = false;
                }}
                case 2 -> { if (createUser()) {
                    System.out.println("User Created ");
                    loggedIn = true;
                    authMenu = false;
                }}
                case 0 -> authMenu = false;
                default -> System.out.println("Please select an option");
            }
        }

        boolean running = loggedIn;
//        addTestData();

        while (running) {
            System.out.println("\n--- TASK MANAGER MENU ---");
            System.out.println("1. Add New Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Mark entry as complete");
            System.out.println("4. View All Tasks");
            System.out.println("5. View Task Priority Report");
            System.out.println("6. Run Budget Report");
            System.out.println("7. View all entries");
            System.out.println("0. Exit");

            int choice = in.readInt("Select an option");

            switch (choice) {
                case 1 -> handleAddTask();
                case 2 -> {
                    ts.view();
                    try {
                        ts.removeTask(in.readInt("Enter ID to remove"));
                    } catch (TaskNotFound e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 3 -> {
                    runOpenReport();
                    updateTask();
                }
                case 4 -> runOCReport(); // Report Logic
                case 5 -> rs.getTaskPriorityReport(ts.getAllTasks());
                case 6 -> System.out.println("Nothing here yet");
                case 7 -> runTaskReport();
                case 0, -1 -> running = false;
                default -> System.out.println("Feature coming soon!");
            }
        }
        System.out.println("Goodbye!");
    }

    private void addTestData(){
        ts.addTask(new Task("Test Task", "This is a testing task 1", Priority.LOW));
        ts.addTask(new Task("Test Task", "This is a testing task 2", Priority.LOW));
        ts.addTask(new Task("Test Task", "This is a testing task 3", Priority.MEDIUM));
        ts.addTask(new Task("Test Task", "This is a testing task 4", Priority.HIGH));
        bs.addTransaction(new Transaction("Car", 1000, TransType.OUTGOING));
        bs.addTransaction(new Transaction("Phone", 100, TransType.OUTGOING));
        bs.addTransaction(new Transaction("Food", 50, TransType.OUTGOING));
        bs.addTransaction(new Transaction("Party", 10, TransType.INCOMING));

    }

    private void handleAddTask() {
        System.out.println("\n--- Create New Task ---");
        String title = in.readString("Enter Title");
        if (title == null) return;
        String desc  = in.readString("Enter Description");
        if (desc == null) return;
        Priority p   = in.readPriority();
        Task newTask = new Task(title, desc, p);
        ts.addTask(newTask);
        System.out.println("Task added successfully!");
    }

    private void runTaskReport() {
        List<Summary> summaryList = new ArrayList<>(ts.getAllTasks());
        rs.getReport(summaryList);
    }

    private void runOpenReport() {
        List<Task> openList = new ArrayList<>(ts.getAllTasks());
        rs.getOpenReport(openList);
    }

    private void runOCReport() {
        List<Task> OCTaskList = new ArrayList<>(ts.getAllTasks());
        rs.getOCReport(OCTaskList);
    }

    private boolean createUser(){
        String username = in.readString("Enter a username");
        String password = in.readString("Enter a password");
        return as.checkUserExist(username, password);
    }

    private boolean login() {
        String username = in.readString("Enter a username");
        String password = in.readString("Enter a password");
        return as.login(username, password);
    }

    private void updateTask() {
        int id = in.readInt("Enter id to update");
        try {
            ts.markComplete(id);
        } catch (TaskNotFound e){
            System.err.println(e.getMessage());
        }
    }
}
