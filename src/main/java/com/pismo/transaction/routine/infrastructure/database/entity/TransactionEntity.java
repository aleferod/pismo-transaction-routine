package com.pismo.transaction.routine.infrastructure.database.entity;

import com.pismo.transaction.routine.domain.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="transaction")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name="operation_type_id", nullable=false)
    private OperationTypeEntity operationType;

    private BigDecimal amount;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    public TransactionEntity(Transaction transaction, AccountEntity accountEntity, OperationTypeEntity operationTypeEntity) {
        this.account = accountEntity;
        this.operationType = operationTypeEntity;
        this.amount = transaction.getAmount();
        this.eventDate = LocalDateTime.now();
    }

}
