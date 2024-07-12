package ru.flamexander.java.pro.spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flamexander.java.pro.spring.entities.Account;
import ru.flamexander.java.pro.spring.errors.AppLogicException;
import ru.flamexander.java.pro.spring.errors.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final AccountsService accountsService;

    @Transactional
    public void transfer(Long sourceAccountId, Long targetAccountId) {
        Account source = accountsService.getAccountById(1L, sourceAccountId).orElseThrow(() -> new AppLogicException("TRANSFER_SOURCE_ACCOUNT_NOT_FOUND", "Перевод невозможен поскольку не существует счет отправителя"));
        Account target = accountsService.getAccountById(1L, targetAccountId).orElseThrow(() -> new AppLogicException("TRANSFER_TARGET_ACCOUNT_NOT_FOUND", "Перевод невозможен поскольку не существует счет получателяч"));

    }
}
