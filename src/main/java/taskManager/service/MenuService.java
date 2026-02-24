package taskManager.service;

import taskManager.exception.InvalidPassword;
import taskManager.exception.TaskNotFound;
import taskManager.exception.UsernameAlreadyUsed;
import taskManager.model.Task;
import taskManager.model.Transaction;
import taskManager.util.Priority;
import taskManager.util.Summary;
import taskManager.util.TransType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private final InputService in = new InputService();
    private final TaskService ts = new TaskService();
    private final ReportingService rs = new ReportingService();
    private final BudgetService bs = new BudgetService();
    private final AuthService as = new AuthService();

    public void authMenu() {
        boolean authMenu = true;
        while (authMenu) {
            System.out.println("\n=== TASK MANAGER ===");
            System.out.println("You need to log in or register before continuing");
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            int choice = in.readInt("Select an option");
            switch (choice) {
                case 1 -> { if (login()) {
                    System.out.println("Log in successful");
                    authMenu = false;
                    mainMenu();
                }}
                case 2 -> { if (createUser()) {
                    System.out.println("User Created ");
                    authMenu = false;
                }}
                case 0 -> authMenu = false;
                default -> System.out.println("Please select an option");
            }
        }
    }

    private void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- TASK MANAGER MAIN MENU ---");
            System.out.println("1. Tasks");
            System.out.println("2. Budget");
            System.out.println("3. All Tasks View");
            System.out.println("4. All Transaction View");
            System.out.println("5. All Summary");
            System.out.println("6. TESTING  --- ADD SAMPLE DATA ");
            System.out.println("0. Exit");

            int choice = in.readInt("Select an option");
            switch (choice) {
                case 1 -> taskMenu();
                case 2 -> budgetMenu();
                case 3 -> runTaskReport();
                case 4 -> System.out.println("Feature coming soon!");
                case 5 -> runTaskReport(); //TODO Update with budget
                case 6 -> addTestData();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void taskMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- TASK SUB-MENU ---");
            System.out.println("1. Add New Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Mark Complete");
            System.out.println("4. View Open");
            System.out.println("5. View Closed");
            System.out.println("6. View All");
            System.out.println("7. View High Priority");
            System.out.println("8. Priority Report");
            System.out.println("0. Back");

            int choice = in.readInt("Select");
            try {
                switch (choice) {
                    case 1 -> handleAddTask();
                    case 2 -> removeTask();
                    case 3 -> updateTask();
                    case 4 -> runOpenReport();
                    case 5 -> runClosedReport(); //TODO Update with Closed
                    case 6 -> rs.getOCReport(ts.getAllTasks());
                    case 7 -> rs.getTaskPriorityReport(ts.getAllTasks()); //TODO Update with High Priority
                    case 8 -> rs.getTaskPriorityReport(ts.getAllTasks());
                    case 0 -> back = true;
                }
            } catch (SQLException | TaskNotFound e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void budgetMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- BUDGET SUB-MENU ---");
            System.out.println("1. Add Outgoing");
            System.out.println("2. Add Incoming");
            System.out.println("3. View Outgoing");
            System.out.println("4. View Incoming");
            System.out.println("5. View Month");
            System.out.println("0. Back");

            int choice = in.readInt("Select");
            switch (choice) {
                case 1 -> System.out.println("Feature coming soon!");
                case 2 -> System.out.println("Feature coming soon!");
                case 3 -> System.out.println("Feature coming soon!");
                case 4 -> System.out.println("Feature coming soon!");
                case 5 -> System.out.println("Feature coming soon!");
                case 0 -> back = true;
                default -> System.out.println("Feature coming soon!");
            }
        }
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
        try {
            List<Summary> summaryList = new ArrayList<>(ts.getAllTasks());
            rs.getReport(summaryList);
        } catch (SQLException | TaskNotFound e) {
            System.err.println(e.getMessage());
        }
    }

    private void runOpenReport() {
        try {
            List<Task> openList = new ArrayList<>(ts.getAllTasks());
            rs.getOpenReport(openList);
        } catch (TaskNotFound | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void runClosedReport() {
        try {
            List<Task> closedList = new ArrayList<>(ts.getAllTasks());
            rs.getClosedReport(closedList);
        } catch (SQLException | TaskNotFound e) {
            System.err.println(e.getMessage());
        }
    }

    private void runOCReport() {
        try {
            List<Task> OCTaskList = new ArrayList<>(ts.getAllTasks());
            rs.getOCReport(OCTaskList);
        } catch (SQLException | TaskNotFound e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean createUser() {
        String username = in.readString("Enter a username");
        String password = in.readString("Enter a password");
        try {
            return as.checkUserExist(username, password);
        } catch (UsernameAlreadyUsed | SQLException e) {
            System.err.println("Unable to register successfully : " + e.getMessage());
            return false;
        }
    }

    private boolean login() {
        String username = in.readString("Enter a username");
        String password = in.readString("Enter a password");
        try {
            return as.login(username, password);
        } catch (InvalidPassword | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    private void updateTask() {
        try {
            rs.getOpenReport(ts.getAllTasks());
            int id = in.readInt("Enter id to update");
            ts.markComplete(id);
        } catch (TaskNotFound | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void removeTask() {
        try {
            rs.getOCReport(ts.getAllTasks());
            ts.removeTask(in.readInt("ID to remove"));
        } catch (SQLException | TaskNotFound e) {
            System.err.println(e.getMessage());
        }
    }
}
