package com.pismo.transaction.routine.steps;

import com.pismo.transaction.routine.infrastructure.database.entity.AccountEntity;
import com.pismo.transaction.routine.infrastructure.database.repository.AccountRepository;
import com.pismo.transaction.routine.integration.AccountRestIntegration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountStep {

    @Autowired
    private CommonResponseValues commonResponseValues;

    @Autowired
    private AccountRepository accountRepository;

    @When("client call create account endpoint with document number {string}")
    public void clientCallCreateAccountEndpointWithDocumentNumber(String documentNumber) {
        var response = AccountRestIntegration.createAccountIntegration(documentNumber);
        commonResponseValues.setStatusCode(response.statusCode());
    }

    @And("there is one account created on database")
    public void thereIsOneAccountCreatedOnDatabase() {
        List<AccountEntity> accounts = accountRepository.findAll();
        assertEquals(1, accounts.size());
    }

    @When("client call get account endpoint with id {string}")
    public void clientCallGetAccountEndpointWithId(String accountId) {
        var response = AccountRestIntegration.getAccountIntegration(accountId);
        commonResponseValues.setResponseBody(response.body());
        commonResponseValues.setStatusCode(response.statusCode());
    }

    @And("account with document number {string} is returned")
    public void accountWithDocumentNumberIsReturned(String documentNumber) {
        try {
            JSONObject body = new JSONObject(commonResponseValues.getResponseBody());
            assertEquals(documentNumber, body.get("document_number"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
