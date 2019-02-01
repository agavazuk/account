package com.ag.account.service;

import com.ag.account.model.Account;
import com.ag.account.model.Transaction;
import com.networknt.service.SingletonServiceFactory;
import com.networknt.utility.Util;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class TransactionServiceImplTest {

    private TransactionService transactionSevice = SingletonServiceFactory.getBean(TransactionService.class);
    private final Map<String, Account> accounts = new HashMap();

    {
        Account account = new Account();
        account.setUuid(Util.getUUID());
        account.setCustomerId("BOB");
        account.setCreatedDate(System.currentTimeMillis());

        accounts.put("BOB", account);

        account = new Account();
        account.setUuid(Util.getUUID());
        account.setCustomerId("JO");
        account.setCreatedDate(System.currentTimeMillis());

        accounts.put("JO", account);
    }


    @Test
    public void createTransactionTest() {
        Transaction transaction = new Transaction();
        transaction.setAccount(accounts.get("BOB"));
        transaction.setDate(System.currentTimeMillis());
        transaction.setUuid(Util.getUUID());
        transaction.setCredit(true);
        transactionSevice.createTransaction(transaction);

        assertTrue(transactionSevice.getTransactions(t -> t.getAccount().equals(accounts.get("BOB"))).size() == 1);
    }

}