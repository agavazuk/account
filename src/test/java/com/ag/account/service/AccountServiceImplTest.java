package com.ag.account.service;

import com.ag.account.model.Account;
import com.ag.account.model.Customer;
import com.ag.account.model.Transaction;
import com.networknt.service.SingletonServiceFactory;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountServiceImplTest {

    private TransactionService transactionSevice = SingletonServiceFactory.getBean(TransactionService.class);
    private AccountService accountService = SingletonServiceFactory.getBean(AccountService.class);

    private Customer customer = new Customer();

    {
        customer.setCustomerId("0");
    }

    @Test
    public void testCreateEmptyAccount() {

        Account account = accountService.createAccount(customer);

        Assert.assertEquals(BigDecimal.ZERO, accountService.getBalance(account));
    }

    @Test
    public void testCreateAccountWithInitial() {

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        Account account = accountService.createAccount(customer, transaction);

        Assert.assertEquals(BigDecimal.valueOf(100), accountService.getBalance(account));
    }

    @Test
    public void testZeroBalanceAccounts() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        Account account = accountService.createAccount(customer, transaction);

        Assert.assertEquals(BigDecimal.valueOf(100), accountService.getBalance(account));

        transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setCredit(false);
        transactionSevice.createTransaction(transaction);

        Assert.assertEquals(BigDecimal.ZERO, accountService.getBalance(account));

    }

    @Test
    public void getAddAccountBalance() {

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        Account account = accountService.createAccount(customer, transaction);

        Assert.assertEquals(BigDecimal.valueOf(100), accountService.getBalance(account));

        transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setCredit(true);
        transactionSevice.createTransaction(transaction);

        Assert.assertEquals(BigDecimal.valueOf(200), accountService.getBalance(account));

    }
}