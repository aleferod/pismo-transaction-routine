package com.pismo.transaction.routine.domain;

import java.math.BigDecimal;

public class PaymentAmountOperation implements TransactionAmountOperation {

    @Override
    public BigDecimal processTransactionAmount(BigDecimal amount) {
        return amount;
    }

}
