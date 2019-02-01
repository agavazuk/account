package com.ag.account.handler;

import com.ag.account.model.Account;
import com.ag.account.model.Customer;
import com.ag.account.service.AccountService;
import com.ag.account.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.config.Config;
import com.networknt.handler.LightHttpHandler;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountCustomerIdGetHandler implements LightHttpHandler {

    private static final ObjectMapper mapper = Config.getInstance().getMapper();
    private static final AccountService accountService = SingletonServiceFactory.getBean(AccountService.class);
    private static final TransactionService transactionService = SingletonServiceFactory.getBean(TransactionService.class);


    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        String customerId = "";

        Map<String, Object> response = new HashMap<>(3);

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName("John");
        customer.setSurname("Doe");
        response.put("customer", new Customer());

        Collection<Account> accounts = accountService.getAccounts(a -> a.getCustomerId().equals(customerId));
        response.put("accounts", accounts);
//        response.put("transactions", transactionService.getTransactions(t -> t));

        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().
                send(mapper.writeValueAsString(response));
//        exchange.endExchange();
    }
}
