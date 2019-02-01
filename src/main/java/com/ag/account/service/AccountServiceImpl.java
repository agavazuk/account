package com.ag.account.service;

import com.ag.account.model.Account;
import com.ag.account.model.Customer;
import com.ag.account.model.Transaction;
import com.networknt.utility.Util;

import java.math.BigDecimal;
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
        if (!transaction.getAmount().equals(BigDecimal.ZERO))
            transaction.setAccount(account);
        transaction.setCredit(true);
        transactionService.createTransaction(transaction);

        account.setBalance(transaction.getAmount());

        return account;
    }

    @Override
    public Collection<Account> getAccounts(Predicate<Account> filter) {
        return repository.stream().filter(filter).collect(Collectors.toSet());
    }

    @Override
    public BigDecimal getBalance(Account account) {
        return transactionService.
                getTransactions(t -> t.getAccount().equals(account)).
                stream().map(tr -> tr.getCredit() ? tr.getAmount() : tr.getAmount().multiply(BigDecimal.valueOf(-1))).
                reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
