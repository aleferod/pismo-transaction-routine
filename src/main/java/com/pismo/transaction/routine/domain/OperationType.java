package com.pismo.transaction.routine.domain;

import lombok.Getter;

@Getter
public enum
OperationType {

    COMPRA_A_VISTA(1, new PurchaseOperation() ),
    COMPRA_PARCELADA(2, new PurchaseOperation()),
    SAQUE(3, new WithdrawOperation()),
    PAGAMENTO(4, new PaymentAmountOperation());

    private final Integer code;

    private final TransactionAmountOperation transactionAmountOperation;

    OperationType(Integer code, TransactionAmountOperation transactionAmountOperation) {
        this.code = code;
        this.transactionAmountOperation = transactionAmountOperation;
    }

    public static TransactionAmountOperation getTransactionAmountOperationByOperationId(Integer operationTypeId) {
            for (OperationType operationType : OperationType.values()) {
                if(operationType.getCode().equals(operationTypeId)) {
                    return operationType.getTransactionAmountOperation();
                }
            }
            throw new IllegalArgumentException("Invalid operation type id: " + operationTypeId);
    }

}
