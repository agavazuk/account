package com.ag.account.handler;

import com.ag.account.service.AccountService;
import com.ag.account.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.config.Config;
import com.networknt.handler.LightHttpHandler;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpServerExchange;

public class AccountCustomerIdPostHandler implements LightHttpHandler {

    private static final ObjectMapper mapper = Config.getInstance().getMapper();
    private static final AccountService accountService = SingletonServiceFactory.getBean(AccountService.class);
    private static final TransactionService transactionService = SingletonServiceFactory.getBean(TransactionService.class);


    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

//        exchange.setres
        exchange.endExchange();


//        Map s = (Map)exchange.getAttachment(BodyHandler.REQUEST_BODY);
//        String json = mapper.writeValueAsString(s);
//        TodoInfo todo = mapper.readValue(json, TodoInfo.class);
    }
}
