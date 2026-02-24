package taskManager.database;

import java.sql.SQLException;

public interface DatabaseActions<T> {

    void insert(T item) throws SQLException;
    void update(T item);
    void delete(int id) throws SQLException;

}
