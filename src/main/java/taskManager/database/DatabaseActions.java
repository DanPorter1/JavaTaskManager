package taskManager.database;

import taskManager.exception.DataNotSaved;
import taskManager.model.Transaction;

import java.sql.SQLException;

public interface DatabaseActions<T> {

    void insert(T item) throws SQLException, DataNotSaved;
    void update(T item);
    void delete(int id) throws SQLException;

}
