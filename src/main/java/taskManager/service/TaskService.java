package taskManager.service;

import taskManager.database.TaskData;
import taskManager.model.Task;

import java.util.List;

public class TaskService {

    private final TaskData td = new TaskData();

    public void addTask(Task t) {
        td.insert(t);
    }

    public List<Task> getAllTasks() {
        return td.getAllTasks();
    }

    public void removeTask(int id) {
        td.delete(id);
    }

    public void view() {
        List<Task> tasks = td.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("=== No Current Tasks ===");
        }
        else {
            tasks.forEach(i -> System.out.println(i.getDetails()));
        }
    }

//    public String findTask(int id) throws InvalidID {
//        Task t = tasks.stream().filter(i -> i.getId() == id)
//                .findFirst().orElseThrow(() -> new InvalidID("Invalid ID for task"));
//        return t.getSummary();
//    }
}
