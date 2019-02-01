package com.ag.account.service;

import com.ag.account.model.Account;
import com.ag.account.model.Customer;
import com.ag.account.model.Transaction;
import com.networknt.utility.Util;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {

    private CopyOnWriteArraySet<Account> repository = new CopyOnWriteArraySet<>();

    private TransactionService transactionService;

    public AccountServiceImpl() {
    }

    public AccountServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public Account createAccount(Customer user) {
        Account account = new Account();
        account.setCustomerId(user.getCustomerId());
        account.setCreatedDate(System.currentTimeMillis());
        account.setUuid(Util.getUUID());

        repository.add(account);
        return account;
    }

    @Override
    public Account createAccount(Customer user, Transaction transaction) {
        Account account = createAccount(user);
        transactionService.createTransaction(transaction);
        return account;
    }

    @Override
    public Collection<Account> getAccounts(Predicate<Account> filter) {
        return repository.stream().filter(filter).collect(Collectors.toSet());
    }
}
