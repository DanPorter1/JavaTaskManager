package taskManager.service;

import taskManager.database.TaskData;
import taskManager.exception.DataNotSaved;
import taskManager.exception.TaskNotFound;
import taskManager.model.Task;

import java.sql.SQLException;
import java.util.List;

public class TaskService {

    private final TaskData td = new TaskData();

    public void addTask(Task t) throws SQLException, DataNotSaved {
        td.insert(t);
    }

    public List<Task> getAllTasks() throws SQLException, TaskNotFound {
        List<Task> tasks = td.getAllTasks();
        if (tasks.isEmpty()) {
            throw new TaskNotFound("No tasks found");
        }
        return tasks;
    }

    public void removeTask(int id) throws SQLException {
        td.delete(id);
    }

    public void view() throws SQLException, TaskNotFound {
        List<Task> tasks = td.getAllTasks();
        if (tasks.isEmpty()) {
            throw new TaskNotFound("No tasks found");
        }
        else {
            tasks.forEach(i -> System.out.println(i.getDetails()));
        }
    }

    public void markComplete(int id) throws SQLException, TaskNotFound {
        td.updateComplete(id);
    }

//    public String findTask(int id) throws InvalidID {
//        Task t = tasks.stream().filter(i -> i.getId() == id)
//                .findFirst().orElseThrow(() -> new InvalidID("Invalid ID for task"));
//        return t.getSummary();
//    }
}
