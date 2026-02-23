package taskManager.model;

import taskManager.util.Summary;
import taskManager.util.TransType;

import java.time.LocalDateTime;

public class Transaction extends BaseItem implements Summary {

    private String reference;
    private double amount;
    private TransType type;

    public Transaction(String reference, double amount, TransType type){
        this.reference = reference;
        this.amount = amount;
        this.type = type;
    }

    public String getDetails(){
        return String.format("%-4d Transaction: %s - %.2f || %s", getId(), reference, amount, type);
    }

    @Override
    public String getSummary(){
        return String.format("Transaction: %s - %.2f || %s", reference, amount, type);
    }
}
