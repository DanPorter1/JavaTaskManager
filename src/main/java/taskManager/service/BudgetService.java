package taskManager.service;

import taskManager.database.TransactionData;
import taskManager.exception.DataNotSaved;
import taskManager.model.Transaction;

import java.sql.SQLException;
import java.util.List;

public class BudgetService {

    private final TransactionData bd = new TransactionData();

    public void addTransaction(Transaction t) throws DataNotSaved, SQLException {
        bd.insert(t);
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        return null;
    }
}

