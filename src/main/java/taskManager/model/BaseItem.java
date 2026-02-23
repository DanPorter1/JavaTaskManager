package taskManager.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseItem {

    private int id;

    public BaseItem(){
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

}