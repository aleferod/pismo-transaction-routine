package com.pismo.transaction.routine.steps;

import org.springframework.stereotype.Component;

@Component
public class CommonResponseValues {

    private Integer statusCode;

    private String responseBody;

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
