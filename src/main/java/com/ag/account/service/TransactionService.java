package com.ag.account.service;

import com.ag.account.model.Transaction;

import java.util.Collection;
import java.util.function.Predicate;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);

    Collection<Transaction> getTransactions(Predicate<Transaction> filterCondition);

}
