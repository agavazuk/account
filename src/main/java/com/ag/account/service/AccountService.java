package com.ag.account.service;

import com.ag.account.model.Account;
import com.ag.account.model.Customer;
import com.ag.account.model.Transaction;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.function.Predicate;

public interface AccountService {

    Account createAccount(Customer user);

    Account createAccount(Customer user, Transaction transaction);

    Collection<Account> getAccounts(Predicate<Account> filter);

    BigDecimal getBalance(Account account);
}
