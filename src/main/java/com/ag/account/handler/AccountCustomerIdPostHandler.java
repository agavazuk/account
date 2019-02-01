package com.ag.account.handler;

import com.ag.account.model.Account;
import com.ag.account.model.Customer;
import com.ag.account.model.Transaction;
import com.ag.account.service.AccountService;
import com.ag.account.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.body.BodyHandler;
import com.networknt.config.Config;
import com.networknt.handler.LightHttpHandler;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class AccountCustomerIdPostHandler implements LightHttpHandler {

    private static final ObjectMapper mapper = Config.getInstance().getMapper();
    private static final AccountService accountService = SingletonServiceFactory.getBean(AccountService.class);
    private static final TransactionService transactionService = SingletonServiceFactory.getBean(TransactionService.class);

    private static final String CUSTOMER_KEY = "customerId";


    static Function<Object, BigDecimal> valueMapper = new Function<Object, BigDecimal>() {
        @Override
        public BigDecimal apply(Object o) {
            if (o instanceof Double) {
                return BigDecimal.valueOf((Double) o);
            } else if (o instanceof Integer) {
                return BigDecimal.valueOf((Integer) o);
            } else if (o instanceof String) {
                return BigDecimal.valueOf(Double.parseDouble((String) o));
            } else {
                throw new IllegalArgumentException("Cannot get the numeric value from " + o);
            }

        }
    };

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        final String customerId = exchange.getQueryParameters().get(CUSTOMER_KEY).getFirst();

        Map optionalValues = Optional.ofNullable(exchange.getAttachment(BodyHandler.REQUEST_BODY)).
                map(obj -> (Map) obj).orElse(Collections.EMPTY_MAP);

        final BigDecimal initialAmount = Optional.ofNullable(optionalValues.get("initialAmount")).
                map(valueMapper).orElse(BigDecimal.ZERO);

        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        Transaction transaction = new Transaction();
        transaction.setCredit(true);
        transaction.setAmount(initialAmount);
        Account account = accountService.createAccount(customer, transaction);

        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().
                send(mapper.writeValueAsString(account));
    }
}
