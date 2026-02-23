package taskManager.service;

import taskManager.database.UserData;
import taskManager.model.User;

public class AuthService {

    private final UserData ud = new UserData();

    public boolean checkUserExist(String username, String password) {
        if (!ud.getUserCheck(username)) {
            User user = new User(username, password);
            ud.insert(user);
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        User user = ud.login(username, password);
        return user != null;
    }
}
