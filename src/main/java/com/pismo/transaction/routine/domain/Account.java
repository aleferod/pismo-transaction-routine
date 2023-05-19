package com.pismo.transaction.routine.domain;

import com.pismo.transaction.routine.infrastructure.database.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Account {

    private Long id;

    private final String documentNumber;

    private BigDecimal availableCreditLimit;

    public Account(String documentNumber) {
        if (documentNumber.isBlank()) {
            throw new IllegalArgumentException("Please enter with a valid document number.");
        }
        this.documentNumber = documentNumber;
    }

    public Account(String documentNumber, BigDecimal availableCreditLimit) {
        if (documentNumber.isBlank()) {
            throw new IllegalArgumentException("Please enter with a valid document number.");
        }
        this.documentNumber = documentNumber;
        this.availableCreditLimit = availableCreditLimit;
    }

    public Account(Long id, String documentNumber) {
        this.id = id;
        this.documentNumber = documentNumber;

    }

    public Account(AccountEntity accountEntity) {
        this.id = accountEntity.getId();
        this.documentNumber = accountEntity.getDocumentNumber();
        this.availableCreditLimit = accountEntity.getAvailableCreditLimit();
    }

}
