package com.pismo.transaction.routine.domain;

import java.math.BigDecimal;

public class PurchaseOperation implements TransactionAmountOperation {

    @Override
    public BigDecimal processTransactionAmount(BigDecimal amount) {
        return amount.negate();
    }

}
