package taskManager.service;

import taskManager.database.UserData;
import taskManager.exception.DataNotSaved;
import taskManager.exception.InvalidPassword;
import taskManager.exception.UsernameAlreadyUsed;
import taskManager.model.User;

import java.sql.SQLException;

public class AuthService {

    private final UserData ud = new UserData();

    public boolean checkUserExist(String username, String password) throws UsernameAlreadyUsed, SQLException {
        ud.getUserCheck(username);
        User user = new User(username, password);
        ud.insert(user);
        return true;
    }

    public boolean login(String username, String password) throws InvalidPassword, SQLException {
        User user = ud.login(username, password);
        return user != null;
    }
}
