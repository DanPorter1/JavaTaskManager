package taskManager.model;

import org.junit.Test;
import taskManager.util.Priority;
import taskManager.util.Status;

import static org.junit.Assert.*;

public class TaskTest {

    private Task task;

    @Test
    public void testTaskCreationAll() {
        // Arrange
        Task task = new Task("Test Task", "Test Description", Priority.HIGH, Status.OPEN);

        // Act
        String title = task.getTaskTitle();
        String details = task.getTaskDetails();
        Priority priority = task.getPriorityEnum();
        Status status = task.getStatusEnum();

        // Assert
        assertEquals("Test Task", title);
        assertEquals("Test Description", details);
        assertEquals(Priority.HIGH, priority);
        assertEquals(Status.OPEN, status);
    }


    @Test
    public void testGetDetails() {
        // Arrange
        Task task = new Task("Test Task", "Test Description", Priority.HIGH, Status.OPEN);
        task.setId(1);

        // Act
        String expectedDetails = "ID: 1    Task: Test Task - Test Description || HIGH - OPEN";

        // Assert
        assertEquals(expectedDetails, task.getDetails());
    }

    @Test
    public void testGetSummary() {
        // Arrange
        Task task = new Task("Test Task", "Test Description", Priority.HIGH, Status.OPEN);

        // Act
        String expectedSummary = "Task: Test Task - Test Description || HIGH - OPEN";

        // Assert
        assertEquals(expectedSummary, task.getSummary());
    }
}
