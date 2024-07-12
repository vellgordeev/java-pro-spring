package ru.flamexander.java.pro.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.java.pro.spring.dtos.AccountDto;
import ru.flamexander.java.pro.spring.dtos.AccountsPageDto;
import ru.flamexander.java.pro.spring.dtos.CreateAccountDto;
import ru.flamexander.java.pro.spring.entities.Account;
import ru.flamexander.java.pro.spring.errors.ResourceNotFoundException;
import ru.flamexander.java.pro.spring.services.AccountsService;

import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountsController {
    private final AccountsService accountsService;

    private Function<Account, AccountDto> entityToDto = account -> new AccountDto(account.getId(), account.getClientId(), account.getBalance());

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getAccountDetails(@RequestHeader Long clientId, @PathVariable Long id) {
//        Optional<Account> account = accountsService.getAccountById(clientId, id);
//        if (!account.isPresent()) {
//            ErrorDto errorDto = new ErrorDto("RESOURCE_NOT_FOUND", "Счет не найден");
//            return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(account.get(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public AccountDto getAccountDetails(@RequestHeader Long clientId, @PathVariable Long id) {
        return accountsService.getAccountById(clientId, id).map(entityToDto).orElseThrow(() -> new ResourceNotFoundException("Счет не найден"));
    }

    @GetMapping
    public AccountsPageDto getAllAccounts(@RequestHeader Long clientId) {
        return new AccountsPageDto(accountsService.getAllAccounts(clientId).stream().map(entityToDto).collect(Collectors.toList()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto createNewAccount(@RequestHeader Long clientId, @RequestBody CreateAccountDto createAccountDto) {
        return entityToDto.apply(accountsService.createNewAccount(clientId, createAccountDto));
    }
}