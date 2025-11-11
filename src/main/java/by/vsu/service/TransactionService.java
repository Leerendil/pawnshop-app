package by.vsu.service;

import by.vsu.repository.TransactionRepository;
import by.vsu.tableClasses.Transactions;

public class TransactionService extends Service<Transactions> {

    public TransactionService(TransactionRepository transactionRepository) {
        super(transactionRepository);
    }

}