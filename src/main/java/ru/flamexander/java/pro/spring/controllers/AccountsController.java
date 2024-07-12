package ru.flamexander.java.pro.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.java.pro.spring.dtos.CreateAccountDto;
import ru.flamexander.java.pro.spring.entities.Account;
import ru.flamexander.java.pro.spring.services.AccountsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountsController {
    private final AccountsService accountsService;

    @GetMapping("/{id}")
    public Account getAccountDetails(@RequestHeader String clientId, @PathVariable String id) {
        return accountsService.getAccountById(clientId, id);
    }

    @GetMapping
    public List<Account> getAllAccounts(@RequestHeader String clientId) {
        return accountsService.getAllAccounts(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewAccount(@RequestHeader String clientId, @RequestBody CreateAccountDto createAccountDto) {
        return accountsService.createNewAccount(clientId, createAccountDto);
    }
}
