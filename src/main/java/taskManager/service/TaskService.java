package taskManager.service;

import taskManager.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private List<Task> tasks;

    public TaskService() {this.tasks = new ArrayList<>();}

    public void addTask(Task t) {tasks.add(t);}

    public List<Task> getAllTasks(){return tasks;}

}
