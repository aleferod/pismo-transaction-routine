package com.pismo.transaction.routine.infrastructure.rest;

import com.pismo.transaction.routine.application.TransactionUseCase;
import com.pismo.transaction.routine.domain.Transaction;
import com.pismo.transaction.routine.domain.OperationType;
import com.pismo.transaction.routine.infrastructure.rest.dto.TransactionDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionUseCase transactionUseCase;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Resource Created")
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = new Transaction(
                transactionDto.getAccountId(),
                transactionDto.getOperationTypeId(),
                OperationType.getTransactionAmountOperationByOperationId(
                        Math.toIntExact(transactionDto.getOperationTypeId())
                ).processTransactionAmount(transactionDto.getAmount())
        );
        transactionUseCase.create(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
