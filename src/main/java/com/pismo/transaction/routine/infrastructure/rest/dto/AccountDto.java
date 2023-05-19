package com.pismo.transaction.routine.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.transaction.routine.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("available_credit_limit")
    private BigDecimal availableCreditLimit;

    public AccountDto(Account account) {
        this.accountId = account.getId();
        this.documentNumber = account.getDocumentNumber();
        this.availableCreditLimit = account.getAvailableCreditLimit();
    }

}
