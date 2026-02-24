package taskManager.database;

import taskManager.exception.TaskNotFound;
import taskManager.model.Task;
import taskManager.util.Priority;
import taskManager.util.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static taskManager.util.DBConnection.getConnection;

public class TaskData implements DatabaseActions<Task> {

    @Override
    public void insert(Task t){
        String sql = "INSERT INTO tasks (title, description, priority, status) VALUES (?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, t.getTaskTitle());
            preparedStatement.setString(2, t.getTaskDetails());
            preparedStatement.setString(3, t.getPriorityEnum().name());
            preparedStatement.setString(4, t.getStatusEnum().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting new data: " + e.getMessage());
        }
    }

    @Override
    public void update(Task t){}

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new TaskNotFound("Task not found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error Deleting : " + e.getMessage());
        }
    }


    public void createTaskTable(){
        String sql = """
        CREATE TABLE IF NOT EXISTS tasks (
            id INT AUTO_INCREMENT PRIMARY KEY,
            title VARCHAR(255) NOT NULL,
            description TEXT,
            priority VARCHAR(50),
            status VARCHAR(50))""";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement())
        {
            statement.execute(sql);
        } catch (SQLException e) {
            System.err.println("Setup Error: " + e.getMessage());
        }
    }

    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            if (!rs.next()) {
                throw new TaskNotFound("No tasks found");
            }
            while (rs.next()) {
                Task task = new Task(
                        rs.getString("title"),
                        rs.getString("description"),
                        Priority.valueOf(rs.getString("priority")),
                        Status.valueOf(rs.getString("status"))
                );
                task.setId(rs.getInt("id"));
                allTasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        return allTasks;
    }

    public void updateComplete(int id) {
        String sql = "UPDATE tasks SET status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, Status.CLOSED.name());
            preparedStatement.setInt(2, id);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0 ) {
                throw new TaskNotFound("No task found with ID : " + id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
