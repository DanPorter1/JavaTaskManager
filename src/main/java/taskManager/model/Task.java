package taskManager.model;

import taskManager.util.Priority;
import taskManager.util.Status;
import taskManager.util.Summary;

import java.time.LocalDateTime;
import java.util.Date;

public class Task extends BaseItem implements Summary {

    private String taskTitle;
    private String taskDetails;
    private Priority priorityEnum;
    private Status statusEnum;

    // Constructors
    public Task(String taskTitle, String taskDetails, Priority priorityEnum, LocalDateTime date) {
        super(date);
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.priorityEnum = priorityEnum;
        this.statusEnum = Status.OPEN;
    }

    public Priority getPriority(){return priorityEnum;}

    public String getDetails(){
        return String.format("%-4d Task: %s - %s || %s - %s || Due: %s", getId(), taskTitle, taskDetails, priorityEnum, statusEnum, getDate());
    }

    @Override
    public String getSummary(){
        return String.format("Task: %s - %s || %s - %s || Due: %s", taskTitle, taskDetails, priorityEnum, statusEnum, getDate());
    }
}
