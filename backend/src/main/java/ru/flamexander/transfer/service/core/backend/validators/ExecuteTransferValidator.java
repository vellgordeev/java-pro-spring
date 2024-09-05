package ru.flamexander.transfer.service.core.backend.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;
import ru.flamexander.transfer.service.core.backend.entities.Account;
import ru.flamexander.transfer.service.core.backend.errors.FieldValidationError;
import ru.flamexander.transfer.service.core.backend.errors.FieldsValidationException;
import ru.flamexander.transfer.service.core.backend.services.AccountsService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExecuteTransferValidator {
    private final AccountsService accountsService;

    public void validate(ExecuteTransferDtoRequest request) {
        List<FieldValidationError> errorFields = new ArrayList<>();

        Optional<Account> sourceAccountOpt = accountsService.getAccountByClientIdAndAccountNumber(request.getSourceClientId(), request.getSourceAccountNumber());
        if (sourceAccountOpt.isEmpty()) {
            errorFields.add(new FieldValidationError("sourceAccountId", "Счет отправителя не существует"));
        }

        Optional<Account> targetAccountOpt = accountsService.getAccountByClientIdAndAccountNumber(request.getTargetClientId(), request.getTargetAccountNumber());
        if (targetAccountOpt.isEmpty()) {
            errorFields.add(new FieldValidationError("targetAccountId", "Счет получателя не существует"));
        }

        if (sourceAccountOpt.isPresent()) {
            Account sourceAccount = sourceAccountOpt.get();
            if (sourceAccount.getBalance().compareTo(request.getAmount()) < 0) {
                errorFields.add(new FieldValidationError("amount", "Недостаточно средств на счете отправителя"));
            }
        }

        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            errorFields.add(new FieldValidationError("amount", "Сумма перевода должна быть больше нуля"));
        }

        if (!errorFields.isEmpty()) {
            throw new FieldsValidationException(errorFields);
        }
    }
}


