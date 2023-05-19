package com.pismo.transaction.routine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Transaction {

    private final Long accountId;

    private final Long operationTypeId;

    private final BigDecimal amount;

}
