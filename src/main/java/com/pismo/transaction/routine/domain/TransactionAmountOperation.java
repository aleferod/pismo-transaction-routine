package com.pismo.transaction.routine.domain;

import java.math.BigDecimal;

public interface TransactionAmountOperation {

    BigDecimal processTransactionAmount(BigDecimal amount);

}
