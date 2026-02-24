package taskManager.model;

import taskManager.util.Summary;
import taskManager.util.TransType;

import java.time.LocalDateTime;

public class Transaction extends BaseItem implements Summary {

    private String reference;
    private double amount;
    private TransType type;
    private LocalDateTime date;

    public Transaction(String reference, double amount, TransType type, LocalDateTime date){
        this.reference = reference;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public double getAmount() {
        return amount;
    }

    public TransType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDetails(){
        return String.format("%-4d - Date: %s  Reference: %s - %.2f || %s ", getId(), date, reference, amount, type);
    }

    @Override
    public String getSummary(){
        return String.format("Transaction: %s - %.2f || %s", reference, amount, type);
    }
}
