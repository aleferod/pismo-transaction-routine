package com.pismo.transaction.routine.steps;

import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonStep {

    @Autowired
    private CommonResponseValues commonResponseValues;

    @Then("return status code {int}")
    public void returnStatusCode(int statusCode) {
        assertEquals(statusCode, this.commonResponseValues.getStatusCode());
    }

}
