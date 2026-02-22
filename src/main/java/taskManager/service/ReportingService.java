package taskManager.service;
import org.w3c.dom.ls.LSOutput;
import taskManager.model.Task;
import taskManager.util.Priority;
import taskManager.util.Summary;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportingService {

    public ReportingService(){};

    public void getReport(List<Summary> summary){
        System.out.println("=== All Items ===");
        summary.stream()
                .forEach(i -> System.out.println(i.getSummary()));
    }

    public void getTaskPriorityReport(List<Task> tasks) {
        System.out.println("\n=== TASKS BY PRIORITY ===");
        if (tasks.stream().anyMatch(i -> i.getPriority() == Priority.HIGH)) {
            System.out.println("== High Priority ==");
            tasks.stream()
                    .filter(i -> i.getPriority() == (Priority.HIGH))
                    .forEach(i -> System.out.println(i.getSummary()));
        }
        if (tasks.stream().anyMatch(i -> i.getPriority() == Priority.MEDIUM)) {
            System.out.println("== Medium Priority ==");
            tasks.stream()
                    .filter(i -> i.getPriority() == (Priority.MEDIUM))
                    .forEach(i -> System.out.println(i.getSummary()));
        }
        if (tasks.stream().anyMatch(i -> i.getPriority() == Priority.LOW)) {
            System.out.println("== Low Priority ==");
            tasks.stream()
                    .filter(i -> i.getPriority() == (Priority.LOW))
                    .forEach(i -> System.out.println(i.getSummary()));
        }
    }

}
