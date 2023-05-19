package com.pismo.transaction.routine.application;

import com.pismo.transaction.routine.domain.Account;
import com.pismo.transaction.routine.domain.Transaction;
import com.pismo.transaction.routine.infrastructure.database.entity.AccountEntity;
import com.pismo.transaction.routine.infrastructure.database.entity.OperationTypeEntity;
import com.pismo.transaction.routine.infrastructure.database.entity.TransactionEntity;
import com.pismo.transaction.routine.infrastructure.database.repository.AccountRepository;
import com.pismo.transaction.routine.infrastructure.database.repository.OperationTypeRepository;
import com.pismo.transaction.routine.infrastructure.database.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionUseCase implements TransactionManager {

    private TransactionRepository transactionRepository;

    private AccountRepository accountRepository;

    private OperationTypeRepository operationTypeRepository;

    @Override
    public void create(Transaction transaction) {

        AccountEntity accountEntity = accountRepository.findById(transaction.getAccountId())
                .orElseThrow(
                        ()-> new EntityNotFoundException("Could not found Account with id: " +
                                transaction.getAccountId() + " Is needed a created account to process a transaction.")
                );

        OperationTypeEntity operationTypeEntity = operationTypeRepository.findById(transaction.getOperationTypeId())
                .orElseThrow(
                        ()-> new EntityNotFoundException("Could not found operation type with id: " +
                                transaction.getOperationTypeId() + " Is needed a valid operation type to process a transaction.")
                );
        Account account = new Account(accountEntity);

        checkCreditLimit(account, transaction);

        TransactionEntity transactionEntity = new TransactionEntity(
                transaction,
                accountEntity,
                operationTypeEntity
        );
        TransactionEntity savedTransaction = transactionRepository.save(transactionEntity);
        updateCreditLimitWithTransaction(accountEntity, transaction);
        log.info("Transaction " + savedTransaction.getId() +  " created successfully.");
    }

    private void checkCreditLimit(Account account, Transaction transaction) {
        if(     !transaction.getOperationTypeId().equals(4L) &&
                account.getAvailableCreditLimit().add(transaction.getAmount()).intValue() < 0) {
                log.info("There is no credit limit available.");
                throw new IllegalArgumentException("There is no credit limit available.");
        }
    }

    private void updateCreditLimitWithTransaction(
            AccountEntity accountEntity,
            Transaction transaction) {

        accountEntity.setAvailableCreditLimit(accountEntity.getAvailableCreditLimit().add(transaction.getAmount()));
        accountRepository.save(accountEntity);
    }

}
