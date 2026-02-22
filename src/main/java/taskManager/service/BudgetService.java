package taskManager.service;

import taskManager.model.Task;
import taskManager.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BudgetService {

    private List<Transaction> transactions;

    public BudgetService() {this.transactions = new ArrayList<>();}

    public void addTransaction(Transaction t) {transactions.add(t);}

    public List<Transaction> getAllTransactions(){return transactions;}

}

