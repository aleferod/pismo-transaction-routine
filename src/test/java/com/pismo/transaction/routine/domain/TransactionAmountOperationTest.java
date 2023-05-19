package com.pismo.transaction.routine.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionAmountOperationTest {

    @Test
    void when_operation_type_is_purchase_should_return_negative_amount() {
        BigDecimal amount = new BigDecimal(100);
        Integer purchaseOperationTypeId = 1;
        BigDecimal purchaseAmount = OperationType.getTransactionAmountOperationByOperationId(purchaseOperationTypeId).processTransactionAmount(amount);
        BigDecimal expectedAmount = new BigDecimal(-100);
        assertEquals(expectedAmount, purchaseAmount);
    }

    @Test
    void when_operation_type_is_installment_purchase_should_return_negative_amount() {
        BigDecimal amount = new BigDecimal(100);
        Integer purchaseOperationTypeId = 2;
        BigDecimal purchaseAmount = OperationType.getTransactionAmountOperationByOperationId(purchaseOperationTypeId).processTransactionAmount(amount);
        BigDecimal expectedAmount = new BigDecimal(-100);
        assertEquals(expectedAmount, purchaseAmount);
    }

    @Test
    void when_operation_type_is_withdraw_should_return_negative_amount() {
        BigDecimal amount = new BigDecimal(100);
        Integer purchaseOperationTypeId = 3;
        BigDecimal purchaseAmount = OperationType.getTransactionAmountOperationByOperationId(purchaseOperationTypeId).processTransactionAmount(amount);
        BigDecimal expectedAmount = new BigDecimal(-100);
        assertEquals(expectedAmount, purchaseAmount);
    }

    @Test
    void when_operation_type_is_payment_should_return_positive_amount() {
        BigDecimal amount = new BigDecimal(100);
        Integer purchaseOperationTypeId = 4;
        BigDecimal purchaseAmount = OperationType.getTransactionAmountOperationByOperationId(purchaseOperationTypeId).processTransactionAmount(amount);
        BigDecimal expectedAmount = new BigDecimal(100);
        assertEquals(expectedAmount, purchaseAmount);
    }

    @Test
    void when_operation_type_is_invalid_should_return_validation() {
        BigDecimal amount = new BigDecimal(100);
        Integer purchaseOperationTypeId = 10;
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    OperationType.getTransactionAmountOperationByOperationId(purchaseOperationTypeId).processTransactionAmount(amount);
                }, "IllegalArgumentException error was expected");

        Assertions.assertEquals("Invalid operation type id: 10", thrown.getMessage());
    }

}