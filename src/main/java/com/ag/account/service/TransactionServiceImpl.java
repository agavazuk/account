package com.ag.account.service;

import com.ag.account.model.Transaction;
import com.networknt.utility.Util;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {

    private Collection<Transaction> repository = new CopyOnWriteArrayList<Transaction>();

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setDate(System.currentTimeMillis());
        transaction.setUuid(Util.getUUID());
        repository.add(transaction);
        return transaction;
    }

    @Override
    public Collection<Transaction> getTransactions(Predicate<Transaction> filterCondition) {
        return repository.stream().filter(filterCondition).collect(Collectors.toSet());
    }
}
