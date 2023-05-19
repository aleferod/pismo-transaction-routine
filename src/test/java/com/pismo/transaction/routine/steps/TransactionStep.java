package com.pismo.transaction.routine.steps;

import com.pismo.transaction.routine.domain.Account;
import com.pismo.transaction.routine.infrastructure.database.entity.AccountEntity;
import com.pismo.transaction.routine.infrastructure.database.entity.OperationTypeEntity;
import com.pismo.transaction.routine.infrastructure.database.repository.AccountRepository;
import com.pismo.transaction.routine.infrastructure.database.repository.OperationTypeRepository;
import com.pismo.transaction.routine.infrastructure.database.repository.TransactionRepository;
import com.pismo.transaction.routine.infrastructure.rest.dto.TransactionDto;
import com.pismo.transaction.routine.integration.TransactionRestIntegration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionStep {

    private BigDecimal amount;

    private Long operationTypeId;

    private Long accountId;

    @Autowired
    private CommonResponseValues commonResponseValues;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Given("amount {string} and operation type id {int} and account id {int}")
    public void amountAndOperationTypeIdAndAccountId(String amount, int operationTypeId, int accountId) {
        this.amount = new BigDecimal(amount);
        this.operationTypeId = (long) operationTypeId;
        this.accountId = (long) accountId;
    }

    @When("client call create transaction endpoint to create a transaction")
    public void clientCallCreateTransactionEndpointToCreateATransaction() {
        mockAccountAndOperationType();
        TransactionDto transactionDto = new TransactionDto(this.accountId, this.operationTypeId, this.amount);
        var response = TransactionRestIntegration.createTransactionIntegration(transactionDto);
        commonResponseValues.setStatusCode(response.statusCode());
        commonResponseValues.setResponseBody(response.body());
    }

    @And("there is {int} transaction created on database")
    public void thereIsTransactionCreatedOnDatabase(int size) {
        var transactions = this.transactionRepository.findAll();
        assertEquals(size, transactions.size());
    }

    private void mockAccountAndOperationType() {
        OperationTypeEntity operationTypeCompraAVistaEntity = new OperationTypeEntity(1L, "COMPRA A VISTA", null);
        OperationTypeEntity operationTypeCompraParceladaEntity = new OperationTypeEntity(2L, "COMPRA A VISTA", null);
        OperationTypeEntity operationTypeSaqueEntity = new OperationTypeEntity(3L, "COMPRA A VISTA", null);
        OperationTypeEntity operationTypePagamentoEntity = new OperationTypeEntity(4L, "COMPRA A VISTA", null);

        this.operationTypeRepository.saveAll(List.of(
                operationTypeCompraAVistaEntity,
                operationTypeCompraParceladaEntity,
                operationTypeSaqueEntity,
                operationTypePagamentoEntity
        ));

        var accounts = accountRepository.findAll();
        if(accounts.isEmpty()) {
            Account account = new Account("12345678900", BigDecimal.ZERO);
            AccountEntity accountEntity = new AccountEntity(account);
            this.accountRepository.save(accountEntity);
        }
    }


}
