
package com.ag.account.handler;

import com.networknt.exception.ApiException;
import com.networknt.exception.ClientException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;


public class AccountCustomerIdPostHandlerTest {
    @ClassRule
    public static TestServer server = TestServer.getInstance();

    static final Logger logger = LoggerFactory.getLogger(AccountCustomerIdPostHandlerTest.class);
    static final boolean enableHttp2 = server.getServerConfig().isEnableHttp2();
    static final boolean enableHttps = server.getServerConfig().isEnableHttps();
    static final int httpPort = server.getServerConfig().getHttpPort();
    static final int httpsPort = server.getServerConfig().getHttpsPort();
    static final String url = enableHttps ? "https://localhost:" + httpsPort : "http://localhost:" + httpPort;


    @Test
    public void testPostAccount() throws ClientException, ApiException, IOException {
        URL connection = new URL(url + "/account/customerId");

        HttpURLConnection con = (HttpURLConnection) connection.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        DataOutputStream out = new DataOutputStream(con.getOutputStream());

        out.writeBytes("{\"initialAmount\":999}");
        out.flush();
        out.close();

        int status = con.getResponseCode();
        Assert.assertEquals(200, status);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), UTF_8));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();
        Assert.assertTrue(content.indexOf("customerId") != -1);
        Assert.assertTrue(content.indexOf("null") != -1);

    }


    @Test
    public void testPostAndGetAccount() throws ClientException, ApiException, IOException {
        URL connection = new URL(url + "/account/customerId");

        HttpURLConnection con = (HttpURLConnection) connection.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        DataOutputStream out = new DataOutputStream(con.getOutputStream());

        out.writeBytes("{\"initialAmount\":999}");
        out.flush();
        out.close();

        int status = con.getResponseCode();
        Assert.assertEquals(200, status);

        con.disconnect();

        con = (HttpURLConnection) connection.openConnection();

        con.setRequestMethod("GET");
        status = con.getResponseCode();
        Assert.assertEquals(200, status);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), UTF_8));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();
        Assert.assertTrue(content.indexOf("999") != -1);
    }



}

