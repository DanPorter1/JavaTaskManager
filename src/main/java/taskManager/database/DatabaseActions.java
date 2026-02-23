package taskManager.database;

public interface DatabaseActions<T> {

    void insert(T item);
    void update(T item);
    void delete(int id);

}
