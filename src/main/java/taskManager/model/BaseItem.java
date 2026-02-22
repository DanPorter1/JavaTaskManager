package taskManager.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseItem {

    private static int taskIdInc = 0;

    private final int id;
    private LocalDateTime date;

    // TODO Get ID from saved items in DB
    public BaseItem(LocalDateTime date){
        this.id = ++taskIdInc;
        this.date = date;
    }

    // Default items to 1 week if not entered
    public BaseItem(){
        taskIdInc++;
        this.id = taskIdInc;
        this.date = LocalDateTime.now().plusWeeks(1);
    }

    public int getId(){
        return this.id;
    }

    public String getDate(){
        LocalDateTime date = LocalDateTime.now().plusWeeks(1);
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.date.format(formatDate);
    }
}