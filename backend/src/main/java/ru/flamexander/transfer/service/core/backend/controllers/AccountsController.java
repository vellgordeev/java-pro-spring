package ru.flamexander.transfer.service.core.backend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.flamexander.transfer.service.core.api.dtos.AccountDto;
import ru.flamexander.transfer.service.core.api.dtos.AccountsPageDto;
import ru.flamexander.transfer.service.core.api.dtos.CreateAccountDto;
import ru.flamexander.transfer.service.core.backend.entities.Account;
import ru.flamexander.transfer.service.core.backend.errors.ErrorDto;
import ru.flamexander.transfer.service.core.backend.errors.ResourceNotFoundException;
import ru.flamexander.transfer.service.core.backend.services.AccountsService;

import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@Tag(name = "Счета клиентов", description = "Методы работы со счетами клиентов")
public class AccountsController {
    private final AccountsService accountsService;

    private Function<Account, AccountDto> entityToDto = account -> new AccountDto(account.getId(), account.getAccountNumber(), account.getClientId(), account.getBalance());

    @GetMapping("/{accountNumber}")
    public AccountDto getAccountDetails(@RequestHeader Long clientId, @PathVariable String accountNumber) {
        return accountsService.getAccountByClientIdAndAccountNumber(clientId, accountNumber)
                .map(entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Счет не найден"));
    }

    @Operation(summary = "Получение информации о всех счетах пользователя")
    @GetMapping
    public AccountsPageDto getAllAccounts(
            @RequestHeader Long clientId
    ) {
        return new AccountsPageDto(accountsService.getAllAccounts(clientId).stream().map(entityToDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Создание нового счета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Счет успешно создан"),
            @ApiResponse(responseCode = "400", description = "Не удалось создать счет. Например, если превышено максимальное количество счетов - 10",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))}),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto createNewAccount(
            @RequestHeader @Parameter(description = "Идентификатор клиента для которого создается новый счет") Long clientId,
            @RequestBody CreateAccountDto createAccountDto
    ) {
        return entityToDto.apply(accountsService.createNewAccount(clientId, createAccountDto));
    }
}