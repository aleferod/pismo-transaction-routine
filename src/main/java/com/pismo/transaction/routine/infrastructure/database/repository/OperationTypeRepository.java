package com.pismo.transaction.routine.infrastructure.database.repository;

import com.pismo.transaction.routine.infrastructure.database.entity.OperationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationTypeEntity, Long> {}
