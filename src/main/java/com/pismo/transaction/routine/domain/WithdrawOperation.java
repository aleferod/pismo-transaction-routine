package com.pismo.transaction.routine.domain;

import java.math.BigDecimal;

public class WithdrawOperation implements TransactionAmountOperation {

    @Override
    public BigDecimal processTransactionAmount(BigDecimal amount) {
        return amount.negate();
    }

}
