package com.pismo.transaction.routine.application;

import com.pismo.transaction.routine.domain.Account;

public interface AccountManager {

    void create(Account account);

    Account get(Long id);

}
