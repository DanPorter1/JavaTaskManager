package taskManager.service;
import taskManager.model.Task;
import taskManager.util.Priority;
import taskManager.util.Status;
import taskManager.util.Summary;
import java.util.List;

public class ReportingService {

    public ReportingService(){}

    public void getReport(List<Summary> summary){
        System.out.println("=== All Items ===");
        summary.stream()
                .forEach(i -> System.out.println(i.getSummary()));
    }

    public void getOpenReport(List<Task> openList){
        openList.stream()
                .filter(i -> i.getStatusEnum() == Status.OPEN)
                .forEachOrdered(i -> System.out.println(i.getDetails()));
    }

    public void getOCReport(List<Task> OCList){
        System.out.println("\n=== Tasks by Status ===");
        if (OCList.stream().anyMatch(i -> i.getStatusEnum() == Status.OPEN)) {
            System.out.println("== Open Tasks ==");
            OCList.stream()
                    .filter(i -> i.getStatusEnum() == Status.OPEN)
                    .forEachOrdered(i -> System.out.println(i.getDetails()));
        }
        if (OCList.stream().anyMatch(i -> i.getStatusEnum() == Status.CLOSED)) {
            System.out.println("== Closed Tasks ==");
            OCList.stream()
                    .filter(i -> i.getStatusEnum() == Status.CLOSED)
                    .forEachOrdered(i -> System.out.println(i.getDetails()));
        }
    }

    public void getTaskPriorityReport(List<Task> tasks) {
        System.out.println("\n=== TASKS BY PRIORITY ===");
        if (tasks.stream().anyMatch(i -> i.getPriorityEnum() == Priority.HIGH)) {
            System.out.println("== High Priority ==");
            tasks.stream()
                    .filter(i -> i.getPriorityEnum() == (Priority.HIGH))
                    .forEach(i -> System.out.println(i.getSummary()));
        }
        if (tasks.stream().anyMatch(i -> i.getPriorityEnum() == Priority.MEDIUM)) {
            System.out.println("== Medium Priority ==");
            tasks.stream()
                    .filter(i -> i.getPriorityEnum() == (Priority.MEDIUM))
                    .forEach(i -> System.out.println(i.getSummary()));
        }
        if (tasks.stream().anyMatch(i -> i.getPriorityEnum() == Priority.LOW)) {
            System.out.println("== Low Priority ==");
            tasks.stream()
                    .filter(i -> i.getPriorityEnum() == (Priority.LOW))
                    .forEach(i -> System.out.println(i.getSummary()));
        }
    }

}
