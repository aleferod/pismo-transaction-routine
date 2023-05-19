package com.pismo.transaction.routine.application;

import com.pismo.transaction.routine.domain.Account;
import com.pismo.transaction.routine.infrastructure.database.entity.AccountEntity;
import com.pismo.transaction.routine.infrastructure.database.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class
AccountUseCase implements AccountManager {

    private AccountRepository accountRepository;

    @Override
    public void create(Account account) {
        AccountEntity accountEntity = new AccountEntity(account);
        AccountEntity savedAccount = accountRepository.save(accountEntity);
        log.info("Account " + savedAccount.getId() +  " created successfully.");
    }

    @Override
    public Account get(Long id) {
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Could not find Account with id: " + id)
        );
        log.info("Account " + accountEntity.getId() +  " returned successfully.");
        return new Account(accountEntity);
    }

}
