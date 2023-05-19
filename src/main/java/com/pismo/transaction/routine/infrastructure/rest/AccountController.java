package com.pismo.transaction.routine.infrastructure.rest;

import com.pismo.transaction.routine.application.AccountUseCase;
import com.pismo.transaction.routine.domain.Account;
import com.pismo.transaction.routine.infrastructure.rest.dto.AccountDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private AccountUseCase accountUseCase;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Resource Created")
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    public ResponseEntity<Void> createAccount(@RequestBody AccountDto accountDto) {
        Account account = new Account(accountDto.getDocumentNumber(), accountDto.getAvailableCreditLimit());
        accountUseCase.create(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        Account account = accountUseCase.get(id);
        AccountDto accountDto = new AccountDto(account);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

}
