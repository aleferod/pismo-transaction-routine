package com.pismo.transaction.routine.application;

import com.pismo.transaction.routine.domain.Transaction;

public interface TransactionManager {

    void create(Transaction transaction);

}
