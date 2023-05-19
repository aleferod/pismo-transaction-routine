package com.pismo.transaction.routine.infrastructure.database.entity;

import com.pismo.transaction.routine.domain.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="account")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "document_number", unique = true)
    private String documentNumber;

    @Column(name="available_credit_limit")
    private BigDecimal availableCreditLimit;

    @OneToMany(mappedBy = "account")
    private Set<TransactionEntity> transactions;

    public AccountEntity(Account account) {
        this.documentNumber = account.getDocumentNumber();
        this.availableCreditLimit = account.getAvailableCreditLimit();
    }

    public void setAvailableCreditLimit(BigDecimal availableCreditLimit) {
        this.availableCreditLimit = availableCreditLimit;
    }
}
