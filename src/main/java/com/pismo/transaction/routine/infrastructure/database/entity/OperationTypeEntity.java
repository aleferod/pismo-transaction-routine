package com.pismo.transaction.routine.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="operation_type")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OperationTypeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "operationType")
    private Set<TransactionEntity> transactions;

}
