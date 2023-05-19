package com.pismo.transaction.routine.integration;

import com.pismo.transaction.routine.domain.Account;
import com.pismo.transaction.routine.infrastructure.rest.dto.AccountDto;
import com.pismo.transaction.routine.infrastructure.rest.dto.TransactionDto;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TransactionRestIntegration {

    private static final String URL = "http://localhost:8091/transaction";

    public static HttpResponse<String> createTransactionIntegration(TransactionDto transactionDto) {
        try {
            JSONObject body = new JSONObject();
            body.put("account_id", transactionDto.getAccountId());
            body.put("operation_type_id", transactionDto.getOperationTypeId());
            body.put("amount", transactionDto.getAmount());

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .headers("Content-Type", "application/json;charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
