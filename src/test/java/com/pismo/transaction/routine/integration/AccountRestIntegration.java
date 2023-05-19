package com.pismo.transaction.routine.integration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AccountRestIntegration {

    private static final String URL = "http://localhost:8091/account";

    public static HttpResponse<String> createAccountIntegration(String documentNumber) {
        try {
            JSONObject body = new JSONObject();
            body.put("document_number", documentNumber);

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

    public static HttpResponse<String> getAccountIntegration(String accountId) {
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL.concat("/" + accountId)))
                    .headers("Content-Type", "application/json;charset=UTF-8")
                    .GET()
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
