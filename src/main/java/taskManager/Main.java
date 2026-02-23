package taskManager;

import taskManager.database.TaskData;

import taskManager.database.UserData;
import taskManager.service.*;
import taskManager.util.DBConnection;


public class Main {

    public static void main(String[] args) {
        System.out.println("Project Main");
        // Test DB Connection
        DBConnection.testConnection();
        // New Menu Service
        MenuService menu = new MenuService();
        // New TaskDBCon
        TaskData tDB = new TaskData();
        // Create Task Table if not exist
        tDB.createTaskTable();
        // New UserDBCon
        UserData uDB = new UserData();
        // Create User Table if not exist
        uDB.createUserTable();


        menu.mainMenu();

    }

}
