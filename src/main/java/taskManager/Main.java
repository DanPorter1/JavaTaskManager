package taskManager;

import taskManager.model.Task;
import taskManager.model.Transaction;
import taskManager.service.BudgetService;
import taskManager.service.ReportingService;
import taskManager.service.TaskService;
import taskManager.util.Priority;
import taskManager.util.Summary;
import taskManager.util.TransType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Project Main");

        // New Task Service
        TaskService ts = new TaskService();
        // New Budget Service
        BudgetService bs = new BudgetService();
        // New Reporting Service
        ReportingService rs = new ReportingService();

        // Add new Tasks
        ts.addTask(new Task("Test Task", "This is a testing task 1", Priority.LOW, LocalDateTime.now().plusWeeks(1)));
        ts.addTask(new Task("Test Task", "This is a testing task 2", Priority.LOW, LocalDateTime.now().plusWeeks(1)));
        ts.addTask(new Task("Test Task", "This is a testing task 3", Priority.MEDIUM, LocalDateTime.now().plusWeeks(1)));
        ts.addTask(new Task("Test Task", "This is a testing task 4", Priority.HIGH, LocalDateTime.now().plusWeeks(2)));

        // Add new Transactions
        bs.addTransaction(new Transaction("Car", 1000, TransType.OUTGOING));
        bs.addTransaction(new Transaction("Phone", 100, TransType.OUTGOING));
        bs.addTransaction(new Transaction("Food", 50, TransType.OUTGOING));
        bs.addTransaction(new Transaction("Party", 10, TransType.INCOMING));

        // ReportService Report for all Items
        List<Summary> summary = new ArrayList<>();
        summary.addAll(ts.getAllTasks());
        summary.addAll(bs.getAllTransactions());
        rs.getReport(summary);

        // Report for all Tasks
        rs.getTaskPriorityReport(ts.getAllTasks());
    }

}
