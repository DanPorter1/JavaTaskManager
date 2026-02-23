package taskManager.model;

import taskManager.util.Priority;
import taskManager.util.Status;
import taskManager.util.Summary;

import java.time.LocalDateTime;

public class Task extends BaseItem implements Summary {

    private String taskTitle;
    private String taskDetails;
    private Priority priorityEnum;
    private Status statusEnum;

    // Constructors
    public Task(String taskTitle, String taskDetails, Priority priorityEnum, Status statusEnum) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.priorityEnum = priorityEnum;
        this.statusEnum = statusEnum;
    }

    public Task(String taskTitle, String taskDetails, Priority priorityEnum) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.priorityEnum = priorityEnum;
        this.statusEnum = Status.OPEN;
    }

    public String getTaskTitle() {return taskTitle;}

    public String getTaskDetails() {return taskDetails;}

    public Status getStatusEnum() {return statusEnum;}

    public Priority getPriorityEnum() {return priorityEnum;}

    public String getDetails(){
        return String.format("ID: %-4d Task: %s - %s || %s - %s", getId(), taskTitle, taskDetails, priorityEnum, statusEnum);
    }

    @Override
    public String getSummary(){
        return String.format("Task: %s - %s || %s - %s", taskTitle, taskDetails, priorityEnum, statusEnum);
    }
}
