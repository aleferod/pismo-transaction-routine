package com.pismo.transaction.routine.infrastructure.database.repository;

import com.pismo.transaction.routine.infrastructure.database.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {}
