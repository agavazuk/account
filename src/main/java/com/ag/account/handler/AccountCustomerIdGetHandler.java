package com.ag.account.handler;

import com.ag.account.model.Account;
import com.ag.account.model.Customer;
import com.ag.account.model.Transaction;
import com.ag.account.service.AccountService;
import com.ag.account.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.config.Config;
import com.networknt.handler.LightHttpHandler;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.*;

public class AccountCustomerIdGetHandler implements LightHttpHandler {

    private static final ObjectMapper mapper = Config.getInstance().getMapper();
    private static final AccountService accountService = SingletonServiceFactory.getBean(AccountService.class);
    private static final TransactionService transactionService = SingletonServiceFactory.getBean(TransactionService.class);

    private static final String CUSTOMER_KEY = "customerId";

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        final String customerId = exchange.getQueryParameters().get(CUSTOMER_KEY).getFirst();

        Map<String, Object> response = new HashMap<>(3);

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName("John");
        customer.setSurname("Doe");
        response.put("customer", customer);

        Collection<Account> accounts = accountService.getAccounts(a -> a.getCustomerId().equals(customerId));
        accounts.forEach(account -> {
            account.setBalance(accountService.getBalance(account));
            Collection<Transaction> transactions = transactionService.getTransactions(tr -> tr.getAccount().equals(account));
            account.setTransactions(new ArrayList<Transaction>(transactions));
        });

        response.put("accounts", accounts);

        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().
                send(mapper.writeValueAsString(response));

        //dirty hack to break mem association
        accounts.forEach(account -> {
            account.setTransactions(Collections.emptyList());
        });

    }
}
